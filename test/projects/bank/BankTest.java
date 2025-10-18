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

    @Test
    void loadAccountsTest() {
        bank.loadAccounts("data/accounts.csv");
        assertEquals(393, bank.getCount());
        assertEquals(369, bank.findAccountByID("do458480"));
        assertEquals(243, bank.findAccountByID("gq777329"));
    }

    //remove all values from "test-accounts-for-file-save.csv" before running test
    @Test
    void writeAccountsTest() {
        Bank bank2 = new Bank();
        Account a = Account.createAccountFromCSV("savings,wz240833,Anna Gomez,8111.00");
        Account b = Account.createAccountFromCSV("checking,hr108256,Anna Gomez,1715.18");
        Account c = Account.createAccountFromCSV("savings,hr676528,Anna Rodriguez,6738.80");
        Account d = Account.createAccountFromCSV("checking,tx835396,Anna Rodriguez,2593.18");
        Account e = Account.createAccountFromCSV("savings,mi131700,Anna Hernandez,7260.84");
        bank2.addAccount(a);
        bank2.addAccount(b);
        bank2.addAccount(c);
        bank2.addAccount(d);
        bank2.addAccount(e);
        bank2.writeAccounts("data/test-accounts-for-file-save.csv");
        Bank bank3 = new Bank();
        bank3.loadAccounts("data/test-accounts-for-file-save.csv");
        assertEquals(5, bank3.getCount());
        assertEquals(0, bank3.findAccountByID("wz240833"));
        assertEquals(2, bank3.findAccountByID("hr676528"));
        assertEquals(4, bank3.findAccountByID("mi131700"));
    }

    // TODO test failure modes for loadAccounts, writeAccounts
}