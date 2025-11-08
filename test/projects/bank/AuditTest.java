package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

public class AuditTest {

    private String file;
    private Audit au;

    @BeforeEach
    void setUp() {
        file = "test/audit.log";
        try{
            au = new Audit(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void accountDoesNotExistTest() {
        Transaction t = new Withdrawal("ABC", 10.0);
        au.accountDoesNotExist(t);
        au.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(file));

            assertEquals(true, scan.hasNextLine());
            String line = scan.nextLine();
            assertEquals(true, line.contains("ERROR account does not exist: " + t.toString()));

            assertEquals(false, scan.hasNextLine());
            scan.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void nonSufficientFundsTest() {
        Transaction t = new Withdrawal("ABC", 10.0);
        Account a = new CheckingAccount("ABC", "Bob", 5.0);
        au.nonSufficientFunds(t, a);
        au.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(file));

            assertEquals(true, scan.hasNextLine());
            String line = scan.nextLine();
            assertEquals(true, line.contains("ERROR non-sufficient funds available: " + t.toString()));
            assertEquals(true, line.contains("current balance: " + String.valueOf(a.getBalance())));

            assertEquals(false, scan.hasNextLine());
            scan.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void recordTransactionTest() {
        Transaction t = new Withdrawal("ABC", 10.0);
        Account a = new CheckingAccount("ABC", "Bob", 50.0);
        au.recordTransaction(t, a);
        au.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(file));

            assertEquals(true, scan.hasNextLine());
            String line = scan.nextLine();
            assertEquals(true, line.contains("INFO: " + t.toString()));
            assertEquals(true, line.contains("new balance: " + String.valueOf(a.getBalance())));

            assertEquals(false, scan.hasNextLine());
            scan.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
