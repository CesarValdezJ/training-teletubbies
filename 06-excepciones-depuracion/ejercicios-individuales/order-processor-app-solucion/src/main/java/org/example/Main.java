package org.example;

import java.util.List;

/** Same scenario as the exercise, now handled explicitly. */
public class Main {
    public static void main(String[] args) {
        List<OrderLine> good = List.of(
            new OrderLine("Keyboard", 350.0, "2"),
            new OrderLine("Monitor", 2200.0, "1")
        );

        OrderProcessor processor = new OrderProcessor();

        System.out.println("Subtotal: " + processor.calculateSubtotal(good));   // 2900.0
        System.out.println("Total:    " + processor.calculateTotal(good));      // 2610.0 (-10%)
        System.out.println(processor.buildSummary("#4521", good));              // lists every product

        // A bad quantity now fails with a clear, domain-specific error.
        List<OrderLine> bad = List.of(new OrderLine("Cable", 45.0, "three"));
        try {
            processor.calculateSubtotal(bad);
        } catch (InvalidQuantityException e) {
            System.out.println("Expected: " + e.getMessage());
        }
    }
}
