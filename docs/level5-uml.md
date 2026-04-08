# Level 5 — UML Class Diagram

## New classes

```
«enumeration»
┌─────────────────────┐
│  domain.TransactionType    │
├─────────────────────┤
│  DEPOSIT            │
│  WITHDRAWAL         │
│  TRANSFER           │
└─────────────────────┘

┌──────────────────────────────────┐
│           domain.Transaction            │
├──────────────────────────────────┤
│ - type: domain.TransactionType          │
│ - amount: double                 │
│ - dateTime: LocalDateTime        │
├──────────────────────────────────┤
│ + domain.Transaction(type, amount)      │
│ + getType(): domain.TransactionType     │
│ + getAmount(): double            │
│ + getDateTime(): LocalDateTime   │
│ + toString(): String             │
└──────────────────────────────────┘

«interface»
┌──────────────────────────────────┐
│          domain.Printable               │
├──────────────────────────────────┤
│ + printStatement(): void         │
└──────────────────────────────────┘
```

## Updated classes

```
┌──────────────────────────────────────────┐
│           domain.BankAccount (abstract)         │
├──────────────────────────────────────────┤
│ - balance: double                        │
│ - accountNumber: String                  │
│ - transactions: List<domain.Transaction>        │   ← new
├──────────────────────────────────────────┤
│ + deposit(amount): void                  │   ← records domain.Transaction
│ + withdraw(amount): boolean              │   ← records domain.Transaction
│ + getTransactions(): List<domain.Transaction>   │   ← new
│ + printStatement(): void                 │   ← implements domain.Printable
│ + getBalance(): double                   │
│ + getAccountNumber(): String             │
│ + getType(): String                      │
└──────────────────────────────────────────┘
         implements ▲
                    │
              «interface»
              domain.Printable

┌──────────────────────────────────────────┐
│              app.BankApp                     │
├──────────────────────────────────────────┤
│ (existing fields unchanged)              │
├──────────────────────────────────────────┤
│ (existing methods unchanged)             │
│ + transfer(): void                       │   ← new
│ + viewTransactions(): void               │   ← new
│ + printStatement(): void                 │   ← new
└──────────────────────────────────────────┘
```

## Key Concepts
- **Interface**: defines a contract (`domain.Printable`) — any class that implements it must provide `printStatement()`
- **Enum**: `domain.TransactionType` — a fixed set of named constants, safer than using plain strings
- **LocalDateTime**: from `java.time` — captures date and time of each transaction
- **DateTimeFormatter**: formats `LocalDateTime` into a readable string for output