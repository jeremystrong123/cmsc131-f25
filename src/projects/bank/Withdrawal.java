/** TODO / comments
 * 
 * same comment about execute and ID validation
 */
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
    public void execute(Account acc, Audit audit) {
        acc.debit(this.getAmount());
        audit.recordTransaction(this, acc);
    }

    /**
     * Validates that a given account has sufficient funds for a withdrawal.
     * 
     * @param acc account to be validated.
     * 
     * @return true if the account has sufficient funds, false if not.
     */
    @Override
    public boolean validate(Account acc, Audit audit) {
        if ((acc.getBalance() >= this.getAmount())) {
            return true;
        }
        else {
            audit.nonSufficientFunds(this, acc);
            return false;
        }
    }
}
