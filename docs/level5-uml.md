# Level 5 — UML Class Diagram

## New classes

```
«enumeration»
┌─────────────────────┐
│  TransactionType    │
├─────────────────────┤
│  DEPOSIT            │
│  WITHDRAWAL         │
│  TRANSFER           │
└─────────────────────┘

┌──────────────────────────────────┐
│           Transaction            │
├──────────────────────────────────┤
│ - type: TransactionType          │
│ - amount: double                 │
│ - dateTime: LocalDateTime        │
├──────────────────────────────────┤
│ + Transaction(type, amount)      │
│ + getType(): TransactionType     │
│ + getAmount(): double            │
│ + getDateTime(): LocalDateTime   │
│ + toString(): String             │
└──────────────────────────────────┘

«interface»
┌──────────────────────────────────┐
│          Printable               │
├──────────────────────────────────┤
│ + printStatement(): void         │
└──────────────────────────────────┘
```

## Updated classes

```
┌──────────────────────────────────────────┐
│           BankAccount (abstract)         │
├──────────────────────────────────────────┤
│ - balance: double                        │
│ - accountNumber: String                  │
│ - transactions: List<Transaction>        │   ← new
├──────────────────────────────────────────┤
│ + deposit(amount): void                  │   ← records Transaction
│ + withdraw(amount): boolean              │   ← records Transaction
│ + getTransactions(): List<Transaction>   │   ← new
│ + printStatement(): void                 │   ← implements Printable
│ + getBalance(): double                   │
│ + getAccountNumber(): String             │
│ + getType(): String                      │
└──────────────────────────────────────────┘
         implements ▲
                    │
              «interface»
              Printable

┌──────────────────────────────────────────┐
│              BankApp                     │
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
- **Interface**: defines a contract (`Printable`) — any class that implements it must provide `printStatement()`
- **Enum**: `TransactionType` — a fixed set of named constants, safer than using plain strings
- **LocalDateTime**: from `java.time` — captures date and time of each transaction
- **DateTimeFormatter**: formats `LocalDateTime` into a readable string for output