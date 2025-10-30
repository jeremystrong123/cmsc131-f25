package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private CheckingAccount acc;

    @BeforeEach
    void createAccount() {
        acc = new CheckingAccount("ABCDEF", "Bob", 99.99);
    }

    @Test
    void constructorThrowsForInvalidID() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {new CheckingAccount(null, "Bob", 99.99);}
        );
        assertEquals(
            "Account ID must be a string.",
            e.getMessage()
        );
    }

    @Test
    void constructorThrowsForInvalidOwner() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {new CheckingAccount("ABCDEF", null, 99.99);}
        );
        assertEquals(
            "Account Owner must be a string.",
            e.getMessage()
        );
    }

    @Test
    void getIDTest () {
        assertEquals("ABCDEF", acc.getID()); 
    }

    @Test
    void getNameTest () {
        assertEquals("Bob", acc.getName()); 
    }

    @Test
    void getBalanceTest () {
        assertEquals(99.99, acc.getBalance()); 
    }

    @Test
    void getTypeTest() {
        assertEquals(AccountType.CHECKING, acc.getType());
    }

    @Test
    void toCSVCreatesCSV() {
        assertEquals("CHECKING,ABCDEF,Bob,99.99", acc.toCSV(acc));
    }

    @Test
    void createAccountCreatesFromCSV() {
        Account a = Account.createAccountFromCSV("savings,GHIJKL,Jim,100.0");
        assertEquals("SAVINGS,GHIJKL,Jim,100.0", a.toCSV(a));
    }

    @Test
    void factoryThrowsNullInput() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {Account.createAccountFromCSV(null);}
        );
        assertEquals(
            "input values cannot be null",
            e.getMessage()
        );
    }

    @Test
    void creditAddsToAccount() {
        acc.credit(10);
        assertEquals(109.99, acc.getBalance());
    }

    @Test
    void creditThrowsForInvalidAmount() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {acc.credit(-1);}
        );
        assertEquals(
            "Amount to be credited must be a positive number greater than zero.",
            e.getMessage()
        );
    }

    @Test
    void debitRemovesFromAccount() {
        acc.debit(10);
        assertEquals(89.99, acc.getBalance());
    }

    @Test
    void debitThrowsForInvalidAmount() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {acc.debit(-1);}
        );
        assertEquals(
            "Amount to be debited must be a positive number greater than zero.",
            e.getMessage()
        );
    }

    @Test
    void debitThrowsForNSF() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {acc.debit(999);}
        );
        assertEquals(
            "Non-suffienct funds, account balance cannot be withdrawn over the current balance.",
            e.getMessage()
        );
    }
}   