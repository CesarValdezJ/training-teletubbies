package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Starter test class. Finish the homework with AT LEAST FOUR tests, one per defect plus the
 * happy path. Follow Arrange - Act - Assert, and use assertThrows for the rejection cases.
 */
class BankAccountTest {

    @Test
    void deposit_increasesBalance() {
        // Arrange
        BankAccount account = new BankAccount(100.0);
        // Act
        account.deposit(25.0);
        // Assert
        assertEquals(125.0, account.getBalance(), 1e-9);
    }

    // TODO defect 1: withdraw() more than the balance -> should be rejected, balance unchanged.
    // TODO defect 2: deposit() a negative amount -> should be rejected.
    // TODO defect 3: transferTo() must obey the same overdraft rule as withdraw().
    // TODO happy path: a valid transferTo() moves the money and leaves both balances right.
}
