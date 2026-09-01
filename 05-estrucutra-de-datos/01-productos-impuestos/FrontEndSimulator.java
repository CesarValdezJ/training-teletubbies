import java.util.ArrayList;
import java.util.List;

public class FrontEndSimulator {

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 899.99, 15));
        products.add(new Product(2, "Mouse", 19.99, 80));
        products.add(new Product(3, "Keyboard", 39.99, 50));
        products.add(new Product(4, "Monitor", 199.99, 25));
        products.add(new Product(5, "Headphones", 59.99, 40));
        products.add(new Product(6, "Webcam", 45.50, 30));
        products.add(new Product(7, "Microphone", 65.00, 20));
        products.add(new Product(8, "USB Cable", 8.99, 100));
        products.add(new Product(9, "External Hard Drive", 120.00, 18));
        products.add(new Product(10, "Graphics Tablet", 250.00, 12));
        products.add(new Product(11, "Wireless Router", 89.99, 22));
        products.add(new Product(12, "Power Bank", 34.99, 60));
        return products;
    }

    public List<Taxes> getTaxes() {
        List<Taxes> taxes = new ArrayList<>();
        taxes.add(new Taxes(101, "VAT", 16.0, 1));
        taxes.add(new Taxes(102, "Import Tax", 5.0, 1));
        taxes.add(new Taxes(103, "VAT", 16.0, 2));
        taxes.add(new Taxes(104, "VAT", 16.0, 3));
        taxes.add(new Taxes(105, "Eco Tax", 3.0, 3));
        taxes.add(new Taxes(106, "VAT", 16.0, 4));
        taxes.add(new Taxes(107, "Luxury Tax", 8.0, 4));
        taxes.add(new Taxes(108, "VAT", 16.0, 5));
        taxes.add(new Taxes(109, "VAT", 16.0, 6));
        taxes.add(new Taxes(110, "Eco Tax", 2.5, 6));
        taxes.add(new Taxes(111, "VAT", 16.0, 7));
        taxes.add(new Taxes(112, "VAT", 16.0, 8));
        taxes.add(new Taxes(113, "Environmental Tax", 1.0, 8));
        taxes.add(new Taxes(114, "VAT", 16.0, 9));
        taxes.add(new Taxes(115, "Import Tax", 4.0, 9));
        taxes.add(new Taxes(116, "Luxury Tax", 10.0, 9));
        taxes.add(new Taxes(117, "VAT", 16.0, 10));
        taxes.add(new Taxes(118, "Eco Tax", 3.5, 10));
        taxes.add(new Taxes(119, "VAT", 16.0, 11));
        taxes.add(new Taxes(120, "VAT", 16.0, 12));
        return taxes;
    }
}
