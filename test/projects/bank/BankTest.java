package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
    private Bank bank;
    private Account acc;

    @BeforeEach
    void createBankAndAccount() {
        bank = new Bank();
        acc = new Account("ABCDEF", "Bob", 99.99, "CHECKING");
    }

    @Test
    void accountsAreAdded() {
        assertEquals(bank.addAccount(acc), true);
    }

}