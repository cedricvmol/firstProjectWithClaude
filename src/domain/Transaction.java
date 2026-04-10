package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private TransactionType type;
    private double amount;
    private LocalDateTime localDateTime;
    private double balanceAfterTransaction;


    public Transaction(TransactionType type, double amount, double balanceAfterTransaction) {
        this.amount = amount;
        this.type = type;
        this.localDateTime = LocalDateTime.now();
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Transaction(TransactionType type, double amount, double balanceAfterTransaction, LocalDateTime dateTime) {
        this.type = type;
        this.amount = amount;
        this.localDateTime = dateTime;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return localDateTime;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return getType() + " - " + getAmount() + " - " + localDateTime.format(formatter);
    }
}
