package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account acc;

    @BeforeEach
    void createAccount() {
        acc = new Account("ABCDEF", "Bob", 99.99, "CHECKING");
    }

    // TODO rewrite so this tests both id == null, owner == null
    @Test
    void constructorThrowsForInvalidID() {
        Exception e = assertThrows(
        IllegalArgumentException.class  // compile error
        () -> {new Account(10, "Bob", 99.99, "CHECKING");}
        );
        assertEquals(
            "Account ID must be a string.",
            e.getMessage()
        );
    }

    @Test
    void getIDTest () {
        assertEquals("ABCDEF", acc.getID())  // compile error
    }

    @Test
    void getNameTest () {
        assertEquals("Bob", acc.getName())  // compile error
    }

    @Test
    void getBalanceTest () {
        assertEquals(99.99, acc.getBalance())  // compile error
    }
}   