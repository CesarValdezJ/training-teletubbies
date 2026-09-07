package org.example;

/**
 * A very small bank account.
 *
 * NOTE (homework): this class has defects. Find them, fix them with explicit validation
 * and a custom exception, and cover each one with a unit test.
 */
public class BankAccount {

    private double balance;

    public BankAccount(double openingBalance) {
        this.balance = openingBalance;
    }

    public double getBalance() {
        return balance;
    }

    /** Add money to the account. */
    public void deposit(double amount) {
        // DEFECT: a negative amount is accepted and quietly drains the account.
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        balance += amount;
    }

    /** Take money out of the account. */
    public void withdraw(double amount) {
        // DEFECT: no check for sufficient funds - the balance can go negative.
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }

    /** Move money from this account to another one. */
    public void transferTo(BankAccount target, double amount) {
        if (target == null) {
            throw new IllegalArgumentException(
                    "Target account cannot be null"
            );
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount cannot be zero or negative");
        }

        withdraw(amount);
        target.deposit(amount);
    }
}
