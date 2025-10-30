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

    /*@Override
    public boolean validate(Account acc) {
        return false;
    }*/
}