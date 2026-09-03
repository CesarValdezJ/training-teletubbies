package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/** Reference tests: one per planted defect, plus a couple of edge cases. */
class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();

    @Test
    void calculateSubtotal_addsUpEveryLine() {
        List<OrderLine> lines = List.of(
            new OrderLine("Keyboard", 350.0, "2"),
            new OrderLine("Mouse", 150.0, "1")
        );
        assertEquals(850.0, processor.calculateSubtotal(lines), 1e-9);
    }

    @Test
    void calculateSubtotal_throwsInvalidQuantityOnNonNumericText() {   // defect 1
        List<OrderLine> lines = List.of(new OrderLine("Cable", 45.0, "three"));
        InvalidQuantityException ex =
            assertThrows(InvalidQuantityException.class, () -> processor.calculateSubtotal(lines));
        assertTrue(ex.getMessage().contains("Cable"));
        assertEquals(NumberFormatException.class, ex.getCause().getClass());
    }

    @Test
    void calculateTotal_appliesVolumeDiscountAboveThreshold() {        // defect 2
        List<OrderLine> lines = List.of(new OrderLine("Monitor", 2000.0, "1")); // subtotal 2000
        assertEquals(1800.0, processor.calculateTotal(lines), 1e-9);            // 2000 - 10%
    }

    @Test
    void calculateTotal_noDiscountBelowThreshold() {
        List<OrderLine> lines = List.of(new OrderLine("Mouse", 150.0, "2"));    // subtotal 300
        assertEquals(300.0, processor.calculateTotal(lines), 1e-9);
    }

    @Test
    void buildSummary_listsEveryLineIncludingFirstAndLast() {          // defect 3 (off-by-one)
        List<OrderLine> lines = List.of(
            new OrderLine("Keyboard", 350.0, "1"),
            new OrderLine("Monitor", 2200.0, "1"),
            new OrderLine("Cable", 45.0, "1")
        );
        String text = processor.buildSummary("#4521", lines);
        assertTrue(text.contains("Keyboard"), text);   // the buggy loop skipped index 0
        assertTrue(text.contains("Cable"), text);      // ...and threw before reaching the last one
        assertTrue(text.contains("(3 lines)"), text);
    }

    @Test
    void buildSummary_handlesSingleLineOrder() {
        List<OrderLine> lines = List.of(new OrderLine("Mouse", 150.0, "1"));
        assertTrue(processor.buildSummary("#7", lines).contains("Mouse"));
    }
}
