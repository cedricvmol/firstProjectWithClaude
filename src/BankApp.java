import java.util.Scanner;


public class BankApp {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Hi there, What is your name?");
        String customer = scan.nextLine();
        System.out.printf("Welcome %s, I've created a new bank account for you!%n",customer);

        BankAccount account1 = new BankAccount(customer);

        boolean flag = true;
        while(flag){
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.Check Balance");
            System.out.println("4.Exit");
            int choice = scan.nextInt();

            switch (choice){
                case 1:
                    System.out.println("What is the amount you would like to deposit?");
                    account1.deposit(scan.nextDouble());
                    break;
                case 2:
                    System.out.println("What is the amount you would like to withdraw?");
                    account1.withdraw(scan.nextDouble());
                    break;
                case 3:

                    System.out.printf("%nThe amount of money on %s's bankaccount is %.2f%n",account1.getOwner(),account1.getBalance());
                    break;
                case 4:
                    System.out.println("Thanks for using our Bank! see you soon!");
                    flag = false;
                    break;

            }
        }
    }
}