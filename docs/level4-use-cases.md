# Level 4 — Test Cases

These are the behaviors to verify with JUnit tests. Each row is one `@Test` method.

## BankAccountTest (test via domain.SavingAccount as concrete implementation)

| # | Test method                  | What it verifies                                              |
|---|------------------------------|---------------------------------------------------------------|
| 1 | testDepositIncreasesBalance  | Depositing a positive amount increases the balance correctly  |
| 2 | testNegativeDepositIgnored   | Depositing a negative amount does not change the balance      |
| 3 | testInitialBalanceIsZero     | A new account always starts with balance 0                    |

## SavingAccountTest

| # | Test method                  | What it verifies                                              |
|---|------------------------------|---------------------------------------------------------------|
| 1 | testWithdrawSufficientFunds  | Withdrawing less than the balance succeeds and returns true   |
| 2 | testWithdrawInsufficientFunds| Withdrawing more than the balance fails and returns false     |
| 3 | testGetType                  | getType() returns "Savings"                                   |

## CheckingAccountTest

| # | Test method                  | What it verifies                                              |
|---|------------------------------|---------------------------------------------------------------|
| 1 | testWithdrawWithinOverdraft  | Withdrawing within the overdraft limit succeeds               |
| 2 | testWithdrawBeyondOverdraft  | Withdrawing beyond the overdraft limit fails                  |
| 3 | testGetType                  | getType() returns "Checking"                                  |

## CustomerTest

| # | Test method                  | What it verifies                                              |
|---|------------------------------|---------------------------------------------------------------|
| 1 | testAddAccount               | Adding a valid account increases the account list size        |
| 2 | testAddNullAccountIgnored    | Adding null does not add anything to the list                 |
| 3 | testGetName                  | getName() returns the name passed to the constructor          |