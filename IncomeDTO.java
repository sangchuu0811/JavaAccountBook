package classes;

public class IncomeDTO {
    private int incMoney;
    private String incDate;
    private String incCategory;
    private String incMemo;
    private String incItem;
    private int incId;

    // Constructor
    public IncomeDTO(int incMoney, String incDate, String incCategory, String incMemo, String incItem, int incId) {
        this.incMoney = incMoney;
        this.incDate = incDate;
        this.incCategory = incCategory;
        this.incMemo = incMemo;
        this.incItem = incItem;
        this.incId = incId;
    }

    // Getter and Setter methods
    public int getIncMoney() { return incMoney; }
    public void setIncMoney(int incMoney) { this.incMoney = incMoney; }

    public String getIncDate() { return incDate; }
    public void setIncDate(String incDate) { this.incDate = incDate; }

    public String getIncCategory() { return incCategory; }
    public void setIncCategory(String incCategory) { this.incCategory = incCategory; }

    public String getIncMemo() { return incMemo; }
    public void setIncMemo(String incMemo) { this.incMemo = incMemo; }

    public String getIncItem() { return incItem; }
    public void setIncItem(String incItem) { this.incItem = incItem; }

    public int getIncId() { return incId; }
    public void setIncId(int incId) { this.incId = incId; }

    @Override
    public String toString() {
        return "[" + incId + "] " + "날짜: " + incDate + ", 항목: " + incItem + ", 금액: " + incMoney + "원, 카테고리: " + incCategory + ", 메모: " + incMemo;
    }
}
