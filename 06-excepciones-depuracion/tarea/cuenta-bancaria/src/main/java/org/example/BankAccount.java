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

    final public double getBalance() {
        return balance;
    }

    /** Add money to the account. */
    final public void deposit(final double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");//debe ser mayor que 0, no "no negativo".
        }
        // DEFECT: a negative amount is accepted and quietly drains the account.
        balance += amount;
    }

    /** Take money out of the account. */
    public void withdraw(final double amount) {
        // DEFECT: no check for sufficient funds - the balance can go negative.
        if(amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }
        if (amount>balance) {
            throw new InsufficientFundsException("Insufficient funds: tried to withdraw" + amount+", but balance is "+ balance);
        }
        balance -= amount;
    }


    /** Move money from this account to another one. */
    public void transferTo(final BankAccount target, final double amount) {
        // DEFECT: touches the balances directly instead of reusing withdraw()/deposit(),
        if (target==null){
            throw new IllegalArgumentException("Target account cannot be null");
        }

        // so any rule added to those methods is bypassed here.
        withdraw(amount);
        target.deposit(amount);

    }
}
