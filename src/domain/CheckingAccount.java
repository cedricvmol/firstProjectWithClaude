package domain;

public class CheckingAccount extends BankAccount{

    private static final double OVERDRAFT_LIMIT = -500.00;

    public CheckingAccount(String accountNumber){
        super(accountNumber);
    }

    public void  withdraw(double amount){
        if(getBalance() - amount >= OVERDRAFT_LIMIT){
            super.withdraw(amount);
        }else {
            throw new IllegalArgumentException("Overdraft limit reached!");
        }
    }

    public String getType(){
        return "Checkings";
    }

}
