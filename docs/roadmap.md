# Java Learning Roadmap — BankApp

## Goal
Relearn Java by building a BankApp from simple to complex, level by level. Each level introduces new concepts.

---

## BankApp Levels

### Level 1 — The Basics [DONE]
- **What:** Single BankAccount, simple menu (deposit, withdraw, balance, exit)
- **Concepts:** Classes, fields, methods, constructors, Scanner, loops, switch

### Level 2 — Inheritance & Customers [DONE]
- **What:** Abstract BankAccount, SavingsAccount, CheckingAccount (overdraft), Customer class
- **Concepts:** Abstract classes, inheritance, method overriding, polymorphism, ArrayList

### Level 3 — Multiple Customers & Error Handling [DONE]
- **What:** Support multiple customers, input validation, handle bad input gracefully
- **Concepts:** HashMap, try-catch, exceptions, input validation

### Level 4 — Unit Testing with JUnit [DONE]
- **What:** Write tests for all domain classes (BankAccount, SavingAccount, CheckingAccount, Customer)
- **Concepts:** JUnit 5, assertions, @Test annotation, test structure (Arrange-Act-Assert), Maven/Gradle for dependencies

### Level 5 — Interfaces & Transactions
- **What:** Transaction history, transferring between accounts, printable statements
- **Concepts:** Interfaces, enums, date/time API, formatting output

### Level 6 — File Storage
- **What:** Save and load customers/accounts to file so data persists between runs
- **Concepts:** File I/O, serialization, reading/writing text or JSON

### Level 7 — Refactor & Polish
- **What:** Clean up code, separate concerns, add a proper service layer
- **Concepts:** Packages, access modifiers, separation of concerns, design patterns
- **Known issues to address:**
  - Running balance calculation lives inside `printStatement()` — should be extracted as separate logic
  - TRANSFER transactions don't distinguish between source (money out) and destination (money in) — both accounts get the same Transaction object

### Level 8 — Database Storage
- **What:** Replace file storage with a database, store and retrieve customers, accounts, and transactions
- **Concepts:** JDBC, SQL basics (CREATE, INSERT, SELECT, UPDATE), SQLite or MySQL, connection management

---

## After BankApp
Once all levels are complete, pick a personal project and build it from scratch!