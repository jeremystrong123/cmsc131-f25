package projects.bank;

abstract class Account{
    //instance variables
    private final String accountID;
    private final String accountOwner;
    private double currentBalance;
    /**
     * Accessor for account type.
     */
    abstract AccountType getType();

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
     * @throws IllegalArgumentException If ID or Owner is not a String.
    */
    protected Account(String id, String owner, double balance) {
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
        accountID = id;
        accountOwner = owner;
        currentBalance = balance;
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
     * Creates a CSV line from all values of an Account object.
     * 
     * @param a Account to be turned into a CSV line.
     * 
     * @return the line for an Acount object to be added to a CSV file.
     */
    public String toCSV(Account a) {
        String type = a.getType().toString();
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
     * @param values line from CSV file in format "type,id,name,balance".
     * 
     * @return Account object created from CSV line.
     * 
     * @throws IllegalArgumentException if inputted string is null.
     */
    public static Account createAccountFromCSV(String values) {
        if (values == null) {
            throw new IllegalArgumentException("input values cannot be null");
        }
        String[] tokens = values.split(",");
        AccountType t = AccountType.valueOf(tokens[0].toUpperCase());
        double d = Double.parseDouble(tokens[3]);
        if (t == AccountType.CHECKING) {
            return new CheckingAccount(tokens[1], tokens[2], d);
        }
        else {
            return new SavingsAccount(tokens[1], tokens[2], d);
        }
    }

    /**
     * Rounds a given double to 2 decimal places.
     * 
     * @param d double to be rounded.
     * 
     * @return rounded form of the given double.
     */
    public double roundToTwoDecimalPlaces(Double d) {
        Double answer = Math.floor(d * 100) / 100;
        return answer;
    }

    /**
     * Credits an account with a given transaction amount.
     * 
     * @param amount amount to be credited to the account.
     * 
     * @throws IllegalArgumentException if amount to be credited is negative or zero.
     */
    public void credit(double amount) {
        double newBalance = 0;
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount to be credited must be a positive number greater than zero."
            ); 
        }
        newBalance = (currentBalance + amount);
        newBalance = roundToTwoDecimalPlaces(newBalance);
        currentBalance = newBalance;
    }

    /**
     * Debits from an account with a given transaction amount.
     * 
     * @param amount amount to be debited from the account.
     * 
     * @throws IllegalArgumentException if amount to be debited is negative or zero or if the account does not have suffient funds for the transaction.
     */
    public void debit(double amount) {
        double newBalance = 0;
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount to be debited must be a positive number greater than zero."
            ); 
        }
        else if ((currentBalance - amount) < 0) {
            throw new IllegalArgumentException(
                "Non-suffienct funds, account balance cannot be withdrawn over the current balance."
            ); 
        }
        newBalance = (currentBalance - amount);
        newBalance = roundToTwoDecimalPlaces(newBalance);
        currentBalance = newBalance;
    }
}