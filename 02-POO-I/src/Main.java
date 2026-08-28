public class Main {

    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        inventario.agregarProducto(new Producto("Teclado", 250.0, 10));
        inventario.agregarProducto(new Producto("Mouse", 120.5, 20));
        inventario.agregarProducto(new Producto("Monitor", 1500.0, 5));

        System.out.println("Productos en inventario:");
        inventario.listarProductos().forEach(System.out::println);

        System.out.printf("Valor total del inventario: %.2f%n", inventario.calcularValorTotalInventario());

        inventario.eliminarProducto("Mouse");
        System.out.println("Después de eliminar Mouse:");
        inventario.listarProductos().forEach(System.out::println);

        try {
            inventario.agregarProducto(new Producto("", 10.0, 1));
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        try {
            new Producto("Cable", -5.0, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        try {
            inventario.eliminarProducto("NoExiste");
        } catch (IllegalStateException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
    }
}
