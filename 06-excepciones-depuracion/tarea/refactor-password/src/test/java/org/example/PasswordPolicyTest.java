package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Characterization tests: they pin the CURRENT behavior of PasswordPolicy.check().
 * Do not change them. After refactoring, every one of them must still pass.
 */
class PasswordPolicyTest {

    private final PasswordPolicy policy = new PasswordPolicy();

    @Test
    void nullPassword_isTooShort() {
        assertEquals("TOO_SHORT", policy.check(null, "bob"));
    }

    @Test
    void shortPassword_isTooShort() {
        assertEquals("TOO_SHORT", policy.check("abc", "bob"));
    }

    @Test
    void longEnoughButNoDigit_isNoDigit() {
        assertEquals("NO_DIGIT", policy.check("abcdefgh", "bob"));
    }

    @Test
    void hasDigitButNoUppercase_isNoUppercase() {
        assertEquals("NO_UPPERCASE", policy.check("abcdefg1", "bob"));
    }

    @Test
    void containsUsername_isContainsUsername() {
        assertEquals("CONTAINS_USERNAME", policy.check("Bob12345", "bob"));
    }

    @Test
    void strongPassword_isOk() {
        assertEquals("OK", policy.check("Abcdefg1", "bob"));
    }

    @Test
    void nullUsername_skipsTheUsernameCheck() {
        assertEquals("OK", policy.check("Abcdefg1", null));
    }
}
