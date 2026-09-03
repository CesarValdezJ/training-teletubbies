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
        balance += amount;
    }

    /** Take money out of the account. */
    public void withdraw(double amount) {
        // DEFECT: no check for sufficient funds - the balance can go negative.
        balance -= amount;
    }

    /** Move money from this account to another one. */
    public void transferTo(BankAccount target, double amount) {
        // DEFECT: touches the balances directly instead of reusing withdraw()/deposit(),
        // so any rule added to those methods is bypassed here.
        this.balance -= amount;
        target.balance += amount;
    }
}
