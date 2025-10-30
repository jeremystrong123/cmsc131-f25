package projects.bank;

public class CheckingAccount extends Account {
    
    //constructor, works same as parent class.
    public CheckingAccount(String id, String owner, double balance) {
        super(id, owner, balance);
    }

    /**
     * Accessor for AccountType.
     * @return checking because this is a checking account.
     */
    @Override
    public AccountType getType() {
        AccountType t = AccountType.valueOf("CHECKING");
        return t;
    }
}
