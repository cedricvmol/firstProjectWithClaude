package app;

import domain.*;
import service.BankService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class BankApp {
    private static final Scanner scan = new Scanner(System.in);
    private static BankService bankService = new BankService();

    public static void main(String[] args) throws SQLException {
        bankService.createTables();
        bankService.loadData();
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
                        bankService.saveData();
                        flag = false;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please select a valid menu option!");
                scan.nextLine();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void openAccount() {
        if (!bankService.isCustomerSelected()) return;
        System.out.println();
        System.out.printf("What type of account do you want to open?%n" +
                "1.Saving%n" +
                "2.Checking%n" +
                "3.Go back to menu%n");
        try {
            int choiceOfAccount = scan.nextInt();

            if (choiceOfAccount == 1) {
                BankAccount savingAccount = bankService.openSavingsAccount();
                System.out.printf("You have successfully created the next account: %n%s.%n", savingAccount.toString());
            }
            if (choiceOfAccount == 2) {
                BankAccount checkingAccount = bankService.openCheckingAccount();
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
        if (!bankService.isCustomerSelected()) return;
        if (!bankService.hasAccounts()) return;

        System.out.println(bankService.getSelectedCustomer().getName() + " has the following accounts: ");
        List<BankAccount> accounts = bankService.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, accounts.get(i).toString());
        }
    }

    public static void deposit() {
        if (!bankService.isCustomerSelected()) return;
        if (!bankService.hasAccounts()) return;
        viewAccounts();

        try {
            System.out.println("On what account do you want to deposit?");
            int accountChoice = scan.nextInt();

            System.out.println("What is the amount you want to deposit?");
            double amount = scan.nextDouble();

            bankService.deposit(accountChoice - 1, amount);
            System.out.printf("You have successfully deposited %.2f on %s%n", amount, bankService.getAccount(accountChoice - 1).toString());

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That account doesn't exist.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void withdraw() {
        if (!bankService.isCustomerSelected()) return;
        if (!bankService.hasAccounts()) return;
        viewAccounts();

        try {

            System.out.println("On what account do you want to withdraw?");
            int accountChoiceToWithdraw = scan.nextInt();

            System.out.println("What is the amount you want to withdraw?");
            double amount = scan.nextDouble();

            bankService.withdraw(accountChoiceToWithdraw - 1, amount);
            System.out.printf("You have successfully withdrawn %.2f on %s%n", amount, bankService.getAccount(accountChoiceToWithdraw - 1).toString());


        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That account doesn't exist.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void viewBalance() {
        if (!bankService.isCustomerSelected()) return;
        if (!bankService.hasAccounts()) return;

        System.out.println("For what account do you want see the balance?");
        viewAccounts();
        try {
            int account = scan.nextInt();
            System.out.printf("The balance for %s , is €%.2f%n", bankService.getAccount(account - 1), bankService.getAccount(account - 1).getBalance());
        } catch (InputMismatchException e) {
            System.out.println("Give a valid input!");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Select an account from the list!");
            scan.nextLine();
        }

    }

    public static void createCustomer() {

        System.out.println("New customer name: ");
        String customerName = scan.nextLine();

        bankService.createCustomer(customerName);
        System.out.printf("%nNew customer %s added to the system!%n%n", customerName);
    }

    public static void selectCustomer() {
        viewAllCustomers();
        System.out.println("Which customer you would like to select?: ");
        String customerToSelect = scan.nextLine();

        bankService.selectCustomer(customerToSelect);

        if (bankService.getSelectedCustomer() != null) {
            System.out.println("U have selected " + bankService.getSelectedCustomer().getName() + ".");
        } else {
            System.out.println("Please type a valid customer!");
        }


    }

    public static void viewAllCustomers() {

        System.out.println("Customers in our system:");
        for (String key : bankService.getAllCustomers().keySet()) {
            System.out.println("ID:" + key + "  --------  Name: " + bankService.getAllCustomers().get(key));
        }
    }


    public static void transfer() {
        //  1. Check isUserSelected() and hasUserAccount()
        if (!bankService.isCustomerSelected()) return;
        if (!bankService.hasAccounts()) return;
        //  2. The customer needs at least 2 accounts to transfer between — add a check for that
        if (bankService.getAccounts().size() >= 2) {
            try {
                //  3. Ask which account to transfer from
                System.out.println("From what account you want to transfer money?");
                viewAccounts();
                int accountFromChoice = scan.nextInt();

                //  4. Ask which account to transfer to
                System.out.println("To what account you want to transfer money?");
                viewAccounts();
                int accountToChoice = scan.nextInt();

                //  5. Ask for the amount
                System.out.println("What is the amount you want to transfer?");
                double amount = scan.nextDouble();


                bankService.transfer(accountFromChoice - 1, accountToChoice - 1, amount);


            } catch (InputMismatchException e) {
                System.out.println("Give a valid input.");
                scan.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Give a valid account!");
                scan.nextLine();
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("U need 2 accounts in order to transfer between accounts.");
            return;
        }


    }

    public static void viewTransactions() {
        if (!bankService.isCustomerSelected()) return;
        if (!bankService.hasAccounts()) return;

        try {
            System.out.println("Chose account to see transaction history:");
            viewAccounts();
            List<Transaction> transactions = bankService.getTransactions(scan.nextInt() - 1);
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    System.out.println(transaction.toString());
                }
            } else {
                System.out.println("This account has no transactions.");
            }


        } catch (InputMismatchException e) {
            System.out.println("Give a valid input.");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Give a valid account!");
            scan.nextLine();
        }

    }


    public static void printStatement() {
        if (!bankService.isCustomerSelected()) return;
        if (!bankService.hasAccounts()) return;

        try {
            System.out.println("Chose account to see create print statement:");
            viewAccounts();
            System.out.println(bankService.printStatement(scan.nextInt()-1));
        } catch (InputMismatchException e) {
            System.out.println("Give a valid input.");
            scan.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Give a valid account!");
            scan.nextLine();
        }
    }

}