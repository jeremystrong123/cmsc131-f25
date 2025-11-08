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
    void constructorThrowsForInvalidValue() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {new Deposit("ABCDEF", -3);}
        );
        assertEquals(
            "Value must be a positive number greater than zero.",
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
        assertEquals(10.0, t.getAmount());
    }

    @Test
    void testDepositValidateSuccess() {
        assertEquals(true, depo.validate(acc));
    }

    @Test
    void testDepositValidateFailure() {
        Deposit depo2 = new Deposit("GHIJKL", 5.59);
        assertEquals(false, depo2.validate(acc));
    }

    @Test
    void testWithdrawalValidateSuccess() {
        Withdrawal with2 = new Withdrawal("ABCDEF", 3.50);
        assertEquals(true, with2.validate(acc));
    }

    @Test
    void testWithdrawalValidateFailure() {
        assertEquals(false, with.validate(acc));
    }

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
