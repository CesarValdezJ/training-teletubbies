import java.util.List;

public class Main {

    public static void main(String[] args) {
        final List<Employee> payroll = List.of(
                new Manager("Ana", 20000.0, 5000.0),
                new Salesperson("Luis", 12000.0, 150.0, 30),
                new Salesperson("Marta", 12000.0, 150.0, 10)
        );

        System.out.println("=== Monthly payroll ===");
        for (final Employee employee : payroll) {
            System.out.printf("%s (%s): $%.2f%n",
                    employee.getName(), employee.getClass().getSimpleName(), employee.calculatePayment());
        }

        System.out.println("\nSame calculatePayment() method in Employee, different result depending on the actual "
                + "subclass: that is polymorphism over a legitimate inheritance hierarchy (Manager and Salesperson "
                + "truly ARE Employees).");
    }
}