    # Level 2 — UML Class Diagram

```
┌─────────────────────────────────────┐
│          domain.BankAccount (abstract)     │
├─────────────────────────────────────┤
│ - accountNumber: String             │
│ - balance: double                   │
├─────────────────────────────────────┤
│ + domain.BankAccount(accountNumber: String)│
│ + deposit(amount: double): void     │
│ + withdraw(amount: double): boolean │
│ + getBalance(): double              │
│ + getAccountNumber(): String        │
│ + getType(): String (abstract)      │
└──────────────┬──────────────────────┘
               │
       ┌───────┴────────┐
       │                 │
┌──────┴───────┐  ┌──────┴──────────┐
│SavingsAccount│  │domain.CheckingAccount  │
├──────────────┤  ├─────────────────┤
│              │  │ - OVERDRAFT_    │
│              │  │   LIMIT: double │
│              │  │   = -500.0      │
├──────────────┤  ├─────────────────┤
│ + withdraw() │  │ + withdraw()    │
│ + getType()  │  │ + getType()     │
└──────────────┘  └─────────────────┘

┌──────────────────────────────────────────┐
│              domain.Customer                    │
├──────────────────────────────────────────┤
│ - name: String                           │
│ - customerId: String                     │
│ - accounts: List<domain.BankAccount>            │
├──────────────────────────────────────────┤
│ + domain.Customer(name: String, id: String)     │
│ + addAccount(account: domain.BankAccount): void │
│ + getAccounts(): List<domain.BankAccount>       │
│ + getName(): String                      │
│ + getCustomerId(): String                │
└──────────────────────────────────────────┘
```

## Notes
- `domain.BankAccount` is now abstract — cannot be instantiated directly
- `getType()` is abstract — each subclass returns its own type name
- `withdraw()` is overridden in each subclass with different rules
- `withdraw()` returns boolean — true if successful, false if denied
- `SavingsAccount`: no overdraft allowed (balance cannot go below 0)
- `domain.CheckingAccount`: allows overdraft down to -500.0
- `deposit()` should validate that amount is positive
- `domain.Customer` holds a List<domain.BankAccount> — needs `import java.util.ArrayList`