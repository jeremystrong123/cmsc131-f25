package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TransactionTest {
    
    private Account acc;
    private Transaction depo, with;

    @BeforeEach
    void setUp() {
        acc = new CheckingAccount("ABCDEF", "Bob", 99.99);
        depo = new Deposit("ABCDEF", 3.50);
        with = new Withdrawal("GHIJKL", 5.59);
    }

    @Test
    void testConstructorThrowsForNull() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {new Deposit(null, 99.99);}
        );
        assertEquals(
            "Account ID must be a string.",
            e.getMessage()
        );
    }

    @Test
    void factoryThrowsForNull() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {Transaction.factoryFromCSV(null);}
        );
        assertEquals(
            "input values cannot be null",
            e.getMessage()
        );
    }

    @Test
    void factoryMethodWorks() {
        Transaction t = Transaction.factoryFromCSV("deposit,ABCDEF,10.0");
        assertEquals("ABCDEF", t.getID());
    }

    /*@Test
    void testValidateDeposit() {
        // TODO
    }

    @Test
    void testValidateWithdrawal() {
        // TODO
    }*/

    @Test
    void testExecuteDeposit() {
        depo.execute(acc);
        assertEquals(103.49, acc.getBalance());
    }

    @Test
    void testExecuteWithdrawal() {
        with.execute(acc);
        assertEquals(94.40, acc.getBalance());
    }
}
