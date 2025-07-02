package classes;

public class ExpenseDTO {
    private int expMoney;
    private String expDate;
    private String expCategory;
    private String expMemo;
    private String expItem;
    private int expId;

    // Constructor
    public ExpenseDTO(int expMoney, String expDate, String expCategory, String expMemo, String expItem, int expId) {
        this.expMoney = expMoney;
        this.expDate = expDate;
        this.expCategory = expCategory;
        this.expMemo = expMemo;
        this.expItem = expItem;
        this.expId = expId;
    }

    // Getter and Setter methods
    public int getExpMoney() {
        return expMoney;
    }

    public void setExpMoney(int expMoney) {
        this.expMoney = expMoney;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getExpCategory() {
        return expCategory;
    }

    public void setExpCategory(String expCategory) {
        this.expCategory = expCategory;
    }

    public String getExpMemo() {
        return expMemo;
    }

    public void setExpMemo(String expMemo) {
        this.expMemo = expMemo;
    }

    public String getExpItem() {
        return expItem;
    }

    public void setExpItem(String expItem) {
        this.expItem = expItem;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

	public String toString() {
		return "[" + expId + "] " + "날짜: " + expDate + ", 항목: " + expItem + ", 금액: " + expMoney + "원, 카테고리: " + expCategory + ", 메모: " + expMemo;
	}

}
