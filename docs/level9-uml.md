# Level 9 — Database Storage UML

## Class Diagram

```
┌─────────────────────────────────────────┐
│              BankApp (UI)               │
├─────────────────────────────────────────┤
│  (no changes from level 8)             │
└────────────────┬────────────────────────┘
                 │ uses
                 ▼
┌─────────────────────────────────────────┐
│           BankService                   │
├─────────────────────────────────────────┤
│  (no changes from level 8)             │
│  loadData() now calls DatabaseManager  │
│  saveData() now calls DatabaseManager  │
└────────────────┬────────────────────────┘
                 │ uses
                 ▼
┌─────────────────────────────────────────┐
│        DatabaseManager (replaces        │
│            FileManager)                 │
├─────────────────────────────────────────┤
│ - CONNECTION_URL: String               │
├─────────────────────────────────────────┤
│ + createTables(): void                 │
│ + saveData(customers: HashMap): void   │
│ + loadData(): HashMap<String,Customer> │
│ - getConnection(): Connection          │
│ - saveCustomer(conn, customer): void   │
│ - saveAccount(conn, custId, acc): void │
│ - saveTransaction(conn, accNr, t): void│
└─────────────────────────────────────────┘
```

## Database Tables Needed

You need 3 tables that mirror your domain objects:
- A table for **customers**
- A table for **accounts** (linked to a customer)
- A table for **transactions** (linked to an account)

Think about what columns each table needs based on your domain classes.

## Key Principles

- **DatabaseManager** has the same public interface as FileManager (`saveData`, `loadData`)
- BankService only changes which class it calls — the rest of the app stays the same
- Use **JDBC** (`java.sql.*`) to connect to SQLite
- Use **prepared statements** (not string concatenation) for all SQL — prevents SQL injection
- `createTables()` uses `CREATE TABLE IF NOT EXISTS` so it's safe to call every startup
- `saveData()` should clear existing data and re-insert (simplest approach for this level)
- `loadData()` reconstructs the full object graph: customers -> accounts -> transactions