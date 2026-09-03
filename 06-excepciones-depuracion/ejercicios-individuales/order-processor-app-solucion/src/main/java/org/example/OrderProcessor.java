package org.example;

import java.util.List;

/**
 * Computes the total of an order, applies a volume discount and builds a short summary.
 *
 * Reference solution. The three original defects were:
 *  1. calculateSubtotal(): Integer.parseInt() threw an unhandled NumberFormatException on
 *     non-numeric text. Now wrapped in InvalidQuantityException, keeping the cause.
 *  2. calculateTotal(): the discount was ADDED instead of subtracted.
 *  3. buildSummary(): the product loop ran `for (i = 1; i <= lines.size(); i++)` - it skipped
 *     the first line and read one index past the end (IndexOutOfBoundsException). Now a for-each.
 *
 * Naming note: these methods do work (parse, iterate, apply a discount rule), so they read as
 * verbs. `getTotal()` would be misleading - it implies a stored field, not a computation.
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
            subtotal += line.getUnitPrice() * parseQuantity(line);
        }
        return subtotal;
    }

    private int parseQuantity(OrderLine line) {
        try {
            return Integer.parseInt(line.getQuantityText().trim());
        } catch (NumberFormatException e) {
            throw new InvalidQuantityException(line.getProduct(), line.getQuantityText(), e);
        }
    }

    /** Order total after the volume discount, if it applies. */
    public double calculateTotal(List<OrderLine> lines) {
        double subtotal = calculateSubtotal(lines);
        if (subtotal >= VOLUME_THRESHOLD) {
            return subtotal - (subtotal * VOLUME_DISCOUNT_RATE);
        }
        return subtotal;
    }

    /** Human-readable summary, e.g. "Order #4521 (3 lines) - Keyboard, Monitor, Cable - total 2900.0". */
    public String buildSummary(String orderId, List<OrderLine> lines) {
        StringBuilder products = new StringBuilder();
        for (OrderLine line : lines) {              // fix 3: for-each - no indices, no off-by-one
            if (products.length() > 0) {
                products.append(", ");
            }
            products.append(line.getProduct());
        }
        return "Order " + orderId + " (" + lines.size() + " lines) - " + products
            + " - total " + calculateTotal(lines);
    }
}
