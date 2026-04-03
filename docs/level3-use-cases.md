# Level 3 — Use Cases

| # | Use Case          | Description                                                                                  |
|---|-------------------|----------------------------------------------------------------------------------------------|
| 1 | Create customer   | System creates a customer with a name and generated ID, stored in a map of customers         |
| 2 | Select customer   | User selects an existing customer by ID or from a list to perform actions on                  |
| 3 | Open account      | Selected customer can open a Savings or Checking account                                     |
| 4 | Deposit money     | Selected customer deposits into a specific account                                           |
| 5 | Withdraw money    | Selected customer withdraws with overdraft rules                                             |
| 6 | Check balance     | Selected customer views balance of a specific account                                        |
| 7 | View accounts     | Selected customer sees all their accounts                                                    |
| 8 | View all customers| User sees a list of all customers in the system                                              |
| 9 | Exit              | User quits the application                                                                   |

## Error Handling Rules
- All numeric input is wrapped in try-catch — invalid input shows a message and returns to menu
- Selecting a non-existent customer shows an error
- Selecting a non-existent account shows an error
- All actions requiring a customer check if one is selected first