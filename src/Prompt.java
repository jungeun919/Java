import java.text.ParseException;
import java.util.Scanner;

public class Prompt {

	public void printMenu() {
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록");
		System.out.println("| 2. 일정 검색");
		System.out.println("| 3. 달력 보기");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+----------------------+");
	}

	/*
	 * @param week: 요일명
	 * 
	 * @return 0 ~ 6: (0: Sunday, 6: Saturday)
	 */
	public int parseDay(String week) {
		if (week.equals("su"))
			return 0;
		else if (week.equals("mo"))
			return 1;
		else if (week.equals("tu"))
			return 2;
		else if (week.equals("we"))
			return 3;
		else if (week.equals("th"))
			return 4;
		else if (week.equals("fr"))
			return 5;
		else if (week.equals("sa"))
			return 6;
		else
			return 0;
	}

	public void runPrompt() throws ParseException {
		printMenu();

		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		while (true) {
			System.out.print("메뉴를 선택하세요 (1, 2, 3, h, q): ");
			String cmd = scanner.next();
			if (cmd.equals("1"))
				cmdRegister(scanner, cal);
			else if (cmd.equals("2"))
				cmdSearch(scanner, cal);
			else if (cmd.equals("3"))
				cmdCal(scanner, cal);
			else if (cmd.equals("h"))
				printMenu();
			else if (cmd.equals("q"))
				break;
		}

		System.out.println("Thank you");
		scanner.close();
	}

	private void cmdCal(Scanner s, Calendar c) {
		System.out.print("연도를 입력하세요: ");
		int year = s.nextInt();

		System.out.printf("달을 입력하세요: ");
		int month = s.nextInt();
		System.out.println();

		if (month > 12 || month < 1) {
			System.out.println("잘못된 입력입니다.");
			return;
		}

		c.printCalendar(year, month);
	}

	private void cmdSearch(Scanner s, Calendar c) {
		System.out.println("[일정 검색]");
		System.out.printf("날짜를 입력해주세요 (yyyy-MM-dd): ");
		String date = s.next();

		PlanItem plan;
		plan = c.searchPlan(date);
		
		if (plan != null)
			System.out.println(plan.detail);
		else
			System.out.println("일정이 없습니다.");
	}

	private void cmdRegister(Scanner s, Calendar c) throws ParseException {
		System.out.println("[새 일정 등록]");
		System.out.printf("날짜를 입력해주세요 (yyyy-MM-dd): ");
		String date = s.next();
		s.nextLine(); // 개행문자 제거
		System.out.printf("일정을 입력해주세요: ");
		String text = s.nextLine();
		c.registerPlan(date, text);
	}

	public static void main(String[] args) throws ParseException {
		// 쉘 실행
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
