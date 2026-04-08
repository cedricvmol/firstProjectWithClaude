package storage;

import domain.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class FileManager {
    private static final String FILE_PATH = "data/bank.txt";


    public static void saveData(HashMap<String, Customer> customers) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (String key : customers.keySet()) {
            writer.write("CUSTOMER;" + key + ";" + customers.get(key).getName());
            writer.newLine();
            for (BankAccount account : customers.get(key).getAccounts()) {
                writer.write("ACCOUNT;" + key + ";" + account.getAccountNumber() + ";" + account.getType() + ";" + account.getBalance());
                writer.newLine();
                for (Transaction transaction : account.getTransactions()) {
                    writer.write("TRANSACTION;" + account.getAccountNumber() + ";" + transaction.getType() + ";" + transaction.getAmount() + ";" + transaction.getDateTime().format(formatter));
                    writer.newLine();
                }
            }
        }
        writer.close();
    }

    public static HashMap<String, Customer> loadData() throws IOException {

        try {
            BufferedReader reader = new BufferedReader((new FileReader(FILE_PATH)));
            String line;
            HashMap<String, Customer> customerMap = new HashMap<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            while ((line = reader.readLine()) != null) {
                String[] inputs = line.split(";");
                if (inputs[0].equals("CUSTOMER")) {
                    customerMap.put(inputs[1], new Customer(inputs[2], inputs[1]));
                } else if (inputs[0].equals("ACCOUNT")) {
                    if(inputs[3].equals("Savings")){
                        SavingAccount acc = new SavingAccount(inputs[2]);
                        acc.setBalance(Double.parseDouble(inputs[4]));
                        customerMap.get(inputs[1]).addAccount(acc);
                    }else {
                        CheckingAccount acc = new CheckingAccount(inputs[2]);
                        acc.setBalance(Double.parseDouble(inputs[4]));
                        customerMap.get(inputs[1]).addAccount(acc);
                    }

                } else if (inputs[0].equals("TRANSACTION")) {
                    for(Customer customer : customerMap.values()){
                        for(BankAccount account : customer.getAccounts()){
                            if(account.getAccountNumber().equals(inputs[1])){
                                Transaction t = new Transaction(TransactionType.valueOf(inputs[2]),Double.parseDouble(inputs[3]), LocalDateTime.parse(inputs[4],formatter));
                                account.addTransaction(t);
                            }
                        }
                    }
                }
            }
            reader.close();
            return customerMap;
        } catch (FileNotFoundException e){
            return new HashMap<>();
        }

    }
}
