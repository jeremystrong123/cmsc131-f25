/** TODO / comments
 * 
 * processTransactions
 * don't return -1. this breaks the interpretation of the returned value as the number of transactions that were processed.
 * when you declare acc, you're assuming that findAccountByID(t.getID()) != -1. but that could happen, and your code needs changing to handle this case.
 * double-check that your transaction constructor/factory and account constructor/factory cannot possibly return null. then clean up that unnecessary null checking logic.
 */
package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank{
    //instance variables
    private Account[] accounts;
    private int count = 0;

    //constructors
     /** 
     * Initializes the bank's accounts array to have 1000 slots for accounts.
    */
    public Bank() {
        accounts = new Account[1000];
    }

    //methods
    /** 
     * Adds an account to the accounts array in the first available slot and increments count.
     * 
     * @param a Account to be added to the accounts array.
     * 
     * @return true or false to tell whether or not an account was added to the array.
     * 
     * @throws IllegalArgumentException if given Account a is null
    */
    public boolean addAccount(Account a) {
        if (a == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        if (count < accounts.length) {
            accounts[count] = a;
            count++;
            return true;
        }
        return false;
    }

    /** 
     * Finds an account within the accounts array by searching based on a given ID.
     * 
     * @param id ID of the account that you want to find within the array.
     * 
     * @return index within the accounts array of the account with the given ID or -1 if no account was found.
     * 
     * @throws IllegalArgumentException if id is null
    */
    public int findAccountByID(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must be a string.");
        }
        int currentIndex = 0;
        while (currentIndex < count) {
            if (id.equals(accounts[currentIndex].getID())) {
                return currentIndex;
            }
            currentIndex++;
        }
        return -1;
    }

    /** 
     * Finds all accounts within the accounts array that match a given name.
     * 
     * @param name name associated with the account that you want to find.
     * 
     * @return an array of all accounts that match the given name.
    */
    public Account[] findAccountsByName(String name) {
        int currentIndex = 0;
        int foundAccounts = 0;
        Account[] accountsNamed = new Account[1000];
        while (currentIndex < count) {
            if (name.equals(accounts[currentIndex].getName())) {
                accountsNamed[foundAccounts] = accounts[currentIndex];
                foundAccounts++;
            }
            currentIndex++;
        }
        return accountsNamed;
    }

    /** 
     * Returns the current number of accounts within the accounts array (accessor method).
     * 
     * @return current count value.
    */
    public int getCount() {
        return count;
    }

    /** 
     * Loads accounts from a CSV file into the bank's accounts array.
     * 
     * @param file file path for the CSV file of accounts to be loaded into the bank.
    */
    public boolean loadAccounts(String file) {
        boolean result = true;
        File accountsFile = new File(file);
        Scanner scan;
        try {
            scan = new Scanner(accountsFile);
            while (scan.hasNextLine()) {
                String current = scan.nextLine();
                Account a = Account.createAccountFromCSV(current);
                addAccount(a);
                System.out.println("break"); // remove if not single-stepping
            }
            scan.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /** 
     * Writes all accounts from the bank's accounts array into a CSV file.
     * 
     * @param file file path for the CSV file that the account strings will be to be put into.
     * 
     * @return true if the operation suceeded, false if it did not.
    */
    public boolean writeAccounts(String file) {
        boolean result = true;
        File accountsFile = new File(file);
        FileWriter writer;
        try {
            writer = new FileWriter(accountsFile);
            for (int i = 0; i<count; i++) {
                String csvLine = accounts[i].toCSV(accounts[i]);
                System.out.println("break"); // ok to remove
                writer.write(csvLine + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    /**
     * Processes a list of transactions from a file.
     * 
     * @param file file path for the file holding all of the transactions.
     * 
     * @return 0 if successful, -1 if fail.
     */
    public int processTransactions(String file) {
        int result = 0;
        File transactionsFile = new File(file);
        Scanner scan;
        try {
            scan = new Scanner(transactionsFile);
            while (scan.hasNextLine()) {
                String current = scan.nextLine();
                Transaction t = Transaction.factoryFromCSV(current);
                if (t != null) {
                    Account acc = accounts[this.findAccountByID(t.getID())];
                    if (acc == null) {
                        System.out.println("Account with given ID does not exist.");
                    }
                    else {
                        t.execute(acc);
                    }
                    
                }
            }
            scan.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }
}