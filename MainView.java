package classes;

import java.util.Scanner;

public class MainView {

    private UserAPI uAPI = new UserAPI();
    private UserDAO uDAO = new UserDAO();
    private ExpenseDAO eDAO = new ExpenseDAO();
	private IncomeDAO iDAO = new IncomeDAO();  // IncomeDAO 추가
    private Scanner sc = new Scanner(System.in);

	int inputValidate(String _userInput){
		int _errCode = 0;	

		if (_userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
			System.out.println("\n\t시스템을 종료합니다.\n");
			_errCode = 1;
		}
		else if (_userInput.isEmpty()) {	// Err01. 엔터 입력
			System.out.println("^ [Error Chk] 공백이 입력되었습니다.\n");
			_errCode = 2;
		}
		else if (_userInput.length() > 1) {	// Err02. 자릿수 오류
			System.out.println("^ [Error Chk] 1자리 숫자를 입력해주세요.\n");
			_errCode = 3;
		}
		else if ((_userInput.charAt(0) < 49) || (_userInput.charAt(0) > 51)) {	// Err03. 범위 오류
			System.out.println("^ [Error Chk] 1 ~ 3 사이의 번호를 입력해주세요. \n");
			_errCode = 4;
		}
		return _errCode;
	}

	public void initialPrint(){
		uAPI.mLine('=' ,75);
		System.out.printf("%42s\n", "##  가계부 시스템  ##");
		uAPI.mLine('=' ,75);
		System.out.printf("\n%-27s%s\n","","1. 회원가입");
		System.out.printf("\n%-27s%s\n","","2. 아이디 찾기");
		System.out.printf("\n%-27s%s\n","","3. 로그인");
		System.out.printf("\n\n%57s%s\n\n","","[시스템 종료 : Q]");
		uAPI.mLine('-' ,75);
		System.out.print(" ☞ 메뉴를 선택해주세요 : ");
	}

    public void mainShowMenu() {
        while (true) {
            initialPrint();
            String userInput = sc.nextLine();

            switch (userInput) {
                case "1":
                    mainRegisterUser();
                    break;
                case "2":
                    mainViewFindUser();
                    break;
                case "3":
                    mainViewLogin();
                    break;
                case "q":
                case "Q":
                    System.out.println("\n\t시스템을 종료합니다.\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    private void mainRegisterUser() {
		String userName;
		String userBirth;
		String userId;
		String userInput;

		System.out.println("\n");
		uAPI.mLine('=' ,75);
		System.out.printf("%37s\n", "회원가입");
		uAPI.mLine('=' ,75);
		System.out.printf("\n%-20s%s","","이름, 생년월일, 아이디를 입력하세요.");
		System.out.printf("\n\n%-15s%s","","아이디 조건 [숫자와 영문자 조합, 5자 이상]");
		System.out.printf("\n\n\n%48s%s\n\n","","[이전 메뉴로 돌아가기 : Q]");
		uAPI.mLine('-' ,75);

		while(true){
			System.out.print(" ☞ 이름을 입력하세요. : ");
			userInput = sc.nextLine();
			if (userInput.equalsIgnoreCase("q")) {	// 메인화면
				System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
				return;
			}else if (userInput.isEmpty() || userInput.trim().isEmpty()) {   // 입력 예외처리 (공백, 엔터)
				continue;
		    }
			userName = userInput;
			break;
		}

		while(true){
			System.out.print(" ☞ 생년월일을 입력하세요. : ");
			userInput = sc.nextLine();
			if (userInput.equalsIgnoreCase("q")) {	// 메인화면
				System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
				return;
			}else if (userInput.isEmpty() || userInput.trim().isEmpty()) {   // 입력 예외처리 (공백, 엔터)
				continue;
			}
			userBirth = userInput;
			break;
		}
		

		while(true){
			System.out.print(" ☞ 아이디를 입력하세요. : ");
            userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("q"))   { // 시스템 종료
      			System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
               return;
            }
            uDAO.userIdCondition(userInput); 			// 아이디 조건 함수 호출

            if (uDAO.userChk) {										// 아이디 조건에 부합시
               userId = userInput;
               uDAO.userIsUserExists(userId);  // 회원 가입 전 MainView에서 입력받은 Id에 대한 중복 확인 실행 

               if (!uDAO.userChk) {						// 중복이 아닌 경우 사용자 객체 추가
                  uDAO.userAdd(userName, userId, userBirth, uDAO.userCreateAt());
                  userId = userInput;
                  return;
               } else {												// 중복이면 아이디를 재입력받는 while문으로 이동
                  System.out.println("이미 존재하는 아이디입니다.");
               }
            } else {
               System.out.print("5자 이상의 숫자와 영문자 조합으로 입력하세요. \n");
            }
         }
      }

    private void mainViewFindUser() {
		String userName;
		String userBirth;
		String userInput;

		System.out.println("\n");
		uAPI.mLine('=' ,75);
		System.out.printf("%38s\n", "아이디 찾기");
		uAPI.mLine('=' ,75);
		System.out.printf("\n%-24s%s","","이름, 생년월일을 입력하세요.");
		System.out.printf("\n\n\n%48s%s\n\n","","[메인 메뉴로 돌아가기 : Q]");
		uAPI.mLine('-' ,75);

		while(true){
			System.out.print(" ☞ 이름을 입력하세요. : ");
			userInput = sc.nextLine();
			if (userInput.equalsIgnoreCase("q")) {	// 메인화면
				System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
				return;
			}else if(userInput.isEmpty() || userInput.trim().isEmpty()){
				continue;
			}
			userName = userInput;
			break;
		}

		while(true){
			System.out.print(" ☞ 생년월일을 입력하세요. : ");
			userInput = sc.nextLine();
			if (userInput.equalsIgnoreCase("q")) {	// 메인화면
				System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
				return;
			}else if(userInput.isEmpty() || userInput.trim().isEmpty()){
				continue;
			}
			userBirth = userInput;
			break;
		}

        uDAO.userFindId(userName, userBirth);
    }

    private void mainViewLogin() {
		String userId;
		System.out.println("\n");
		uAPI.mLine('=' ,75);
		System.out.printf("%37s\n", "로그인");
		uAPI.mLine('=' ,75);
		System.out.printf("\n%-27s%s","","아이디를 입력하세요.");
		System.out.printf("\n\n\n%48s%s\n\n","","[메인 메뉴로 돌아가기 : Q]");
		uAPI.mLine('-' ,75);

		while(true){ 
			System.out.print(" ☞ 아이디를 입력하세요. : ");
			userId = sc.nextLine();
			if (userId.isEmpty() || userId.trim().isEmpty()){	// 입력 예외처리 (공백, 엔터)
				continue;
			}
			break;
		}
        if (uDAO.userIsUserExists(userId)) {
            System.out.println("로그인 성공!");
            mainMoneyMenu(userId);
        } else {
            System.out.println("존재하지 않는 아이디입니다.");
        }
    }

    private void mainMoneyMenu(String userId) {
		while (true) {
			System.out.println("\n");
			uAPI.mLine('=', 75);
			System.out.printf("%32s 님의 가계부\n", userId);
			uAPI.mLine('=', 75);
			System.out.printf("\n%-32s%s\n", "", "1. 내역 기입"); // 기존 '지출 내역 기입' -> '내역 기입'으로 변경
			System.out.printf("\n%-32s%s\n", "", "2. 내역 수정");
			System.out.printf("\n%-32s%s\n", "", "3. 내역 확인");
			System.out.printf("\n%-32s%s\n", "", "4. 잔액 확인");
			System.out.printf("\n\n\n%58s%s\n\n", "", "[로그아웃 : Q]");
			uAPI.mLine('-', 75);
			System.out.print(" ☞ 메뉴를 선택해주세요 : ");
			String input = sc.nextLine().trim();

			if (input.equalsIgnoreCase("q")) {
				System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
				return;
			}

			switch (input) {
				case "1":
					mainInputMenu(userId);  // 내역 기입 선택 시 새로운 메서드 호출
					break;
				case "2":
					mainEditMoney(userId);
					break;
				case "3":
					mainShowMoney(userId);
					break;
				case "4":
					mainShowBalance(userId);
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 선택하세요.");
			}
		}
	}

	private void mainInputMenu(String userId) {
    uAPI.mLine('=', 75);
    System.out.printf("%39s\n", "내역 기입");
    uAPI.mLine('=', 75);
    System.out.printf("\n%-32s%s\n", "", "1. 지출 내역 기입");
    System.out.printf("\n%-32s%s\n", "", "2. 입금 내역 기입");
    System.out.printf("\n\n\n%48s%s\n\n", "", "[이전 메뉴로 돌아가기 : Q]");
    uAPI.mLine('-', 75);
    System.out.print(" ☞ 입력할 내역을 선택하세요: ");
    
    String input = sc.nextLine().trim();
    
		if (input.equalsIgnoreCase("q")) {
			System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
			return;
		}

		switch (input) {
			case "1":
				mainInputMoney(userId);
				break;
			case "2":
				mainInputIncome(userId);
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 선택하세요.");
				mainInputMenu(userId);
		}
	}

	private void mainEditMoney(String userId) {
    uAPI.mLine('=', 75);
    System.out.printf("%39s\n", "내역 수정");
    uAPI.mLine('=', 75);
    System.out.printf("\n%-32s%s\n", "", "1. 지출 내역 수정");
    System.out.printf("\n%-32s%s\n", "", "2. 입금 내역 수정");
    System.out.printf("\n\n\n%48s%s\n\n", "", "[이전 메뉴로 돌아가기 : Q]");
    uAPI.mLine('-', 75);
    System.out.print(" ☞ 수정할 내역을 선택하세요: ");
    
    String input = sc.nextLine().trim();
    
    if (input.equalsIgnoreCase("q")) {
        System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
        return;
    }

    switch (input) {
        case "1":
            mainEditExpense(userId);
            break;
        case "2":
            mainEditIncome(userId);
            break;
        default:
            System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            mainEditMoney(userId);
    }
}

    private void mainInputMoney(String userId) {
		System.out.println("\n");
		uAPI.mLine('=' ,75);
		System.out.printf("\n%-28s%s\n","","내역을 입력해주세요.");
		System.out.printf("\n%-8s%s\n\n", "", "[ 예시 ] ");
		System.out.println("\t날짜\t\t지출\t금액\t카테고리   메모");
		System.out.printf("\n\tYY-MM-DD\t커피\t2000\t간식\t   메가커피 아이스티");
		System.out.printf("\n\n\n%48s%s\n\n","","[이전 메뉴로 돌아가기 : Q]");
		uAPI.mLine('=' ,75);


        String date = null;
		while (true) {
			System.out.print(" ☞ 날짜를 입력하세요 (YY-MM-DD) : ");
			date = sc.nextLine();
			if (date.matches("\\d{2}-\\d{2}-\\d{2}")) { // YY-MM-DD 형식
				break;
			} else {
				System.out.println("잘못된 날짜 형식입니다. (YY-MM-DD 형식으로 입력해주세요.)");
			}
		}

		String item = null;
		while (true) {
			System.out.print(" ☞ 지출 항목을 입력하세요 : ");
			item = sc.nextLine();
			if (!item.trim().isEmpty()) { // 항목이 비어있지 않으면
				break;
			} else {
				System.out.println("지출 항목을 입력해주세요.");
			}
		}

		int amount = -1;
		while (true) {
			System.out.print(" ☞ 금액을 입력하세요 : ");
			try {
				amount = Integer.parseInt(sc.nextLine());
				if (amount > -1) { 
					break;
				} else {
					System.out.println("금액은 -1 이상의 숫자여야 합니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요.");
			}
		}

		String category = null;
		while (true) {
			System.out.print(" ☞ 카테고리를 입력하세요: ");
			category = sc.nextLine();
			if (!category.trim().isEmpty()) { // 카테고리 비어있지 않으면
				break;
			} else {
				System.out.println("카테고리를 입력해주세요.");
			}
		}

		String memo = null;
		while (true) {
			System.out.print(" ☞ 메모를 입력하세요: ");
			memo = sc.nextLine();
			if (!memo.trim().isEmpty()) { // 메모 비어있지 않으면
				break;
			} else {
				System.out.println("메모를 입력해주세요.");
			}
		}

		eDAO.expAdd(userId, date, item, amount, category, memo);
    }

	private void mainEditExpense(String userId) {
		String userInput;

		uAPI.mLine('=', 75);
		System.out.printf("%39s\n", "지출 내역 수정");
		uAPI.mLine('=', 75);
		eDAO.printAllExpenses(userId);
		System.out.printf("\n\n\n%48s%s\n\n", "", "[삭제 : D / 이전 화면 : Q]");
		uAPI.mLine('-', 75);
		System.out.print(" ☞ 수정할 번호를 입력하세요. [삭제를 원할 시 'D'부터 입력]: ");
		userInput = sc.nextLine().trim();

		if (userInput.equalsIgnoreCase("q")) {
			System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
			return;
		}

		if (userInput.equalsIgnoreCase("d")) {
			System.out.print(" ☞ 삭제할 지출 번호를 입력하세요: ");
			userInput = sc.nextLine().trim();
			if (userInput.equalsIgnoreCase("q")) {
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			eDAO.expDelete(userId, Integer.parseInt(userInput));
			return;
		}

		int expId;
		try {
			expId = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요.");
			mainEditExpense(userId);
			return;
		}

		String newDate;
		while (true) {
			System.out.print(" ☞ 새로운 날짜 입력하세요 (YY-MM-DD): ");
			newDate = sc.nextLine().trim();
			if (newDate.matches("\\d{2}-\\d{2}-\\d{2}")) {
				break;
			} else {
				System.out.println("잘못된 날짜 형식입니다. (YY-MM-DD 형식으로 입력해주세요.)");
			}
		}

		String newItem;
		while (true) {
			System.out.print(" ☞ 새로운 지출 항목을 입력하세요: ");
			newItem = sc.nextLine().trim();
			if (!newItem.isEmpty()) {
				break;
			} else {
				System.out.println("지출 항목을 입력해주세요.");
			}
		}

		int newMoney;
		while (true) {
			System.out.print(" ☞ 새로운 지출 금액을 입력하세요: ");
			try {
				newMoney = Integer.parseInt(sc.nextLine().trim());
				if (newMoney > 0) {
					break;
				} else {
					System.out.println("금액은 1 이상이어야 합니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요.");
			}
		}

		System.out.print(" ☞ 새로운 카테고리를 입력하세요: ");
		String newCategory = sc.nextLine().trim();

		System.out.print(" ☞ 새로운 메모를 입력하세요: ");
		String newMemo = sc.nextLine().trim();

		eDAO.expEdit(userId, expId, newDate, newItem, newMoney, newCategory, newMemo);
	}

	private void mainEditIncome(String userId) {
		String userInput;

		uAPI.mLine('=', 75);
		System.out.printf("%39s\n", "입금 내역 수정");
		uAPI.mLine('=', 75);
		iDAO.printAllIncomes(userId);
		System.out.printf("\n\n\n%48s%s\n\n", "", "[삭제 : D / 이전 화면 : Q]");
		uAPI.mLine('-', 75);
		System.out.print(" ☞ 수정할 번호를 입력하세요. [삭제를 원할 시 'D'부터 입력]: ");
		userInput = sc.nextLine().trim();

		if (userInput.equalsIgnoreCase("q")) {
			System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
			return;
		}

		if (userInput.equalsIgnoreCase("d")) {
			System.out.print(" ☞ 삭제할 입금 번호를 입력하세요: ");
			userInput = sc.nextLine().trim();
			if (userInput.equalsIgnoreCase("q")) {
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			iDAO.incDelete(userId, Integer.parseInt(userInput));
			return;
		}

		int incId;
		try {
			incId = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요.");
			mainEditIncome(userId);
			return;
		}

		String newDate;
		while (true) {
			System.out.print(" ☞ 새로운 날짜 입력하세요 (YY-MM-DD): ");
			newDate = sc.nextLine().trim();
			if (newDate.matches("\\d{2}-\\d{2}-\\d{2}")) {
				break;
			} else {
				System.out.println("잘못된 날짜 형식입니다. (YY-MM-DD 형식으로 입력해주세요.)");
			}
		}

		String newItem;
		while (true) {
			System.out.print(" ☞ 새로운 입금 항목을 입력하세요: ");
			newItem = sc.nextLine().trim();
			if (!newItem.isEmpty()) {
				break;
			} else {
				System.out.println("입금 항목을 입력해주세요.");
			}
		}

		int newMoney;
		while (true) {
			System.out.print(" ☞ 새로운 입금 금액을 입력하세요: ");
			try {
				newMoney = Integer.parseInt(sc.nextLine().trim());
				if (newMoney > 0) {
					break;
				} else {
					System.out.println("금액은 1 이상이어야 합니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요.");
			}
		}

		System.out.print(" ☞ 새로운 카테고리를 입력하세요: ");
		String newCategory = sc.nextLine().trim();

		System.out.print(" ☞ 새로운 메모를 입력하세요: ");
		String newMemo = sc.nextLine().trim();

		iDAO.incEdit(userId, incId, newDate, newItem, newMoney, newCategory, newMemo);
	}

	private void mainShowMoney(String userId) {
		uAPI.mLine('=', 75);
		System.out.printf("%39s\n", "내역 확인");
		uAPI.mLine('=', 75);
		System.out.printf("\n%-32s%s\n", "", "1. 지출 내역 확인");
		System.out.printf("\n%-32s%s\n", "", "2. 입금 내역 확인");
		System.out.printf("\n\n\n%48s%s\n\n", "", "[이전 메뉴로 돌아가기 : Q]");
		uAPI.mLine('-', 75);
		System.out.print(" ☞ 확인할 내역을 선택하세요: ");
		
		String input = sc.nextLine().trim();
		
		if (input.equalsIgnoreCase("q")) {
			System.out.println("\n\t\t\t\t메뉴로 돌아갑니다.\n");
			return;
		}

		switch (input) {
			case "1":
				mainShowExpense(userId);
				break;
			case "2":
				mainShowIncome(userId);
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 선택하세요.");
				mainShowMoney(userId);
		}
	}

	private void mainShowExpense(String userId) {
		uAPI.mLine('=', 75);
		eDAO.printAllExpenses(userId);
		uAPI.mLine('-', 75);

		while (true) { // 반복문 추가
			System.out.print(" ☞ 총 지출 내역을 확인하려면 'A'를 입력하세요 (이전 화면으로 가려면 'Q'): ");
			String input = sc.nextLine().trim();

			if (input.equalsIgnoreCase("A")) {
				mainShowTotalMoney(userId);
				return;
			} else if (input.equalsIgnoreCase("Q")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				return;
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		}
	}

    private void mainShowTotalMoney(String userId) {
        int total = eDAO.expGetTotal(userId);
        System.out.println("총 지출 금액: " + total + "원");
    }

	private void mainInputIncome(String userId) {
		System.out.print(" ☞ 날짜를 입력하세요 (YY-MM-DD) : ");
		String date = sc.nextLine();

		System.out.print(" ☞ 입금 항목을 입력하세요 : ");
		String item = sc.nextLine();

		System.out.print(" ☞ 금액을 입력하세요 : ");
		int amount = Integer.parseInt(sc.nextLine());

		System.out.print(" ☞ 카테고리를 입력하세요: ");
		String category = sc.nextLine();

		System.out.print(" ☞ 메모를 입력하세요: ");
		String memo = sc.nextLine();

		iDAO.incAdd(userId, date, item, amount, category, memo);
	}

	private void mainShowIncome(String userId) {
		uAPI.mLine('=' ,75);
		iDAO.printAllIncomes(userId);  // 입금 내역 출력
		uAPI.mLine('-' ,75);

		while (true) { // 반복문 추가
			System.out.print(" ☞ 총 입금 내역을 확인하려면 'A'를 입력하세요 (이전 화면으로 가려면 'Q'): ");
			String input = sc.nextLine();

			if (input.equalsIgnoreCase("A")) {
				mainShowTotalIncome(userId); // 총 입금 금액 출력
				return;
			} else if (input.equalsIgnoreCase("Q")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				return; // 이전 화면(가계부 메뉴)으로 복귀
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		}
	}

	private void mainShowTotalIncome(String userId) {
		int total = iDAO.incGetTotal(userId);
		System.out.println("총 입금 금액: " + total + "원");
	}

	private void mainShowBalance(String userId) {
		int totalIncome = iDAO.incGetTotal(userId);  // 총 입금 금액
		int totalExpense = eDAO.expGetTotal(userId); // 총 지출 금액

		int balance = totalIncome - totalExpense; // 잔액 계산

		uAPI.mLine('=' ,75);
		System.out.println("[" + userId + "]의 현재 잔액:");
		System.out.println("총 입금 금액: " + totalIncome + "원");
		System.out.println("총 지출 금액: " + totalExpense + "원");
		System.out.println("남은 잔액: " + balance + "원");
		uAPI.mLine('-' ,75);
	}

    public static void main(String[] args) {
        MainView obj = new MainView();
        obj.mainShowMenu();
    }
}
