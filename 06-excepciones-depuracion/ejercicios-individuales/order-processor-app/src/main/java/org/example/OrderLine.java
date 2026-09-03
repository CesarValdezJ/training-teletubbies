package org.example;

/** One line of an order: a product name, a unit price and a quantity still given as raw text. */
public class OrderLine {
    private final String product;
    private final double unitPrice;
    private final String quantityText;   // comes straight from a CSV column / form field, unparsed

    public OrderLine(String product, double unitPrice, String quantityText) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantityText = quantityText;
    }

    public String getProduct() {
        return product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getQuantityText() {
        return quantityText;
    }
}
