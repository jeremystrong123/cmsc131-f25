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

    /*@Override
    public boolean validate(Account acc) {
        return false;
    }*/
}
