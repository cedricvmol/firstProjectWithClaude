# Java Learning Roadmap — app.BankApp

## Goal
Relearn Java by building a app.BankApp from simple to complex, level by level. Each level introduces new concepts.

---

## app.BankApp Levels

### Level 1 — The Basics [DONE]
- **What:** Single domain.BankAccount, simple menu (deposit, withdraw, balance, exit)
- **Concepts:** Classes, fields, methods, constructors, Scanner, loops, switch

### Level 2 — Inheritance & Customers [DONE]
- **What:** Abstract domain.BankAccount, SavingsAccount, domain.CheckingAccount (overdraft), domain.Customer class
- **Concepts:** Abstract classes, inheritance, method overriding, polymorphism, ArrayList

### Level 3 — Multiple Customers & Error Handling [DONE]
- **What:** Support multiple customers, input validation, handle bad input gracefully
- **Concepts:** HashMap, try-catch, exceptions, input validation

### Level 4 — Unit Testing with JUnit [DONE]
- **What:** Write tests for all domain classes (domain.BankAccount, domain.SavingAccount, domain.CheckingAccount, domain.Customer)
- **Concepts:** JUnit 5, assertions, @Test annotation, test structure (Arrange-Act-Assert), Maven/Gradle for dependencies

### Level 5 — Interfaces & Transactions [DONE]
- **What:** domain.Transaction history, transferring between accounts, printable statements
- **Concepts:** Interfaces, enums, date/time API, formatting output

### Level 6 — File Storage [DONE]
- **What:** Save and load customers/accounts to file so data persists between runs
- **Concepts:** File I/O, serialization, reading/writing text or JSON

### Level 7 — Refactor & Polish [DONE]
- **What:** Clean up code, organize into packages, fix known issues
- **Concepts:** Packages, access modifiers, separation of concerns
- **Done:**
  - Organized classes into `domain`, `app`, `storage` packages
  - Added `addTransaction()` on `BankAccount` for proper encapsulation
  - Stored `balanceAfterTransaction` on `Transaction` instead of recalculating in `printStatement()`
  - Split `TRANSFER` into `TRANSFER_IN` / `TRANSFER_OUT` to distinguish source vs destination

### Level 8 — Domain Controller / Service Layer
- **What:** Extract business logic out of app.BankApp into a dedicated BankService (DomainController) class
- **Concepts:** Separation of concerns, service layer pattern, UI layer vs domain layer, dependency injection basics

### Level 9 — Database Storage
- **What:** Replace file storage with a database, store and retrieve customers, accounts, and transactions
- **Concepts:** JDBC, SQL basics (CREATE, INSERT, SELECT, UPDATE), SQLite or MySQL, connection management

---

## After app.BankApp
Once all levels are complete, pick a personal project and build it from scratch!