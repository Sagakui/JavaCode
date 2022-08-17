import java.util.Map;

public class ThreadRun implements Runnable {

    private final String accFrom;
    private final String accTo;
    private final long amount;
    private final Map<String, Account> accounts;

    Bank bank = new Bank(null);

    public ThreadRun(String accFrom, String accTo, long amount, Map<String, Account> accounts) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.amount = amount;
        this.accounts = accounts;
    }

    @Override
    public void run() {
        bank.synchronizedTransfer(accFrom,accTo, amount, accounts);
    }
}