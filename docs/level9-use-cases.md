# Level 9 — Database Storage Use Cases

## Setup
1. Add SQLite JDBC driver to the project (via IntelliJ Project Structure > Libraries > From Maven)
   - Search for: `org.xerial:sqlite-jdbc`
   - Pick the latest version
2. Create `DatabaseManager` class in the `storage` package

## Use Case 1: Create Tables on Startup
- When the app starts, `createTables()` is called
- Creates `customers`, `accounts`, and `transactions` tables if they don't exist
- Uses `CREATE TABLE IF NOT EXISTS` so it's safe to run every time

## Use Case 2: Save All Data on Exit
- When the user exits (menu option 12), all data is saved to the database
- Clear existing rows first (DELETE FROM each table)
- Insert all customers, their accounts, and their transactions
- All inserts happen inside a single transaction (commit/rollback)

## Use Case 3: Load All Data on Startup
- When the app starts, load all customers from the database
- For each customer, load their accounts
- For each account, load their transactions
- Reconstruct the same HashMap<String, Customer> that FileManager returned

## Implementation Order
1. Add SQLite dependency to the project
2. Create `DatabaseManager` with `getConnection()` and `createTables()`
3. Implement `saveData()` — test by running the app, creating data, and exiting
4. Implement `loadData()` — test by restarting the app and verifying data persists
5. Update `BankService` to use `DatabaseManager` instead of `FileManager`
6. Test the full flow: create customer → open account → deposit → exit → restart → verify