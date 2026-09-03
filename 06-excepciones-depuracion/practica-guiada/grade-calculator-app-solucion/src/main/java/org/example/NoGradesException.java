package org.example;

/** Raised when a grade statistic is requested for a student (or group) that has no grades. */
public class NoGradesException extends RuntimeException {
    public NoGradesException(String who) {
        super("'" + who + "' has no grades to average");
    }
}
