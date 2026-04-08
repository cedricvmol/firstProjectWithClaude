import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public HashMap<String, Customer> loadData() throws IOException {

        BufferedReader reader = new BufferedReader((new FileReader(FILE_PATH)));
        String line;
        HashMap<String, Customer> customerMap = new HashMap<>();

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
                //TODO
            }
        }
        reader.close();
        return customerMap;
    }
}
