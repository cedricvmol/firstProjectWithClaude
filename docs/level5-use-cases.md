# Level 5 — Use Cases

## Existing use cases (unchanged)
All Level 3 use cases remain. Level 5 adds transaction tracking and transfers.

## New use cases

| # | Use Case              | Description                                                                                      |
|---|-----------------------|--------------------------------------------------------------------------------------------------|
| 1 | View transaction history | Selected customer views all transactions on a specific account (type, amount, date/time)      |
| 2 | Transfer between accounts | Transfer money from one account to another belonging to the same customer                   |
| 3 | Print account statement  | Print a formatted statement showing all transactions with running balance for an account      |

## domain.Transaction Rules
- Every deposit and withdrawal is recorded as a domain.Transaction
- A domain.Transaction stores: type (DEPOSIT / WITHDRAWAL / TRANSFER), amount, and timestamp
- Transactions are immutable — they are never edited after creation
- Transfer records a TRANSFER transaction on both the source and destination account