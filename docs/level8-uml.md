# Level 8 — Service Layer UML

## Class Diagram

```
┌─────────────────────────────────────────┐
│              BankApp (UI)               │
├─────────────────────────────────────────┤
│ - scan: Scanner                         │
│ - bankService: BankService              │
├─────────────────────────────────────────┤
│ + main(args)                            │
│ + mainMenu()                            │
│ - openAccount()       ← reads input,   │
│ - deposit()             calls service,  │
│ - withdraw()            prints result   │
│ - transfer()                            │
│ - viewBalance()                         │
│ - viewAccounts()                        │
│ - viewTransactions()                    │
│ - printStatement()                      │
│ - createCustomer()                      │
│ - selectCustomer()                      │
│ - viewAllCustomers()                    │
└────────────────┬────────────────────────┘
                 │ uses
                 ▼
┌─────────────────────────────────────────┐
│           BankService                   │
├─────────────────────────────────────────┤
│ - customers: HashMap<String, Customer>  │
│ - selectedCustomer: Customer            │
├─────────────────────────────────────────┤
│ + createCustomer(name): Customer        │
│ + selectCustomer(name): Customer        │
│ + getSelectedCustomer(): Customer       │
│ + getAllCustomers(): HashMap             │
│ + isCustomerSelected(): boolean         │
│ + hasAccounts(): boolean                │
│ + openSavingsAccount(): BankAccount     │
│ + openCheckingAccount(): BankAccount    │
│ + getAccounts(): List<BankAccount>      │
│ + deposit(accountIndex, amount): void   │
│ + withdraw(accountIndex, amount): bool  │
│ + transfer(fromIndex, toIndex, amt)     │
│ + getTransactions(accountIndex): List   │
│ + getAccount(index): BankAccount        │
│ + generateCustomerId(): String          │
│ + generateAccountNumber(): String       │
├─────────────────────────────────────────┤
│ + loadData(): void                      │
│ + saveData(): void                      │
└─────────────────────────────────────────┘
```

## Key Principles

- **BankApp** only handles: Scanner input, menu display, printing output
- **BankService** only handles: business logic, data, validation
- BankService methods **return** values — they never print to console
- BankService **throws exceptions** for errors — BankApp catches and displays them