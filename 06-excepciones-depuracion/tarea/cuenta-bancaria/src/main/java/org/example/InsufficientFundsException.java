package org.example;


public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(double amount, double balance) {
        super("Insufficient funds. Amount: " + amount
                + ", balance: " + balance);
    }
}
