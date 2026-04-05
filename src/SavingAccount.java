public class SavingAccount extends BankAccount{

    public SavingAccount(String accountNumber) {
        super(accountNumber);
    }

    public boolean withdraw(double amount){
        if(getBalance()<amount){
            System.out.println("U have insufficient balance for that withdrawel.");
            return false;
        }else {
            super.withdraw(amount);
            return true;
        }
    }

    public String getType(){
        return "Savings";
    }

}
