import java.util.Map;
import java.util.Random;

public class Bank {

    private final Random random = new Random();
    private final Map<String, Account> accounts;
    private static final long TRANSACTION_MAX = 50000;

    public Bank(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) {
        return random.nextBoolean();
    }

     /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */

     public void transfer(String fromAccountNum, String toAccountNum, long amount, Map<String, Account> accountsList) {
         if (accountsList.get(fromAccountNum).getAccNumber().equals(fromAccountNum + " blocked") ||
                 accountsList.get(toAccountNum).getAccNumber().equals(toAccountNum + " blocked")) {
             System.out.println("Ваш аккаунт или аккаунт для перевода заблокирован");
         }
         else if (accountsList.get(fromAccountNum).getMoney() >= amount) {
             accountsList.put(fromAccountNum, new Account(accountsList.get(fromAccountNum).getMoney() - amount, fromAccountNum));
             accountsList.put(toAccountNum, new Account(accountsList.get(toAccountNum).getMoney() + amount, toAccountNum));
             System.out.println("Перевод выполнен");
         } else {
             System.out.println("Недостаточно средств");
         }
         try {
             if (amount > TRANSACTION_MAX && isFraud(toAccountNum, fromAccountNum, amount)) {
                 accountsList.put(fromAccountNum, new Account(accountsList.get(fromAccountNum).getMoney(),
                         fromAccountNum + " blocked"));
                 accountsList.put(toAccountNum, new Account(accountsList.get(toAccountNum).getMoney(),
                         toAccountNum + " blocked"));
                 System.out.println("Аккаунт заблокирован");
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         }
     }

     public void synchronizedTransfer (String fromAccountNum, String toAccountNum, long amount, Map<String, Account> accountsList) {
         int fromHashCode = System.identityHashCode(fromAccountNum);
         int toHashCode = System.identityHashCode(toAccountNum);

         if (fromHashCode < toHashCode) {
             synchronized (fromAccountNum) {
                 synchronized (toAccountNum) {
                     transfer(fromAccountNum, toAccountNum, amount, accountsList);
                 }
             }
         } else if (fromHashCode > toHashCode) {
             synchronized (toAccountNum) {
                 synchronized (fromAccountNum) {
                     transfer(fromAccountNum, toAccountNum, amount, accountsList);
                 }
             }
         } else {
             synchronized (Long.valueOf(amount)) {
                 synchronized (fromAccountNum) {
                     synchronized (toAccountNum) {
                         transfer(fromAccountNum, toAccountNum, amount, accountsList);
                     }
                 }
             }
         }
     }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public synchronized long getBalance(String accountNum) throws InterruptedException {
        Thread.sleep(100);
        return accounts.get(accountNum).getMoney();
    }

    public synchronized long getSumAllAccounts(Map<String, Account> accountsList) throws InterruptedException {
        long sum = 0;
        for (String key : accountsList.keySet()) {
            sum += accountsList.get(key).getMoney();
        }
        Thread.sleep(100);
        return sum;
    }
}
