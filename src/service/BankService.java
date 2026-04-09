package service;

import domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BankService {
    private HashMap<String, Customer> customers = new HashMap<>();
    private Customer selectedCustomer;

    public Customer createCustomer(String name){
        String randomId = generateCustomerId();
        Customer cust = new Customer(randomId,name);
        customers.put(randomId,cust);
        return cust;

    }

    public Customer selectCustomer(String name){
        for(String id : customers.keySet()){
            if(customers.get(id).getName().equalsIgnoreCase(name)){
                selectedCustomer = customers.get(id);
                return customers.get(id);
            }
        }
        return null;
    }

    public Customer getSelectedCustomer(){
        return selectedCustomer;
    }

    public HashMap<String,Customer> getAllCustomers(){
        return customers;
    }

    public boolean isCustomerSelected(){
        return selectedCustomer != null;
    }

    public boolean hasAccounts(){
        return !selectedCustomer.getAccounts().isEmpty();
    }

    public BankAccount openSavingsAccount(){
        SavingAccount acc = new SavingAccount(createRandomAccountNumber());
        selectedCustomer.addAccount(acc);
        return acc;
    }

    public BankAccount openCheckingAccount(){
        CheckingAccount acc = new CheckingAccount(createRandomAccountNumber());
        selectedCustomer.addAccount(acc);
        return acc;
    }

    public List<BankAccount> getAccounts(){
        return selectedCustomer.getAccounts();
    }

    public void deposit(int accountIndex, double amount){
        selectedCustomer.getAccounts().get(accountIndex).deposit(amount);
    }

    public boolean withdraw(int accountIndex,double amount){
        return selectedCustomer.getAccounts().get(accountIndex).withdraw(amount);
    }

    public void transfer(int fromIndex, int toIndex, double amount){
        BankAccount accFrom = selectedCustomer.getAccounts().get(fromIndex);
        BankAccount accTo = selectedCustomer.getAccounts().get(toIndex);

        accFrom.withdraw(amount);
        accTo.deposit(amount);

        accFrom.getTransactions().removeLast();
        accTo.getTransactions().removeLast();

        accFrom.addTransaction(new Transaction(TransactionType.TRANSFER_OUT,amount,accFrom.getBalance()));
        accTo.addTransaction(new Transaction(TransactionType.TRANSFER_IN,amount,accTo.getBalance()));
    }

    public List<Transaction> getTransactions(int accountIndex){
        return selectedCustomer.getAccounts().get(accountIndex).getTransactions();
    }

    public BankAccount getAccount(int index){
        return selectedCustomer.getAccounts().get(index);
    }








    private  String generateCustomerId(){
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int n = random.nextInt(10);
            id.append(n);
        }
        return id.toString();
    }

    private static String createRandomAccountNumber() {
        Random random = new Random();
        StringBuilder card = new StringBuilder("BE");
        for (int i = 0; i < 14; i++) {
            int n = random.nextInt(10);
            card.append(n);
        }
        return card.toString();
    }
}
