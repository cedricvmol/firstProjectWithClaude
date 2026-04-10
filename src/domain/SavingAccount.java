package domain;

public class SavingAccount extends BankAccount {

    public SavingAccount(String accountNumber) {
        super(accountNumber);
    }

    public void withdraw(double amount) {
        if (getBalance() < amount) {
            throw new IllegalArgumentException("U have insufficient balance for that withdrawel.");
        }
        super.withdraw(amount);
    }

    public String getType() {
        return "Savings";
    }

}
