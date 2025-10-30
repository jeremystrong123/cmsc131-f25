package projects.bank;

public class Withdrawal extends Transaction {
    
    //constructor, works same as parent class.
    public Withdrawal(String id, double value) {
        super(id, value);
    }
    
    /**
     * Debits the amount of the transaction from the given account.
     * 
     * @param acc account to be debited from.
     */
    @Override
    public void execute(Account acc) {
        acc.debit(this.getAmount());
    }

    /**
     * Validates that a given account is the same as the account that the transaction has an ID for and that the account has sufficient funds for a withdrawal.
     * 
     * @param acc account to be validated.
     * 
     * @return true if the account is correct and has sufficient funds, false if not.
     */
    @Override
    public boolean validate(Account acc) {
        if ((acc.getID() == this.getID()) && (acc.getBalance() >= this.getAmount())) {
            return true;
        }
        else {
        return false;
        }
    }
}
