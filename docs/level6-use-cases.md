# Level 6 — Use Cases

## Existing use cases (unchanged)
All Level 5 use cases remain. Level 6 adds data persistence.

## New use cases

| # | Use Case       | Description                                                                                      |
|---|----------------|--------------------------------------------------------------------------------------------------|
| 1 | Save on exit   | When the user exits the app (option 12), all customers, accounts, and transactions are saved to a file |
| 2 | Load on startup | When the app starts, it reads the file and restores all customers, accounts, and transactions    |
| 3 | Handle missing file | If no save file exists (first run), the app starts with an empty state — no error              |

## Save Rules
- All customers are saved with their ID and name
- All accounts are saved with their type (Savings/Checkings), account number, and balance
- All transactions are saved with their type, amount, and timestamp
- Accounts must be linked back to the correct customer
- Transactions must be linked back to the correct account

## Load Rules
- The file is read line by line and objects are reconstructed in order: customers first, then accounts, then transactions
- Each account is added to its owning customer
- Each transaction is added to its owning account
- The balance is restored directly (not recalculated from transactions)