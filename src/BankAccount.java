abstract class BankAccount {

    private double balance = 0.0;
    private String accountNumber;

    public BankAccount(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount){

        if(amount > 0){
            balance += amount;
        }else{
            System.out.println("The deposit amount needs to be positive.");
        }
    }

    public boolean withdraw(double amount){
        balance -= amount;
        return true;
    }

    public double getBalance(){
        return balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }


    public abstract String getType();
}

