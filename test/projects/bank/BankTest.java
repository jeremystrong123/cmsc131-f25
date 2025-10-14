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
    void addThrowsForNull() {
        Exception e = assertThrows(
        IllegalArgumentException.class,
        () -> {bank.addAccount(null);}
        );
        assertEquals(
            "Account cannot be null.",
            e.getMessage()
        );
    }

    @Test
    void accountsAreAdded() {
        assertEquals(true, bank.addAccount(acc));
    }

    @Test
    void addFailIsFalse() {
        for (int i=0; i<1001; i++) {
            bank.addAccount(new Account ("123", "name", 0, "checking"));
        }
        assertEquals(false, bank.addAccount(acc));
    }

    @Test
    void findThrowsForNull() {
        Exception e = assertThrows(
        IllegalArgumentException.class,
        () -> {bank.findAccountByID(null);}
        );
        assertEquals(
            "ID must be a string.",
            e.getMessage()
        );
    }

    @Test
    void findsAccountFromID() {
        assertEquals(0, bank.findAccountByID("ABCDEF"));
    }

    @Test
    void findFailIsFalse() {
        assertEquals(-1, bank.findAccountByID("123"));
    }

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