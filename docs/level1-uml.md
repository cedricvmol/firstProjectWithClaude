# Level 1 — UML Class Diagram

```
┌──────────────────────────────┐
│         BankAccount          │
├──────────────────────────────┤
│ - owner: String              │
│ - balance: double            │
├──────────────────────────────┤
│ + BankAccount(owner: String) │
│ + deposit(amount: double)    │
│ + withdraw(amount: double)   │
│ + getBalance(): double       │
│ + getOwner(): String         │
├──────────────────────────────┤
└──────────────────────────────┘

┌──────────────────────────────┐
│        BankApp (main)        │
├──────────────────────────────┤
│ + main(args: String[])       │
├──────────────────────────────┤
│ - Shows a menu               │
│ - Reads user input           │
│ - Calls BankAccount methods  │
└──────────────────────────────┘
```

## Notes
- `-` means private
- `+` means public
- Balance starts at 0.0
- withdraw should not allow withdrawing more than the balance
