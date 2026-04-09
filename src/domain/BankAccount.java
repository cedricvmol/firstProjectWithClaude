package domain;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount implements Printable {

    private double balance = 0.0;
    private String accountNumber;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber){
        this.accountNumber = accountNumber;
        transactions = new ArrayList<>();
        setBalance(balance);
    }

    public void addTransaction(Transaction t){
        this.transactions.add(t);
    }

    public void deposit(double amount){

        if(amount > 0){
            balance += amount;
            addTransaction(new Transaction(TransactionType.DEPOSIT,amount,getBalance()));
        }else{
            System.out.println("The deposit amount needs to be positive.");
        }
    }

    public boolean withdraw(double amount){
        balance -= amount;
        addTransaction(new Transaction(TransactionType.WITHDRAWAL,amount,getBalance()));
        return true;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        this.balance = amount;
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

    @Override
    public void printStatement(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.printf("%-20s %-16s %10s %10s %n", "Date", "Type", "Amount", "Balance");
        for(Transaction transaction : transactions){
            System.out.printf("%-20s %-16s %10.2f %10.2f %n",
                    transaction.getDateTime().format(formatter),
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getBalanceAfterTransaction());
        }
    }
}

