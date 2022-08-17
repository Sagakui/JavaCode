import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class TestBank extends TestCase {

    public final Map <String,Account> accounts = new HashMap<>();
    public Bank bank = new Bank(accounts);

    @Override
    protected void setUp() {
        accounts.put("1", new Account(60000L, "1"));
        accounts.put("2", new Account(40000L, "2"));
        accounts.put("3", new Account(70000L, "3"));
        accounts.put("4", new Account(10000L, "4 blocked"));
        accounts.put("5", new Account(100000L, "5"));

        ThreadRun threadRun1 = new ThreadRun("1","2", 10000L,accounts);
        new Thread(threadRun1).start();

        ThreadRun threadRun2 = new ThreadRun("1","4", 10000L,accounts);
        new Thread(threadRun2).start();

        ThreadRun threadRun3 = new ThreadRun("3", "5", 55000L,accounts);
        new Thread(threadRun3).start();
    }

    public void testFromAllAccount() throws InterruptedException {
        assertEquals(280000L, bank.getSumAllAccounts(accounts));
    }

    public synchronized void  testBalance() throws InterruptedException {
        assertEquals(50000L, bank.getBalance("2"));
    }

    public void testTransferToBlockedAccount() {
        assertEquals(10000L, accounts.get("4").getMoney());
    }

    public synchronized void testTransfer() throws InterruptedException {
        assertEquals(50000, bank.getBalance("1"));
    }

    public void testToBlocked() throws InterruptedException {
        synchronized (accounts) {
            Thread.sleep(1000);
            assertEquals("3 blocked", accounts.get("3").getAccNumber());
        }
    }
}
