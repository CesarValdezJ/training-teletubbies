package org.example;

/** Raised when an order line carries a quantity that is not a valid whole number. */
public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(String product, String rawQuantity, Throwable cause) {
        super("line '" + product + "' has an invalid quantity: '" + rawQuantity + "'", cause);
    }
}
