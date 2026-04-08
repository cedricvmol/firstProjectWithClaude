import domain.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    private SavingAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingAccount("BE001");
    }

    @Test
    void testInitialBalanceIsZero() {
        assertEquals(0.00, account.getBalance());
    }

    @Test
    void testDepositIncreaseBalance() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testNegativeDepositIgnored() {
        account.deposit(-100.00);
        assertEquals(0, account.getBalance());
    }
}

