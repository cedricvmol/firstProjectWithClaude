public class BankAccount {

    private String owner;
    private double balance = 0.0;

    public BankAccount(String owner){
        this.owner = owner;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        if(balance < amount) {
            System.out.println("There is insufficient balance on the account. ");
        }else  {
            balance -= amount;
        }

    }

    public double getBalance(){
        return balance;
    }

    public String getOwner(){
        return owner;
    }


}
