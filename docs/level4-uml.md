# Level 4 — UML Class Diagram

No new domain classes in this level. Level 4 adds a test suite alongside the existing classes.

```
┌──────────────────────────┐       tests       ┌──────────────────────────────┐
│     BankAccount          │◄──────────────────│   BankAccountTest            │
│     (abstract)           │                   ├──────────────────────────────┤
└──────────┬───────────────┘                   │ - account: SavingAccount     │
           │                                   ├──────────────────────────────┤
   ┌───────┴────────┐                          │ + setUp(): void (@BeforeEach)│
   │                │                          │ + testInitialBalanceIsZero() │
┌──┴────────────┐  ┌┴─────────────────┐        │ + testDepositIncreasesBalance│
│ SavingAccount │  │ CheckingAccount  │        │ + testNegativeDepositIgnored │
└──────┬────────┘  └────────┬─────────┘        └──────────────────────────────┘
       │                    │
       │ tests              │ tests
       ▼                    ▼
┌──────────────────┐   ┌───────────────────────────┐
│ SavingAccountTest│   │ CheckingAccountTest        │
├──────────────────┤   ├───────────────────────────┤
│- account:        │   │- account: CheckingAccount  │
│  SavingAccount   │   ├───────────────────────────┤
├──────────────────┤   │+ setUp() (@BeforeEach)     │
│+ setUp()         │   │+ testWithdrawWithin        │
│  (@BeforeEach)   │   │  Overdraft()               │
│+ testWithdraw    │   │+ testWithdrawBeyond        │
│  SufficientFunds │   │  Overdraft()               │
│+ testWithdraw    │   │+ testGetType()             │
│  InsufficientFunds    └───────────────────────────┘
│+ testGetType()   │
└──────────────────┘

┌──────────────────┐       tests       ┌──────────────────────────────┐
│    Customer      │◄──────────────────│   CustomerTest               │
└──────────────────┘                   ├──────────────────────────────┤
                                       │ - customer: Customer         │
                                       ├──────────────────────────────┤
                                       │ + setUp() (@BeforeEach)      │
                                       │ + testAddAccount()           │
                                       │ + testAddNullAccountIgnored()│
                                       │ + testGetName()              │
                                       └──────────────────────────────┘
```

## What's new in Level 4
- Test classes live in a separate `test/` source root
- Each domain class gets its own test class (naming convention: `ClassNameTest`)
- Tests use JUnit 5 — added as a dependency via Maven or as a library in IntelliJ

## Key Concepts
- **@Test**: marks a method as a test case
- **Assertions**: `assertEquals`, `assertTrue`, `assertFalse`, `assertThrows` — verify expected outcomes
- **Arrange-Act-Assert (AAA)**: structure of every test — set up, run the thing, check the result
- **Test isolation**: each test method sets up its own state, no shared mutable state between tests