public class Taxes {
    private int id;
    private String name;
    private double value;
    private int productId;

    public Taxes(int id, String name, double value, int productId) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Taxes{id=" + id + ", name='" + name + "', value=" + value + ", productId=" + productId + "}";
    }
}
