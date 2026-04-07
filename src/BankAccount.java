import java.util.ArrayList;
import java.util.List;

abstract class BankAccount {

    private double balance = 0.0;
    private String accountNumber;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber){
        this.accountNumber = accountNumber;
        transactions = new ArrayList<>();
    }

    public void deposit(double amount){

        if(amount > 0){
            balance += amount;
            transactions.add(new Transaction(TransactionType.DEPOSIT,amount));
        }else{
            System.out.println("The deposit amount needs to be positive.");
        }
    }

    public boolean withdraw(double amount){
        balance -= amount;
        transactions.add(new Transaction(TransactionType.WITHDRAWAL,amount));
        return true;
    }

    public double getBalance(){
        return balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }


    public abstract String getType();

    @Override
    public String toString(){
        return getType() + " - " + getAccountNumber();
    }
}

