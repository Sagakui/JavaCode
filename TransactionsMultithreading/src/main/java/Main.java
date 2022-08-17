import java.util.HashMap;

public class Main {

    private static final HashMap <String, Account> ACC_LIST = new HashMap<>();
    private static final Bank BANK = new Bank(ACC_LIST);

    public static void main(String[] args) throws InterruptedException {
        ACC_LIST.put("1", new Account(60000L, "1"));
        ACC_LIST.put("2", new Account(40000L, "2"));
        ACC_LIST.put("3", new Account(70000L, "3"));
        ACC_LIST.put("4", new Account(10000L, "4 blocked"));

        newThread("1", "2", 10000L);
        newThread("1", "4", 10000L);
        newThread("2", "3", 55000L);
        System.out.println("Баланс: " + BANK.getBalance("1"));
        System.out.println("Сумма денег на всех аккаунтах: " + BANK.getSumAllAccounts(ACC_LIST));
    }

    public static void newThread (String fromAcc, String toAcc, long amount) {
        ThreadRun threadRun = new ThreadRun(fromAcc, toAcc, amount, ACC_LIST);
        new Thread(threadRun).start();
    }
}
