public class Account{
    //instance variables
    private String accountID;
    private String accountOwner;
    private double currentBalance;
    private String accountType; //make enum later

    //constructors
    public Account(String ID, String Owner, double Balance, String Type) {
        if (ID == null) {
            throw new IllegalArgumentException(
                "Account ID must be a string."
            )
        }
        else if (Owner == null) {
            throw new IllegalArgumentException(
                "Account Owner must be a string."
            )
        }
        else if (Type != (CHECKING || SAVINGS)) {
            throw new IllegalArgumentException(
                "Account type must be inputted as 'CHECKING' or 'SAVINGS'."
            )
        }
        accountID = ID;
        accountOwner = Owner;
        currentBalance = Balance;
        accountType = Type;
    }

    //methods
    //accessor methods
    public String getID() {
        return accountID;
    }

    public String getName() {
        return accountOwner;
    }

    public double getBalance() {
        return currentBalance;
    }
}