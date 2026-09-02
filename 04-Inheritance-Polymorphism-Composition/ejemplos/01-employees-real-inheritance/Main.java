import java.util.List;

public class Main {

    public static void main(String[] args) {
        final List<Empleado> payroll = List.of(
                new Gerente("Ana", 20000.0, 5000.0),
                new Vendedor("Luis", 12000.0, 150.0, 30),
                new Vendedor("Marta", 12000.0, 150.0, 10)
        );

        System.out.println("=== Nomina del mes ===");
        for (final Empleado employee : payroll) {
            System.out.printf("%s (%s): $%.2f%n",
                    employee.getName(), employee.getClass().getSimpleName(), employee.calculatePayment());
        }

        System.out.println("\nMismo metodo calcularPago() en Empleado, resultado distinto segun la subclase real: "
                + "eso es polimorfismo sobre una jerarquia de herencia legitima (Gerente y Vendedor SI son Empleados).");
    }
}