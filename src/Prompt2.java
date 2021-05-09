
//Prompt.java에서 parseDay, runPrompt 부분의 if -> switch로 구현

import java.text.ParseException;
import java.util.Scanner;

public class Prompt2 {

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
		switch (week) {
		case "su":
			return 0;
		case "mo":
			return 1;
		case "tu":
			return 2;
		case "we":
			return 3;
		case "th":
			return 4;
		case "fr":
			return 5;
		case "sa":
			return 5;
		default:
			return 0;
		}
	}

	public void runPrompt() throws ParseException {
		printMenu();

		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		boolean isLoop = true;
		while (isLoop) {
			System.out.print("메뉴를 선택하세요 (1, 2, 3, h, q): ");
			String cmd = scanner.next();

			switch (cmd) {
			case "1":
				cmdRegister(scanner, cal);
				break;
			case "2":
				cmdSearch(scanner, cal);
				break;
			case "3":
				cmdCal(scanner, cal);
				break;
			case "h":
				printMenu();
				break;
			case "q":
				isLoop = false;
				break;
			}
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
