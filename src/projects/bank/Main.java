package projects.bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        phase1();
        phase2();
    }

    public static void phase1() {
        String logName = "phase1.log";
        try {
            FileWriter writer = new FileWriter(new File(logName));

            Account acct = new Account(
                "id1",
                "Owner Name",
                1.0,
                "SAVINGS"
            );
            
            writer.write(
                String.format(
                    "Account setup: acct id=%s, owner=%s, balance=%f",
                    acct.getID(),
                    acct.getName(),
                    acct.getBalance()
                ) + System.lineSeparator()
            );

            // uncomment lines 36, 41 and remove lines 37, 42 after correcting return value
            Bank bank = new Bank();
            int numAccounts0 = bank.getCount();
            // int findAcct0 = bank.findAccountByID(acct.getID());
            int findAcct0 = -1;

            boolean addResult = bank.addAccount(acct);
            int numAccounts1 = bank.getCount();
            // int findAcct1 = bank.findAccountByID(acct.getID());
            int findAcct1 = 0;

            writer.write(
                String.format(
                    "Bank init: getCount=%d, find=%d",
                    numAccounts0, 
                    findAcct0
                ) + System.lineSeparator()
            );

            writer.write(
                String.format(
                    "After add: result=%b, getCount=%d, find=%d",
                    addResult,
                    numAccounts1, 
                    findAcct1
                ) + System.lineSeparator()
            );

            writer.close();
        } catch (IOException e) { e.printStackTrace(); }

    }

    public static void phase2() {
        String accountsFilename = "data/accounts.csv";
        Bank bank = new Bank();
        boolean result = bank.loadAccounts(accountsFilename);

        System.out.println("Result of loading account: " + result);
        System.out.println("Number of accounts: " + bank.getCount());

        String outputFilename = "data/phase2.csv";
        bank.writeAccounts(outputFilename);
    }
}

