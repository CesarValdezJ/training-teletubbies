package org.example;

import java.util.List;

/**
 * Computes the total of an order, applies a volume discount and builds a short summary.
 *
 * NOTE (for the exercise): this class ships with three planted defects. Find them,
 * fix them with explicit error handling, and cover each one with a unit test.
 */
public class OrderProcessor {

    /** Orders with a subtotal at or above this amount get a discount. */
    public static final double VOLUME_THRESHOLD = 1000.0;

    /** Discount rate applied above the threshold. */
    public static final double VOLUME_DISCOUNT_RATE = 0.10;

    /** Sum of unitPrice * quantity for every line. */
    public double calculateSubtotal(List<OrderLine> lines) {
        double subtotal = 0;
        for (OrderLine line : lines) {
            int quantity = Integer.parseInt(line.getQuantityText());
            subtotal += line.getUnitPrice() * quantity;
        }
        return subtotal;
    }

    /** Order total after the volume discount, if it applies. */
    public double calculateTotal(List<OrderLine> lines) {
        double subtotal = calculateSubtotal(lines);
        if (subtotal >= VOLUME_THRESHOLD) {
            return subtotal + (subtotal * VOLUME_DISCOUNT_RATE);
        }
        return subtotal;
    }

    /** Human-readable summary, e.g. "Order #4521 (3 lines) - Keyboard, Monitor, Cable - total 2900.0". */
    public String buildSummary(String orderId, List<OrderLine> lines) {
        StringBuilder products = new StringBuilder();
        for (int i = 1; i <= lines.size(); i++) {
            if (products.length() > 0) {
                products.append(", ");
            }
            products.append(lines.get(i).getProduct());
        }
        return "Order " + orderId + " (" + lines.size() + " lines) - " + products
            + " - total " + calculateTotal(lines);
    }
}
