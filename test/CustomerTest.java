import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private Customer customer;
    private CheckingAccount account;

    @BeforeEach
    void setUp() {
        customer = new Customer("Cedric", "001");
        account = new CheckingAccount("BE001");
    }

    @Test
    void testAddAccount() {
        customer.addAccount(account);
        assertEquals(1, customer.getAccounts().size());
    }

    @Test
    void testAddNullAccountIgnored() {
        customer.addAccount(null);
        assertEquals(0, customer.getAccounts().size());
    }

    @Test
    void testGetName() {
        assertEquals("Cedric", customer.getName());
    }
}
