import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class BankApp {
    private static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Welcome in my bank app!");
        mainMenu();
    }

    public static void mainMenu(){

        Customer customer = null;

        boolean flag = true;
        while(flag){
            System.out.println();
            System.out.println("1.Create Customer");
            System.out.println("2.Open account (Savings or Checking");
            System.out.println("3.Deposit");
            System.out.println("4.Withdraw");
            System.out.println("5.Check Balance");
            System.out.println("6.View accounts");
            System.out.println("7.Exit");

            System.out.println("What would you like to do?");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1:
                    customer = createCustomer();
                    break;
                case 2:
                    if (isCustomerCreated(customer)) {
                        openAccount(customer);
                    }else {
                        break;
                    }
                    break;
                case 3:
                    if(isCustomerCreated(customer) && !customer.getAccounts().isEmpty()){
                        System.out.println("On what account you want to deposit?");
                        viewAccounts(customer);
                        int accountChoicetoDeposit = scan.nextInt();
                        deposit(customer,accountChoicetoDeposit);
                    }else {
                        System.out.println("You have no account set-up, go to menu and select 'open account'");
                        break;
                    }
                    break;
                case 4:
                    if(isCustomerCreated(customer) && !customer.getAccounts().isEmpty()) {
                        System.out.println("On what account you want to withdraw?");
                        viewAccounts(customer);
                        int accountChoiceToWithdraw = scan.nextInt();
                        withdraw(customer, accountChoiceToWithdraw);
                    }else {
                        System.out.println("You have no account set-up, go to menu and select 'open account'");
                        break;
                    }
                    break;
                case 5:
                    if(isCustomerCreated(customer) && !customer.getAccounts().isEmpty()){
                        System.out.println("On what account you want to see the balance?");
                        viewAccounts(customer);
                        int viewBalanceChoise = scan.nextInt();
                        viewBalance(customer,viewBalanceChoise);
                    }else {
                        System.out.println("You have no account set-up, go to menu and select 'open account'");
                        break;
                    }
                    break;
                case 6:
                    if(isCustomerCreated(customer)&& !customer.getAccounts().isEmpty()){
                        viewAccounts(customer);
                    }else{
                        break;
                    }
                    break;
                case 7:
                    flag = false;
                    break;
            }
        }
    }

    public static void openAccount(Customer customer){
        System.out.printf("What type of account do you want to open?%n" +
                "1.Saving%n" +
                "2.Checking%n" +
                "3.Go back to menu%n");
        int choiceOfAccount = scan.nextInt();
        if(choiceOfAccount == 1){
            SavingAccount savingAccount = new SavingAccount(createRandomAccountNumber());
            customer.addAccount(savingAccount);
        }
        if(choiceOfAccount==2){
            CheckingAccount checkingAccount = new CheckingAccount(createRandomAccountNumber());
            customer.addAccount(checkingAccount);
        }
        if(choiceOfAccount==3){
            return;
        }
    }

    public static void viewAccounts(Customer customer){
        List<BankAccount> accounts = customer.getAccounts();
        for(int i=0;i<accounts.size();i++){
            System.out.printf("%d.%s%n",i+1,accounts.get(i).toString());
        }
    }

    public static void deposit(Customer customer,int accountChoice){
        System.out.println("What is the amount you want to deposit?");
        double amount = scan.nextDouble();
        customer.getAccounts().get(accountChoice-1).deposit(amount);
        System.out.printf("You have successfully deposited €%.2f%n",amount);
        System.out.printf("The new amount on %s, is now €%.2f%n",customer.getAccounts().get(accountChoice-1),customer.getAccounts().get(accountChoice-1).getBalance());
    }

    public static void withdraw(Customer customer, int accountChoice){
        System.out.println("What is the amount you want to withdraw?");
        double amountToWithdraw = scan.nextDouble();

        if(customer.getAccounts().get(accountChoice-1).withdraw(amountToWithdraw)){
            System.out.printf("You have successfully Withdraw €%.2f%n",amountToWithdraw);
            System.out.printf("The new amount on %s, is now €%.2f%n",customer.getAccounts().get(accountChoice-1),customer.getAccounts().get(accountChoice-1).getBalance());
        }
    }

    public static void viewBalance(Customer customer,int accountChoice){
        System.out.printf("The balance for %s , is €%.2f%n",customer.getAccounts().get(accountChoice-1),customer.getAccounts().get(accountChoice-1).getBalance());
    }

    public static Customer createCustomer(){
        System.out.println("Customer name: ");
        Customer customer = new Customer(scan.nextLine(),createRandomId());
        System.out.printf("New customer %s added to the system!%nWith ID:%s.%n%n",customer.getName(),customer.getCustomerId());
        return customer;

    }

    public static String createRandomId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String createRandomAccountNumber(){
        Random random = new Random();
        String card = "BE";
        for(int i=0;i<14;i++){
            int n = random.nextInt(10)+ 0;
            card += Integer.toString(n);
        }
        return card;
    }

    public static boolean isCustomerCreated(Customer customer){
        if(customer == null){
            System.out.println("Please create a customer first.");
            return false;
        }else{
            return true;
        }
    }

}