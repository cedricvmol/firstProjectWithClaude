import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final String name;
    private final String customerId;
    private final List<BankAccount> accounts;


    public Customer(String name, String id){
        this.name = name;
        this.customerId = id;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account){

        if(account != null) {
            accounts.add(account);
        }else {
            System.out.println("Geef een geldig bank account mee.");
        }
    }

    public List<BankAccount> getAccounts(){
        return accounts;
    }

    public String getName(){
        return name;
    }

    public String getCustomerId(){
        return customerId;
    }

    @Override
    public String toString(){
        return getName();
    }
}
