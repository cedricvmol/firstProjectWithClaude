abstract class BankAccount {

    private String owner;
    private double balance = 0.0;
    private String accountNumber;

    public BankAccount(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public boolean withdraw(double amount){
        if(balance < amount) {
            System.out.println("There is insufficient balance on the account. ");
            return false;
        }else  {
            balance -= amount;
            return true;
        }

    }

    public double getBalance(){
        return balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getOwner(){
        return owner;
    }

    public abstract String getType();
}

