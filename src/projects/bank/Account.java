public class Account{
    //instance variables
    private String accountID;
    private String accountOwner;
    private double currentBalance;
    private String accountType; //make enum later

    //constructors TODO javadoc
    public Account(String ID, String Owner, double Balance, String Type) {
        if (ID == null) {
            throw new IllegalArgumentException(
                "Account ID must be a string."
            ) // compile error
        }
        else if (Owner == null) {
            throw new IllegalArgumentException(
                "Account Owner must be a string."
            ) // compile error
        }
        else if (Type != (CHECKING || SAVINGS)) { // compile error
            throw new IllegalArgumentException(
                "Account type must be inputted as 'CHECKING' or 'SAVINGS'."
            ) // compile error
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