package projects.bank;

abstract class Transaction {

    //Instance Variables
    private final String accountID;
    private final double amount;

    //abstract methods used by subclasses
    abstract void execute(Account account, Audit audit);

    abstract boolean validate(Account account, Audit audit);

    //constructor
    /** 
     * Constructs an Account with provided values
     * 
     * @param id ID for the account, must be a string.
     * 
     * @param value Value of the transaction amount.
     * 
     * @throws IllegalArgumentException If ID is not a String.
    */
    protected Transaction(String id, double value) {
        if (id == null) {
            throw new IllegalArgumentException(
                "Account ID must be a string."
            );
        }
        else if (value <= 0) {
            throw new IllegalArgumentException(
                "Value must be a positive number greater than zero."
            ); 
        }
        accountID = id;
        amount = value;
    }

    /**
     * Creates a Transaction object from a CSV line.
     * 
     * @param values line from CSV file in format "type,id,amount".
     * 
     * @return Transaction object created from CSV line.
     * 
     * @throws IllegalArgumentException if inputted string is null.
     */
    protected static Transaction factoryFromCSV(String values) {
        if (values == null) {
            throw new IllegalArgumentException("input values cannot be null");
        }
        String[] tokens = values.split(",");
        TransactionType t = TransactionType.valueOf(tokens[0].toUpperCase());
        double d = Double.parseDouble(tokens[2]);
        if (t == TransactionType.DEPOSIT) {
            return new Deposit(tokens[1], d);
        }
        else {
            return new Withdrawal(tokens[1], d);
        }
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
     * Accessor for amount.
     * @return the value of the transaction amount.
     */
    public double getAmount() {
        return amount;
    }
}
