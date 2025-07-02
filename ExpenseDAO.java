package classes;

import java.util.*;

public class ExpenseDAO {
    private Map<String, List<ExpenseDTO>> userExpenseMap;
    private int expenseIdCounter;

    public ExpenseDAO() {
        this.userExpenseMap = new HashMap<>(); // 유저별 지출 리스트 저장
        this.expenseIdCounter = 1; // ID 자동 증가
    }

	// 사용자가 입력한 지출 내역을 저장 (유저별 관리)
	public void expAdd(String userId, String expDate, String expItem, int expMoney, String expCategory, String expMemo) {
		userExpenseMap.putIfAbsent(userId, new ArrayList<>()); // 유저가 처음 추가하면 리스트 생성
		int expId = expenseIdCounter++; // 각 지출에 고유 ID 부여
		ExpenseDTO newExpense = new ExpenseDTO(expMoney, expDate, expCategory, expMemo, expItem, expId);
		userExpenseMap.get(userId).add(newExpense);
		System.out.println("[" + userId + "]의 지출 내역이 추가되었습니다. (ID: " + expId + ")");
	}


    // 특정 유저의 지출 내역을 삭제
    public void expDelete(String userId, int expId) {
        List<ExpenseDTO> expenseList = userExpenseMap.get(userId);
        if (expenseList == null) {
            System.out.println("해당 유저의 지출 내역이 없습니다.");
            return;
        }

        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getExpId() == expId) {
                expenseList.remove(i);
                System.out.println("[" + userId + "]의 지출 내역이 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 지출 내역을 찾을 수 없습니다.");
    }

    // 특정 유저의 지출 내역 수정
    public void expEdit(String userId, int expId, String expNewDate, String expNewItem, int expNewMoney, String expNewCategory, String expNewMemo) {
        List<ExpenseDTO> expenseList = userExpenseMap.get(userId);
        if (expenseList == null) {
            System.out.println("해당 유저의 지출 내역이 없습니다.");
            return;
        }

        for (ExpenseDTO expense : expenseList) {
            if (expense.getExpId() == expId) {
				expense.setExpDate(expNewDate);
                expense.setExpItem(expNewItem);
                expense.setExpMoney(expNewMoney);
                expense.setExpCategory(expNewCategory);
                expense.setExpMemo(expNewMemo);
                System.out.println("[" + userId + "]의 지출 내역이 수정되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 지출 내역을 찾을 수 없습니다.");
    }

    // 특정 유저의 총 지출 금액 반환
    public int expGetTotal(String userId) {
        List<ExpenseDTO> expenseList = userExpenseMap.get(userId);
        if (expenseList == null) {
            System.out.println("해당 유저의 지출 내역이 없습니다.");
            return 0;
        }

        int total = 0;
        for (ExpenseDTO expense : expenseList) {
            total += expense.getExpMoney();
        }
        return total;
    }

    // 특정 유저의 저장된 모든 지출 내역 출력
    public void printAllExpenses(String userId) {
        List<ExpenseDTO> expenseList = userExpenseMap.get(userId);
        if (expenseList == null || expenseList.isEmpty()) {
            System.out.println("[" + userId + "]의 지출 내역이 없습니다.");
            return;
        }

        System.out.println("[" + userId + "]의 지출 내역:");
        for (ExpenseDTO expense : expenseList) {
            System.out.println(expense);
        }
    }
}
