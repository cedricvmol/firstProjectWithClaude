public class CheckingAccount extends BankAccount{

    private static final double OVERDRAFT_LIMIT = -500.00;

    public CheckingAccount(String accountNumber){
        super(accountNumber);
    }

    public boolean withdraw(double amount){
        if(getBalance() - amount >= OVERDRAFT_LIMIT){
            super.withdraw(amount);
            return true;
        }else {
            System.out.println("Overdraft limit reached!");
            return false;
        }
    }

    public String getType(){
        return "Checking";
    }
}
