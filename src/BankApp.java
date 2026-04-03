import java.util.*;

public class BankApp {
    private static Scanner scan = new Scanner(System.in);
    private static HashMap<String,Customer> customers = new HashMap<>();
    private static Customer selectedCustomer;


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
            System.out.println("2.Select Customer");
            System.out.println("3.Open account (Savings or Checking");
            System.out.println("4.Deposit");
            System.out.println("5.Withdraw");
            System.out.println("6.Check Balance");
            System.out.println("7.View accounts");
            System.out.println("8.View all customers");
            System.out.println("9.Exit");

            System.out.println("What would you like to do?");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1:
                    createCustomer();
                    break;


                case 2:
                    selectCustomer();
                    break;


                case 3: //TODO implement open account


                case 4:
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


                case 5:
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


                case 6:
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

                case 7:
                    if(isCustomerCreated(customer)&& !customer.getAccounts().isEmpty()){
                        viewAccounts(customer);
                    }else{
                        break;
                    }
                    break;


                case 8 :
                    viewAllCustomers();
                    break;


                case 9:
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

    public static void createCustomer(){
        String customerID = createRandomId();

        System.out.println("Customer name: ");
        String customerName = scan.nextLine();


        Customer customer = new Customer(customerName,customerID);
        System.out.printf("New customer %s added to the system!%nWith ID:%s.%n%n",customer.getName(),customer.getCustomerId());
        customers.put(customerID,customer);
    }

    public static void selectCustomer(){
        viewAllCustomers();
        System.out.println("Which customer u want to select?");
        String customerToSelect = scan.nextLine();
        customerToSelect.toLowerCase();

        for(String key : customers.keySet()){
            if(customers.get(key).getName().toLowerCase().equals(customerToSelect)){
                selectedCustomer = customers.get(key);
            }
        }
        System.out.println("U have selected " + selectedCustomer.getName() + ".");

    }

    public static void viewAllCustomers(){
        System.out.println("Customers in our system:");
        for(String key : customers.keySet()){
            System.out.println("ID:"+key + "  --------  Name: " + customers.get(key).toString());
        }
    }

    public static String createRandomId(){
        Random random = new Random();
        String id = "";
        for(int i=0;i<5;i++){
            int n = random.nextInt(10) + 0;
            id += Integer.toString(n);
        }
        return id;
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