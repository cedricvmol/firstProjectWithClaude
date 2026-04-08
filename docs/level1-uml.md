# Level 1 — UML Class Diagram

```
┌──────────────────────────────┐
│         domain.BankAccount          │
├──────────────────────────────┤
│ - owner: String              │
│ - balance: double            │
├──────────────────────────────┤
│ + domain.BankAccount(owner: String) │
│ + deposit(amount: double)    │
│ + withdraw(amount: double)   │
│ + getBalance(): double       │
│ + getOwner(): String         │
├──────────────────────────────┤
└──────────────────────────────┘

┌──────────────────────────────┐
│        app.BankApp (main)        │
├──────────────────────────────┤
│ + main(args: String[])       │
├──────────────────────────────┤
│ - Shows a menu               │
│ - Reads user input           │
│ - Calls domain.BankAccount methods  │
└──────────────────────────────┘
```

## Notes
- `-` means private
- `+` means public
- Balance starts at 0.0
- withdraw should not allow withdrawing more than the balance
