public abstract class Empleado {

    private final String name;
    private final double baseSalary;

    public Empleado(final String name, final double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public abstract double calculatePayment();
}