package app;

import domain.*;
import storage.FileManager;
import java.io.IOException;
import java.util.*;

public class BankApp {
    private static final Scanner scan = new Scanner(System.in);
    private static HashMap<String,Customer> customers;
    private static Customer selectedCustomer;


    public static void main(String[] args) throws IOException {
        customers = FileManager.loadData();
        System.out.println("Welcome in my bank app!");
        mainMenu();

    }

    public static void mainMenu() {


        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("1.Create customer");
            System.out.println("2.Select customer");
            System.out.println("3.Open account (Savings or Checking)");
            System.out.println("4.Deposit");
            System.out.println("5.Withdraw");
            System.out.println("6.Check Balance");
            System.out.println("7.View accounts");
            System.out.println("8.View all customers");
            System.out.println("9.Transfer between accounts");
            System.out.println("10.View transaction history");
            System.out.println("11.View print statement for account");
            System.out.println("12.Exit");

            try {
                System.out.println("What would you like to do?");
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        selectCustomer();
                        break;
                    case 3:
                        openAccount();
                        break;
                    case 4:
                        deposit();
                        break;
                    case 5:
                        withdraw();
                        break;
                    case 6:
                        viewBalance();
                        break;
                    case 7:
                        viewAccounts();
                        break;
                    case 8:
                        viewAllCustomers();
                        break;
                    case 9:
                        transfer();
                        break;
                    case 10:
                        viewTransactions();
                        break;
                    case 11:
                        printStatement();
                        break;
                    case 12:
                        FileManager.saveData(customers);
                        flag = false;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please select a valid menu option!");
                scan.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void openAccount() {
        if (!isUserSelected()) return;
        viewAccounts();
        System.out.println();
        System.out.printf("What type of account do you want to open?%n" +
                "1.Saving%n" +
                "2.Checking%n" +
                "3.Go back to menu%n");
        try {
            int choiceOfAccount = scan.nextInt();

            if (choiceOfAccount == 1) {
                SavingAccount savingAccount = new SavingAccount(createRandomAccountNumber());
                selectedCustomer.addAccount(savingAccount);
                System.out.printf("You have successfully created the next account: %n%s.%n", savingAccount.toString());
            }
            if (choiceOfAccount == 2) {
                CheckingAccount checkingAccount = new CheckingAccount(createRandomAccountNumber());
                selectedCustomer.addAccount(checkingAccount);
                System.out.printf("You have successfully created the next account: %n%s.%n", checkingAccount.toString());
            }
            if (choiceOfAccount == 3) {
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Select a valid account type!");
            scan.nextLine();
        }

    }

    public static void viewAccounts() {
        if (!isUserSelected()) return;
        if (!hasUserAccount()) return;

        System.out.println(selectedCustomer.getName() + " has the following accounts: ");
        List<BankAccount> accounts = selectedCustomer.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, accounts.get(i).toString());
        }
    }

    public static void deposit() {
        if (!isUserSelected()) return;
        if (!hasUserAccount()) return;
        viewAccounts();

        try {
            System.out.println("On what account do you want to deposit?");
            int accountChoice = scan.nextInt();

            System.out.println("What is the amount you want to deposit?");
            double amount = scan.nextDouble();

            selectedCustomer.getAccounts().get(accountChoice - 1).deposit(amount);
            System.out.printf("You have successfully deposited %.2f on %s%n", amount, selectedCustomer.getAccounts().get(accountChoice - 1).toString());

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That account doesn't exist.");
        }

    }

    public static void withdraw() {
        if (!isUserSelected()) return;
        if (!hasUserAccount()) return;
        viewAccounts();

        try {

            System.out.println("On what account do you want to withdraw?");
            int accountChoiceToWithdraw = scan.nextInt();

            System.out.println("What is the amount you want to withdraw?");
            double amount = scan.nextDouble();

            if (selectedCustomer.getAccounts().get(accountChoiceToWithdraw - 1).withdraw(amount)) {
                System.out.printf("You have successfully withdrawn %.2f on %s%n", amount, selectedCustomer.getAccounts().get(accountChoiceToWithdraw - 1).toString());
            } else {
                System.out.println("Insufficient funds.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That account doesn't exist.");
        }


    }

    public static void viewBalance() {
        if (!isUserSelected()) return;
        if (!hasUserAccount()) return;

        System.out.println("For what account do you want see the balance?");
        viewAccounts();
        try {
            int account = scan.nextInt();
            System.out.printf("The balance for %s , is €%.2f%n", selectedCustomer.getAccounts().get(account - 1), selectedCustomer.getAccounts().get(account - 1).getBalance());
        } catch (InputMismatchException e) {
            System.out.println("Give a valid input!");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Select an account from the list!");
            scan.nextLine();
        }

    }

    public static void createCustomer() {
        String customerID = createRandomId();

        System.out.println("New customer name: ");
        String customerName = scan.nextLine();

        Customer customer = new Customer(customerName, customerID);
        System.out.printf("%nNew customer %s with ID:%s added to the system!%n%n", customer.getName(), customer.getCustomerId());
        customers.put(customerID, customer);

        if (customers.size() == 1) {
            selectedCustomer = customer;
        }
    }

    public static void selectCustomer() {
        viewAllCustomers();

        System.out.println("Type a customer name.");
        String customerToSelect = scan.nextLine();
        customerToSelect = customerToSelect.toLowerCase();
        boolean customerFound = false;

        for (String key : customers.keySet()) {
            if (customers.get(key).getName().toLowerCase().equals(customerToSelect)) {
                selectedCustomer = customers.get(key);
                customerFound = true;
            }

        }
        if (customerFound) {
            System.out.println("U have selected " + selectedCustomer.getName() + ".");
        } else {
            System.out.println("Please type a valid customer!");
        }


    }

    public static void viewAllCustomers() {

        System.out.println("Customers in our system:");
        for (String key : customers.keySet()) {
            System.out.println("ID:" + key + "  --------  Name: " + customers.get(key).toString());
        }
    }

    public static String createRandomId() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int n = random.nextInt(10);
            id.append(n);
        }
        return id.toString();
    }

    public static String createRandomAccountNumber() {
        Random random = new Random();
        StringBuilder card = new StringBuilder("BE");
        for (int i = 0; i < 14; i++) {
            int n = random.nextInt(10);
            card.append(n);
        }
        return card.toString();
    }

    public static void transfer() {
        //  1. Check isUserSelected() and hasUserAccount()
        if (!isUserSelected()) return;
        if (!hasUserAccount()) return;
        //  2. The customer needs at least 2 accounts to transfer between — add a check for that
        if (selectedCustomer.getAccounts().size() >= 2) {
            try {
                //  3. Ask which account to transfer from
                System.out.println("From what account you want to transfer money?");
                viewAccounts();
                int accountFromChoice = scan.nextInt();
                BankAccount accountFrom = selectedCustomer.getAccounts().get(accountFromChoice - 1);

                //  4. Ask which account to transfer to
                System.out.println("To what account you want to transfer money?");
                viewAccounts();
                int accountToChoice = scan.nextInt();
                BankAccount accountTo = selectedCustomer.getAccounts().get(accountToChoice - 1);

                //  5. Ask for the amount
                System.out.println("What is the amount you want to transfer?");
                double amount = scan.nextDouble();

                //  6. Withdraw from source, deposit to destination
                accountFrom.withdraw(amount);
                accountTo.deposit(amount);

                accountFrom.getTransactions().removeLast();
                accountTo.getTransactions().removeLast();

                //  7. Both accounts should get a TRANSFER transaction (not DEPOSIT/WITHDRAWAL)

                Transaction tFrom = new Transaction(TransactionType.TRANSFER_OUT, amount,accountFrom.getBalance());
                Transaction tTo = new Transaction(TransactionType.TRANSFER_IN , amount , accountTo.getBalance());
                accountFrom.getTransactions().add(tFrom);
                accountTo.getTransactions().add(tTo);



            } catch (InputMismatchException e) {
                System.out.println("Give a valid input.");
                scan.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Give a valid account!");
                scan.nextLine();
            }

        } else {
            System.out.println("U need 2 accounts in order to transfer between accounts.");
            return;
        }



    }

    public static void viewTransactions(){
        if (!isUserSelected()) return;
        if (!hasUserAccount()) return;

        try{
            System.out.println("Chose account to see transaction history:");
            viewAccounts();
            List <Transaction> transactions = selectedCustomer.getAccounts().get(scan.nextInt()-1).getTransactions();
            if(!transactions.isEmpty()){
                for (Transaction transaction : transactions){
                    System.out.println(transaction.toString());
                }
            }else {
                System.out.println("This account has no transactions.");
            }


        } catch (InputMismatchException e){
            System.out.println("Give a valid input.");
            scan.nextLine();
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Give a valid account!");
            scan.nextLine();
        }

    }

    public static boolean isUserSelected() {
        if (selectedCustomer == null) {
            System.out.println("You need to create and select a customer first.");
            return false;
        } else {
            return true;
        }

    }

    public static boolean hasUserAccount() {
        if (selectedCustomer.getAccounts().isEmpty()) {
            System.out.println("There are no accounts for the following user " + selectedCustomer.getName());
            return false;
        } else {
            return true;
        }
    }

    public static void printStatement(){
        if (!isUserSelected()) return;
        if (!hasUserAccount()) return;

        try{
            System.out.println("Chose account to see create print statement:");
            viewAccounts();
            BankAccount account = selectedCustomer.getAccounts().get(scan.nextInt()-1);
            account.printStatement();

        } catch (InputMismatchException e){
            System.out.println("Give a valid input.");
            scan.nextLine();
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Give a valid account!");
            scan.nextLine();
        }
    }

}