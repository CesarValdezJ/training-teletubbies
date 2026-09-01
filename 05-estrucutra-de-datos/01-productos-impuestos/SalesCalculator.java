import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesCalculator {

    public Map<Product, Double> calculateSoldPrices(List<Product> products, List<Taxes> taxes) {
        return products.stream()
                .collect(Collectors.toMap(
                        product -> product,
                        product -> applyTaxes(product, taxes)
                ));
    }

    private double applyTaxes(Product product, List<Taxes> taxes) {
        double taxPercentSum = taxes.stream()
                .filter(tax -> tax.getProductId() == product.getId())
                .mapToDouble(Taxes::getValue)
                .sum();
        return product.getPrice() + (product.getPrice() * taxPercentSum / 100);
    }
}
