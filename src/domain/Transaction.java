package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private TransactionType type;
    private double amount;
    private LocalDateTime localDateTime;


    public Transaction(TransactionType type,double amount){
        this.amount = amount;
        this.type = type;
        localDateTime = LocalDateTime.now();
    }

    public Transaction(TransactionType type, double amount, LocalDateTime dateTime) {
        this.type = type;
        this.amount = amount;
        this.localDateTime = dateTime;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  getType() + " - " + getAmount() + " - " + localDateTime.format(formatter);
    }
}
