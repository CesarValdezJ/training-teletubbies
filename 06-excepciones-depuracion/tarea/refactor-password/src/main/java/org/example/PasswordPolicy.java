package org.example;

/**
 * Decides whether a password is acceptable for a given username. Returns one of:
 * "TOO_SHORT", "NO_DIGIT", "NO_UPPERCASE", "CONTAINS_USERNAME", "OK".
 *
 * NOTE (homework): this WORKS but it is ugly - nested ifs, magic numbers, one-letter names,
 * an empty catch. Refactor it applying the clean-code practices from the theory slides
 * WITHOUT changing its behavior. The tests in PasswordPolicyTest must stay green.
 */


public class PasswordPolicy {

    private static final int MIN_PASSWORD_LENGTH = 8;

    public String check(String password, String username) {

        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            return "TOO_SHORT";
        }

        if (!containsDigit(password)) {
            return "NO_DIGIT";
        }

        if (!containsUppercase(password)) {
            return "NO_UPPERCASE";
        }

        if (containsUsername(password, username)) {
            return "CONTAINS_USERNAME";
        }

        return "OK";
    }

    private boolean containsDigit(String password) {
        for (char character : password.toCharArray()) {
            if (character >= '0' && character <= '9') {
                return true;
            }
        }

        return false;
    }

    private boolean containsUppercase(String password) {
        for (char character : password.toCharArray()) {
            if (character >= 'A' && character <= 'Z') {
                return true;
            }
        }

        return false;
    }

    private boolean containsUsername(String password, String username) {
        return username != null
                && password.toLowerCase().contains(username.toLowerCase());
    }
}

