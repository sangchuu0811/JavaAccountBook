package classes;

import java.util.*;

public class IncomeDAO {
    private Map<String, List<IncomeDTO>> userIncomeMap;
    private int incomeIdCounter;

    public IncomeDAO() {
        this.userIncomeMap = new HashMap<>();
        this.incomeIdCounter = 1;
    }

    // 입금 내역 추가
    public void incAdd(String userId, String incDate, String incItem, int incMoney, String incCategory, String incMemo) {
        userIncomeMap.putIfAbsent(userId, new ArrayList<>());
        int incId = incomeIdCounter++;
        IncomeDTO newIncome = new IncomeDTO(incMoney, incDate, incCategory, incMemo, incItem, incId);
        userIncomeMap.get(userId).add(newIncome);
        System.out.println("[" + userId + "]의 입금 내역이 추가되었습니다. (ID: " + incId + ")");
    }

    // 특정 유저의 입금 내역 삭제
    public void incDelete(String userId, int incId) {
        List<IncomeDTO> incomeList = userIncomeMap.get(userId);
        if (incomeList == null) {
            System.out.println("해당 유저의 입금 내역이 없습니다.");
            return;
        }

        for (int i = 0; i < incomeList.size(); i++) {
            if (incomeList.get(i).getIncId() == incId) {
                incomeList.remove(i);
                System.out.println("[" + userId + "]의 입금 내역이 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 입금 내역을 찾을 수 없습니다.");
    }

    // 특정 유저의 입금 내역 수정
    public void incEdit(String userId, int incId, String newDate, String newItem, int newMoney, String newCategory, String newMemo) {
        List<IncomeDTO> incomeList = userIncomeMap.get(userId);
        if (incomeList == null) {
            System.out.println("해당 유저의 입금 내역이 없습니다.");
            return;
        }

        for (IncomeDTO income : incomeList) {
            if (income.getIncId() == incId) {
                income.setIncDate(newDate);
                income.setIncItem(newItem);
                income.setIncMoney(newMoney);
                income.setIncCategory(newCategory);
                income.setIncMemo(newMemo);
                System.out.println("[" + userId + "]의 입금 내역이 수정되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 입금 내역을 찾을 수 없습니다.");
    }

    // 특정 유저의 총 입금 금액 반환
    public int incGetTotal(String userId) {
        List<IncomeDTO> incomeList = userIncomeMap.get(userId);
        if (incomeList == null) {
            System.out.println("해당 유저의 입금 내역이 없습니다.");
            return 0;
        }

        int total = 0;
        for (IncomeDTO income : incomeList) {
            total += income.getIncMoney();
        }
        return total;
    }

    // 특정 유저의 저장된 모든 입금 내역 출력
    public void printAllIncomes(String userId) {
        List<IncomeDTO> incomeList = userIncomeMap.get(userId);
        if (incomeList == null || incomeList.isEmpty()) {
            System.out.println("[" + userId + "]의 입금 내역이 없습니다.");
            return;
        }

        System.out.println("[" + userId + "]의 입금 내역:");
        for (IncomeDTO income : incomeList) {
            System.out.println(income);
        }
    }
}
