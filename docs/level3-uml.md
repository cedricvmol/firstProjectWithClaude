pr# Level 3 — UML Class Diagram

```
┌──────────────────────────────────────────────┐
│               app.BankApp (main)                 │
├──────────────────────────────────────────────┤
│ - scan: Scanner (static)                     │
│ - customers: HashMap<String, domain.Customer>       │
│ - selectedCustomer: domain.Customer                 │
├──────────────────────────────────────────────┤
│ + main(args: String[]): void                 │
│ + mainMenu(): void                           │
│ + createCustomer(): void                     │
│ + selectCustomer(): void                     │
│ + openAccount(): void                        │
│ + deposit(): void                            │
│ + withdraw(): void                           │
│ + viewBalance(): void                        │
│ + viewAccounts(): void                       │
│ + viewAllCustomers(): void                   │
└──────────────────────────────────────────────┘

┌─────────────────────────────────────┐
│       domain.BankAccount (abstract)        │
├─────────────────────────────────────┤
│  (unchanged from level 2)           │
└──────────────┬──────────────────────┘
               │
       ┌───────┴────────┐
       │                │
┌──────┴───────┐  ┌─────┴───────────┐
│SavingsAccount│  │domain.CheckingAccount  │
├──────────────┤  ├─────────────────┤
│ (unchanged)  │  │ (unchanged)     │
└──────────────┘  └─────────────────┘

┌──────────────────────────────────────────┐
│              domain.Customer                    │
├──────────────────────────────────────────┤
│  (unchanged from level 2)                │
└──────────────────────────────────────────┘
```

## What's new in Level 3
- `app.BankApp` now holds a `HashMap<String, domain.Customer>` — key is customerId, value is domain.Customer
- `selectedCustomer` tracks which customer is currently active
- Methods no longer pass `customer` as a parameter — they use `selectedCustomer`
- All user input is wrapped in try-catch blocks to handle `InputMismatchException`
- Account selection validates that the chosen index is within bounds

## Key Concepts
- **HashMap**: stores key-value pairs, fast lookup by key — `customers.get(id)` finds a customer instantly
- **try-catch**: wraps code that might fail, catches the exception and handles it gracefully instead of crashing
- **InputMismatchException**: thrown when Scanner expects a number but gets text
- **IndexOutOfBoundsException**: thrown when accessing a list with an invalid index