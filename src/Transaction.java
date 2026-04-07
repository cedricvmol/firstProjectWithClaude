import java.time.LocalDateTime;

public class Transaction {

    private TransactionType type;
    private double amount;
    private LocalDateTime localDateTime;


    public Transaction(TransactionType type,double amount){
        this.amount = amount;
        this.type = type;
        localDateTime = LocalDateTime.now();
    }

    public TransactionType getType(){
        return type;
    }

    public double getAmount(){
        return amount;
    }

    public LocalDateTime getDateTime(){
        return localDateTime;
    }

    public String toString(){
        return "Transaction: " + getType() + " - " + getAmount() + " - " + getDateTime();
    }
}
