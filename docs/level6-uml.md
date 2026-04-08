# Level 6 — UML Class Diagram

## New class

```
┌──────────────────────────────────────────┐
│            storage.FileManager                   │
├──────────────────────────────────────────┤
│ - FILE_PATH: String = "data/bank.txt"   │
├──────────────────────────────────────────┤
│ + saveData(customers: HashMap): void     │
│ + loadData(): HashMap<String, domain.Customer>  │
└──────────────────────────────────────────┘
```

## Updated classes

```
┌──────────────────────────────────────────┐
│           domain.BankAccount (abstract)         │
├──────────────────────────────────────────┤
│ (existing fields unchanged)              │
├──────────────────────────────────────────┤
│ (existing methods unchanged)             │
│ + setBalance(amount: double): void       │   ← new (needed to restore saved balance)
└──────────────────────────────────────────┘

┌──────────────────────────────────────────┐
│              app.BankApp                     │
├──────────────────────────────────────────┤
│ (existing fields unchanged)              │
├──────────────────────────────────────────┤
│ (existing methods unchanged)             │
│ — mainMenu(): exit now calls save        │   ← modified
│ — main(): startup now calls load         │   ← modified
└──────────────────────────────────────────┘
```

## File Format

Plain text, one line per record. Use a delimiter (e.g. `;`) to separate fields.

```
CUSTOMER;customerId;name
ACCOUNT;customerId;accountNumber;type;balance
TRANSACTION;accountNumber;type;amount;dateTime
```

## Key Concepts
- **File I/O**: `BufferedWriter`/`FileWriter` to write, `BufferedReader`/`FileReader` to read
- **File structure**: choosing a simple text format that can represent your object graph
- **Reconstruction**: parsing lines back into objects, reconnecting accounts to customers
- **Directory creation**: ensuring the `data/` folder exists before writing