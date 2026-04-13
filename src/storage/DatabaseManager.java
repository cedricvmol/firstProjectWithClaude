package storage;

import domain.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DatabaseManager {

    private final String CONNECTION_URL = "jdbc:sqlite:data/bank.db";


    private Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_URL);
        return conn;
    }

    public void createTables() throws SQLException {

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement();) {
            stmt.execute("CREATE TABLE IF NOT EXISTS customers ("
                            + "customer_id TEXT PRIMARY KEY,"
                            + "name TEXT NOT NULL)");

            stmt.execute("CREATE TABLE IF NOT EXISTS bank_accounts ("
                            + "account_number TEXT PRIMARY KEY,"
                            + "balance REAL NOT NULL,"
                            +  "type TEXT NOT NULL,"
                            + "customer_id TEXT NOT NULL,"
                            + "FOREIGN KEY (customer_id) REFERENCES customers(customer_id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS transactions ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + "account_number TEXT NOT NULL,"
                            + "transaction_type TEXT NOT NULL, "
                            + "amount REAL NOT NULL,"
                            + "balance_after_transaction REAL NOT NULL,"
                            + "local_date_time TEXT NOT NULL,"
                            + "FOREIGN KEY (account_number) REFERENCES bank_accounts(account_number))");
        }
    }

    public void saveData(HashMap<String, Customer> customers) throws SQLException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             PreparedStatement custStmt = con.prepareStatement("INSERT OR REPLACE INTO customers (customer_id,name) VALUES (?,?)");
             PreparedStatement accountStmt = con.prepareStatement("INSERT OR REPLACE INTO bank_accounts(account_number,balance,type,customer_id) VALUES (?,?,?,?)");
             PreparedStatement transStmt = con.prepareStatement("INSERT OR REPLACE INTO transactions(account_number,transaction_type,amount,balance_after_transaction,local_date_time) VALUES (?,?,?,?,?)")) {

            stmt.execute("DELETE FROM transactions");
            stmt.execute("DELETE FROM bank_accounts");
            stmt.execute("DELETE FROM customers");


            for (String key : customers.keySet()) {
                custStmt.setString(1, key);
                custStmt.setString(2, customers.get(key).getName());
                custStmt.executeUpdate();
                for (BankAccount account : customers.get(key).getAccounts()) {
                    accountStmt.setString(1, account.getAccountNumber());
                    accountStmt.setDouble(2, account.getBalance());
                    accountStmt.setString(3, account.getType());
                    accountStmt.setString(4, key);
                    accountStmt.executeUpdate();
                    for (Transaction transaction : account.getTransactions()) {
                        transStmt.setString(1, account.getAccountNumber());
                        transStmt.setString(2, transaction.getType().name());
                        transStmt.setDouble(3, transaction.getAmount());
                        transStmt.setDouble(4, transaction.getBalanceAfterTransaction());
                        transStmt.setString(5, transaction.getDateTime().format(formatter));
                        transStmt.executeUpdate();
                    }
                }
            }
        }
    }


    public HashMap<String, Customer> loadData() throws SQLException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        HashMap<String, Customer> customers = new HashMap<>();

        try (Connection con = getConnection();
             PreparedStatement custStmt = con.prepareStatement("SELECT * FROM customers"); ResultSet rs = custStmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("customer_id");
                String name = rs.getString("name");
                customers.put(id, new Customer(name, id));
            }

        }
        HashMap<String, BankAccount> accounts = new HashMap<>();
        try (Connection con = getConnection();
             PreparedStatement accountStmt = con.prepareStatement("SELECT * FROM bank_accounts");
             ResultSet rs = accountStmt.executeQuery()) {

            while (rs.next()) {
                String customerID = rs.getString("customer_id");
                String accountNumber = rs.getString("account_number");
                Double balance = rs.getDouble("balance");
                String type = rs.getString("type");
                if (type.equals("Savings")) {
                    SavingAccount savAcc = new SavingAccount(accountNumber);
                    savAcc.setBalance(balance);
                    accounts.put(accountNumber, savAcc);
                    customers.get(customerID).addAccount(savAcc);
                } else {
                    CheckingAccount checkAcc = new CheckingAccount(accountNumber);
                    checkAcc.setBalance(balance);
                    accounts.put(accountNumber, checkAcc);
                    customers.get(customerID).addAccount(checkAcc);
                }
            }
        }
        try (Connection con = getConnection();
             PreparedStatement transactionStmt = con.prepareStatement("SELECT * FROM transactions");
             ResultSet rs = transactionStmt.executeQuery()) {

            while (rs.next()) {
                String accountNumber = rs.getString("account_number");
                String transactionType = rs.getString("transaction_type");
                Double amount = rs.getDouble("amount");
                Double balanceAfterTransaction = rs.getDouble("balance_after_transaction");
                String localDateTime = rs.getString("local_date_time");

                LocalDateTime localDateTime1 = LocalDateTime.parse(localDateTime, formatter);

                accounts.get(accountNumber).addTransaction(new Transaction(TransactionType.valueOf(transactionType), amount, balanceAfterTransaction, localDateTime1));
            }
        }
        return customers;
    }
}
