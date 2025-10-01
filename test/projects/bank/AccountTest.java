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

    @Test
    void constructorThrowsForInvalidID() {
        Exception e = assertThrows(
        IllegalArgumentException.class
        () -> {new Account(10, "Bob", 99.99, "CHECKING");}
        );
        assertEquals(
            "Account ID must be a string.",
            e.getMessage()
        );
    }

    @Test
    void getIDTest () {
        assertEquals("ABCDEF", acc.getID())
    }

    @Test
    void getNameTest () {
        assertEquals("Bob", acc.getName())
    }

    @Test
    void getBalanceTest () {
        assertEquals(99.99, acc.getBalance())
    }
}   