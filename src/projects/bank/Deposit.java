package projects.bank;

public class Deposit extends Transaction {

    //constructor, works same as parent class.
    public Deposit(String id, double value) {
        super(id, value);
    }
    
    /**
     * Credits the given account with the amount of the transaction.
     * 
     * @param acc account to be credited.
     */
    @Override
    public void execute(Account acc) {
        acc.credit(this.getAmount());
    }

    /**
     * Validates that a given account is the same as the account that the transaction has an ID for.
     * 
     * @param acc account to be validated.
     * 
     * @return true if the account is correct, false if not.
     */
    @Override
    public boolean validate(Account acc) {
        if (acc.getID() == this.getID()) {
            return true;
        }
        else {
        return false;
        }
    }
}