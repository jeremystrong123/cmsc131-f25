package projects.bank;

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
}