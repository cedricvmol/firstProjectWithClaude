import domain.CheckingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {

    private CheckingAccount account;

    @BeforeEach
    void setUp(){
        account = new CheckingAccount("BE003");
    }

    @Test
    void testWithdrawWithinOverdraft(){
        assertTrue(account.withdraw(400.00));
        assertEquals(-400.00, account.getBalance());
    }

    @Test
    void testWithdrawBeyondOverdraft(){
        assertFalse(account.withdraw(600.00));
        assertEquals(0.00,account.getBalance());
    }

    @Test
    void testGetType(){
        assertEquals("Checkings",account.getType());
    }


}
