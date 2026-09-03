package org.example;

/** Reproduces two of the defects. Run it and read the output. */
public class Main {
    public static void main(String[] args) {
        BankAccount a = new BankAccount(100.0);
        a.withdraw(150.0);
        System.out.println("Balance after over-withdrawing 150 from 100: " + a.getBalance());
        // prints -50.0 -> the withdrawal should have been rejected

        BankAccount b = new BankAccount(100.0);
        b.deposit(-30.0);
        System.out.println("Balance after depositing -30: " + b.getBalance());
        // prints 70.0 -> a negative deposit should have been rejected

        BankAccount source = new BankAccount(50.0);
        BankAccount dest = new BankAccount(0.0);
        source.transferTo(dest, 200.0);
        System.out.println("Source after transferring 200 it does not have: " + source.getBalance());
        // prints -150.0 -> transferTo bypasses the (missing) overdraft rule
    }
}
