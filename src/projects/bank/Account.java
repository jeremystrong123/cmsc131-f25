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
     * @param ID ID for the account, must be a string.
     * 
     * @param Owner Name of account owner, must be a string.
     * 
     * @param Balance Current balance of the account.
     * 
     * @param Type The type of account being checking or savings, must be input as one of these two.
     * 
     * @throws IllegalArgumentException If ID or Owner is not a String and if the string for account type is not 'checking' or 'savings'.
    */
    public Account(String ID, String Owner, double Balance, String Type) {
        if (ID == null) {
            throw new IllegalArgumentException(
                "Account ID must be a string."
            ); 
        }
        else if (Owner == null) {
            throw new IllegalArgumentException(
                "Account Owner must be a string."
            ); 
        }
        else if (!Type.toUpperCase().equals("CHECKING") && !Type.toUpperCase().equals("SAVINGS")) {
            throw new IllegalArgumentException(
                "Account type must be inputted as 'CHECKING' or 'SAVINGS'."
            ); 
        }
        accountID = ID;
        accountOwner = Owner;
        currentBalance = Balance;
        accountType = Type;
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
}