package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private String CONNECTION_URL;


    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:data/bank.db");
        return con;
    }

    //TODO ● That works. Now tackle createTables() — you'll need to:
    //
    //  1. Get a connection using your getConnection() method
    //  2. Execute SQL statements to create the 3 tables
    //
    //  For executing SQL that doesn't return data (like CREATE TABLE), the pattern is:
    //
    //  Statement stmt = conn.createStatement();
    //  stmt.execute("your SQL here");
    //
    //  You'll need java.sql.Statement. Design the tables based on your domain classes — look at what Customer, BankAccount, and Transaction store as fields. Give it a shot!


}
