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
        bank.addAccount(acc);
    }

    @Test
    void accountsAreAdded() {
        assertEquals(true, bank.addAccount(acc));
    }

    // TODO test add fail returns false

    // TODO correct return value
    @Test
    void findsAccountFromID() {
        assertEquals(acc, bank.findAccountByID("ABCDEF"));
    }

    // TODO test find fail returns correct value

    @Test
    void findsAccountFromName() {
        Account[] accountsNamed = bank.findAccountsByName("Bob");
        assertEquals(acc, accountsNamed[0]);
    }

    @Test
    void getCountTest() {
        assertEquals(1, bank.getCount());
    }

}