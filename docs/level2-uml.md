# Level 2 — UML Class Diagram

```
┌─────────────────────────────────────┐
│          BankAccount (abstract)     │
├─────────────────────────────────────┤
│ - accountNumber: String             │
│ - balance: double                   │
├─────────────────────────────────────┤
│ + BankAccount(accountNumber: String)│
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
│SavingsAccount│  │CheckingAccount  │
├──────────────┤  ├─────────────────┤
│              │  │ - OVERDRAFT_    │
│              │  │   LIMIT: double │
│              │  │   = -500.0      │
├──────────────┤  ├─────────────────┤
│ + withdraw() │  │ + withdraw()    │
│ + getType()  │  │ + getType()     │
└──────────────┘  └─────────────────┘

┌──────────────────────────────────────────┐
│              Customer                    │
├──────────────────────────────────────────┤
│ - name: String                           │
│ - customerId: String                     │
│ - accounts: List<BankAccount>            │
├──────────────────────────────────────────┤
│ + Customer(name: String, id: String)     │
│ + addAccount(account: BankAccount): void │
│ + getAccounts(): List<BankAccount>       │
│ + getName(): String                      │
│ + getCustomerId(): String                │
└──────────────────────────────────────────┘
```

## Notes
- `BankAccount` is now abstract — cannot be instantiated directly
- `getType()` is abstract — each subclass returns its own type name
- `withdraw()` is overridden in each subclass with different rules
- `withdraw()` returns boolean — true if successful, false if denied
- `SavingsAccount`: no overdraft allowed (balance cannot go below 0)
- `CheckingAccount`: allows overdraft down to -500.0
- `deposit()` should validate that amount is positive
- `Customer` holds a List<BankAccount> — needs `import java.util.ArrayList`