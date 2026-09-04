package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

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
        assertThat(account.getBalance())
                .isCloseTo(125.0, within(1e-9));

    }
    @Test
    void deposit_negativeAmount_shouldThrow() {
        // Arrange
       final BankAccount account = new BankAccount(100.0);
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-30.0));
    }
    @Test
    void withdraw_moreThanBalance_shouldThrow() {
        // Arrange
        final BankAccount account = new BankAccount(100.0);
        // Act & Assert
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(150.0));
        assertThat(account.getBalance())
                .isCloseTo(100.0, within(1e-9));
    }
    @Test
    void transferTo_validTransfer_shouldMoveMoney() {
        // Arrange
        final BankAccount source = new BankAccount(200.0);
        final BankAccount target = new BankAccount(50.0);
        // Act
        source.transferTo(target, 100.0);
        // Assert
        assertThat(source.getBalance())
                .isCloseTo(100.0, within(1e-9));
        assertThat(target.getBalance())
                .isCloseTo(150.0, within(1e-9));
    }
    @Test
    void transferTo_moreThanBalance_shouldThrow() {
        // Arrange
        final BankAccount source = new BankAccount(100.0);
        final BankAccount target = new BankAccount(50.0);

        // Act & Assert
        assertThrows(
                InsufficientFundsException.class,
                () -> source.transferTo(target, 150.0)
        );

        // Assert
        assertThat(source.getBalance())
                .isCloseTo(100.0, within(1e-9));
        assertThat(target.getBalance())
                .isCloseTo(50.0, within(1e-9));
    }


    // TODO defect 1: withdraw() more than the balance -> should be rejected, balance unchanged.
    // TODO defect 2: deposit() a negative amount -> should be rejected.
    // TODO defect 3: transferTo() must obey the same overdraft rule as withdraw().
    // TODO happy path: a valid transferTo() moves the money and leaves both balances right.
}
