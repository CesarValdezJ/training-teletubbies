package org.example;

/** Shows the policy in action. The behavior here must not change after the refactor. */
public class Main {
    public static void main(String[] args) {
        PasswordPolicy policy = new PasswordPolicy();
        String username = "bob";

        for (String candidate : new String[] {"abc", "abcdefgh", "abcdefg1", "Bob12345", "Abcdefg1"}) {
            System.out.println(candidate + " -> " + policy.check(candidate, username));
        }
    }
}
