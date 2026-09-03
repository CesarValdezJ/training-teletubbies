package org.example;

import java.util.List;

/** Reproduces the failure: run this and read the stack trace. */
public class Main {
    public static void main(String[] args) {
        List<OrderLine> lines = List.of(
            new OrderLine("Keyboard", 350.0, "2"),
            new OrderLine("Monitor", 2200.0, "1"),
            new OrderLine("Cable", 45.0, "three")   // typed by hand into the order form
        );

        OrderProcessor processor = new OrderProcessor();

        System.out.println("Subtotal: " + processor.calculateSubtotal(lines));
        System.out.println("Total: " + processor.calculateTotal(lines));
        System.out.println(processor.buildSummary("#4521", lines));
    }
}
