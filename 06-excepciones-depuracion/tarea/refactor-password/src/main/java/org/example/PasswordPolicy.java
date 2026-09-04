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

    private static final int MIN_LENGTH = 8;
    private static final String TOO_SHORT = "TOO_SHORT";
    private static final String NO_DIGIT = "NO_DIGIT";
    private static final String NO_UPPERCASE = "NO_UPPERCASE";
    private static final String CONTAINS_USERNAME = "CONTAINS_USERNAME";
    private static final String OK = "OK";

    public String check(final String password, final String username) {
        if (password == null) {
            return TOO_SHORT;
        }
        if (isTooShort(password)) {
            return TOO_SHORT;
        }
        if (!hasDigit(password)) {
            return NO_DIGIT;
        }
        if (!hasUppercase(password)) {
            return NO_UPPERCASE;
        }
        if (containsUsername(password, username)) {
            return CONTAINS_USERNAME;
        }
        return OK;
    }
    private boolean isTooShort(String password) {
        return password.length() < MIN_LENGTH;
    }

    private boolean hasDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                return true;
            }
        }

        return false;
    }

    private boolean hasUppercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                return true;
            }
        }
        return false;
    }

    private boolean containsUsername(String password, String username) {
        return username != null && password.toLowerCase().contains(username.toLowerCase());
    }
}