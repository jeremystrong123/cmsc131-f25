package projects.bank;

public class Account{
    //instance variables
    private String accountID;
    private String accountOwner;
    private double currentBalance;
    private String accountType;

    //constructors
    /** 
     * Constructs an Account with provided values
     * 
     * @param id ID for the account, must be a string.
     * 
     * @param owner Name of account owner, must be a string.
     * 
     * @param balance Current balance of the account.
     * 
     * @param type The type of account being checking or savings, must be input as one of these two.
     * 
     * @throws IllegalArgumentException If ID or Owner is not a String and if the string for account type is not 'checking' or 'savings'.
    */
    public Account(String id, String owner, double balance, String type) {
        if (id == null) {
            throw new IllegalArgumentException(
                "Account ID must be a string."
            ); 
        }
        else if (owner == null) {
            throw new IllegalArgumentException(
                "Account Owner must be a string."
            ); 
        }
        else if (!type.toUpperCase().equals("CHECKING") && !type.toUpperCase().equals("SAVINGS")) {
            throw new IllegalArgumentException(
                "Account type must be inputted as 'CHECKING' or 'SAVINGS'."
            ); 
        }
        accountID = id;
        accountOwner = owner;
        currentBalance = balance;
        accountType = type;
    }

    //methods
    //accessor methods
    /**
     * Accessor for ID.
     * @return the ID associated with the account.
     */
    public String getID() {
        return accountID;
    }

    /**
     * Accessor for owner name.
     * @return name of the account owner.
     */
    public String getName() {
        return accountOwner;
    }

    /**
     * Accessor for account balance.
     * @return the current balance in the acount as a double.
     */
    public double getBalance() {
        return currentBalance;
    }

    /**
     * Accessor for account type.
     * @return the type of the account.
     */
    public String getType() {
        return accountType;
    }

    /**
     * Creates a CSV line from all values of an Account object.
     * 
     * @param a Account to be turned into a CSV line.
     * 
     * @return the line for an Acount object to be added to a CSV file.
     */
    public String toCSV(Account a) {
        String type = a.getType().toUpperCase();
        String id = a.getID();
        String name = a.getName();
        String balance = Double.toString(a.getBalance());
        String[] tokens = {type, id, name, balance};
        String csv = String.join(",", tokens);
        return csv;
    }

    /**
     * Creates an Account object from a CSV line.
     * 
     * @param values line from CSV file.
     * TODO be make clear your assumptions about line format (lines 107-108)
     * 
     * @return Account object created from CSV line.
     */
    public static Account createAccountFromCSV(String values) {
        // TODO validate input
        String[] tokens = values.split(",");
        double d = Double.parseDouble(tokens[3]);
        Account a = new Account(tokens[1], tokens[2], d, tokens[0]);
        return a;
    }
}