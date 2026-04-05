import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    private SavingAccount account;

    @BeforeEach
    void setUp(){
        account = new SavingAccount("BE002");
    }

    @Test
    void testWithdrawSufficientFunds(){
        account.deposit(100.0);
        assertTrue(account.withdraw(50.00));
        assertEquals(50.00,account.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds(){
        account.deposit(10.00);
        assertFalse(account.withdraw(50.00));
    }

    @Test
    void testGetType(){
        assertEquals("Savings", account.getType());
    }
}
