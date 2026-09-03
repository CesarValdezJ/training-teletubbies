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

    public String check(String p, String u) {
        String r = "";
        int l = 0;
        try {
            l = p.length();
        } catch (Exception e) {
        }
        if (p == null) {
            r = "TOO_SHORT";
        } else {
            if (l < 8) {
                r = "TOO_SHORT";
            } else {
                boolean f1 = false;
                for (int i = 0; i < p.length(); i++) {
                    if (p.charAt(i) >= '0' && p.charAt(i) <= '9') {
                        f1 = true;
                    }
                }
                if (f1 == false) {
                    r = "NO_DIGIT";
                } else {
                    boolean f2 = false;
                    for (int i = 0; i < p.length(); i++) {
                        if (p.charAt(i) >= 'A' && p.charAt(i) <= 'Z') {
                            f2 = true;
                        }
                    }
                    if (!f2) {
                        r = "NO_UPPERCASE";
                    } else {
                        if (u != null && p.toLowerCase().contains(u.toLowerCase())) {
                            r = "CONTAINS_USERNAME";
                        } else {
                            r = "OK";
                        }
                    }
                }
            }
        }
        return r;
    }
}
