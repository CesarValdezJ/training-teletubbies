import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // AQUI DEBERIA DAR ERROR AL HACER MERGE
        // SEGUNDA LINEA
        // NOTA PARA GENERAR CONFLICTOS
        boolean training = false;

        FrontEndSimulator simulator = new FrontEndSimulator();
        List<Product> products = simulator.getProducts();
        List<Taxes> taxes = simulator.getTaxes();

        if (!training) {
            System.out.println("--- Ejemplos de Streams de Java 8 usando solo Listas ---");

            System.out.println("\n1) map(): obtener solo el nombre de cada producto");
            List<String> productNames = products.stream()
                    .map(Product::getName)
                    .collect(Collectors.toList());
            productNames.forEach(System.out::println);

            System.out.println("\n2) filter(): productos con precio mayor a 50");
            products.stream()
                    .filter(product -> product.getPrice() > 50)
                    .forEach(System.out::println);

            System.out.println("\n3) sorted(): productos ordenados por precio de menor a mayor");
            products.stream()
                    .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                    .forEach(System.out::println);

            System.out.println("\n4) mapToDouble() + average(): precio promedio de todos los productos");
            double averagePrice = products.stream()
                    .mapToDouble(Product::getPrice)
                    .average()
                    .orElse(0.0);
            System.out.println("Precio promedio: " + averagePrice);

            System.out.println("\n5) mapToDouble() + sum(): valor total del inventario (precio x stock)");
            double totalStockValue = products.stream()
                    .mapToDouble(product -> product.getPrice() * product.getStock())
                    .sum();
            System.out.println("Valor total en inventario: " + totalStockValue);

            System.out.println("\n6) distinct(): nombres de impuestos sin repetir");
            taxes.stream()
                    .map(Taxes::getName)
                    .distinct()
                    .forEach(System.out::println);

            System.out.println("\n7) filter() + count(): cuantos impuestos tienen un valor mayor a 10");
            long highTaxesCount = taxes.stream()
                    .filter(tax -> tax.getValue() > 10)
                    .count();
            System.out.println("Impuestos con valor mayor a 10: " + highTaxesCount);

        } else {
            SalesCalculator salesCalculator = new SalesCalculator();
            Map<Product, Double> soldPrices = salesCalculator.calculateSoldPrices(products, taxes);

            soldPrices.forEach((product, soldPrice) ->
                    System.out.println(product.getName() + " -> precio final de venta: " + soldPrice));
        }
    }
}
