package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Starter test class.
 *
 * You must finish the session with AT LEAST THREE tests here, one per defect you fixed.
 * Each test follows Arrange - Act - Assert, and at least one uses assertThrows.
 * The test below is a freebie to show the setup; keep it or replace it.
 */
class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();

    @Test
    void calculateSubtotal_addsUpEveryLine() {
        // Arrange
        List<OrderLine> lines = List.of(
            new OrderLine("Keyboard", 350.0, "2"),
            new OrderLine("Mouse", 150.0, "1")
        );
        // Act
        double subtotal = processor.calculateSubtotal(lines);
        // Assert
        assertEquals(850.0, subtotal, 1e-9);
    }

    // TODO defect 1: what should calculateSubtotal() do when a quantity is not a number?
    // TODO defect 2: check calculateTotal() when the volume discount applies - cheaper or dearer?
    // TODO defect 3: build a summary for a multi-line order and check every product is listed.
}
