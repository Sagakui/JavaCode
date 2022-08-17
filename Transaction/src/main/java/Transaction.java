public class Transaction {

    private String transactionInfo;
    private double expense;
    private double income;

    public Transaction(String transactionInfo, Double income, Double expense) {
        this.transactionInfo = transactionInfo;
        this.income = income;
        this.expense = expense;
    }

    public Transaction(String transactionInfo, Double expense) {
        this.transactionInfo = transactionInfo;
        this.expense = expense;
    }

    public String getTransactionInfo() {
        return transactionInfo;
    }

    public double getExpense() {
        return expense;
    }

    public double getIncome() {
        return income;
    }
}
