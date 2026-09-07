package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Test
    void withdraw_moreThanBalance_shouldBeRejected() {
        // Arrange
        BankAccount account = new BankAccount(100.0);
        // Act & Assert
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(150.0));
        assertEquals(100.0, account.getBalance(), 1e-9);
    }
    // TODO defect 2: deposit() a negative amount -> should be rejected.
    @Test
    void deposit_negativeAmount_shouldBeRejected() {
        // Arrange
        BankAccount account = new BankAccount(100.0);
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-25.0));
        assertEquals(100.0, account.getBalance(), 1e-9);
    }

    // TODO defect 3: transferTo() must obey the same overdraft rule as withdraw().
    @Test
    void transferTo_moreThanBalance_shouldBeRejected() {
        // Arrange
        BankAccount source = new BankAccount(50.0);
        BankAccount dest = new BankAccount(0.0);
        // Act & Assert
        assertThrows(InsufficientFundsException.class, () -> source.transferTo(dest, 200.0));
        assertEquals(50.0, source.getBalance(), 1e-9);
        assertEquals(0.0, dest.getBalance(), 1e-9);
    }

    // TODO happy path: a valid transferTo() moves the money and leaves both balances right.
    @Test
    void transferTo_validAmount_shouldSucceed() {
        // Arrange
        BankAccount source = new BankAccount(100.0);
        BankAccount dest = new BankAccount(50.0);
        // Act
        source.transferTo(dest, 30.0);
        // Assert
        assertEquals(70.0, source.getBalance(), 1e-9);
        assertEquals(80.0, dest.getBalance(), 1e-9);
    }
}
