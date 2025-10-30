package projects.bank;

public class SavingsAccount extends Account {
    
    //constructor, works same as parent class.
    public SavingsAccount(String id, String owner, double balance) {
        super(id, owner, balance);
    }

    /**
     * Accessor for AccountType.
     * @return savings because this is a savings account.
     */
    @Override
    public AccountType getType() {
        AccountType t = AccountType.valueOf("SAVINGS");
        return t;
    }
}
