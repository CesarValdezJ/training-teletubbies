public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario(new CalculoSimple());

        inventario.agregarProducto(new Producto("Teclado mecánico", 450.0, 10));
        inventario.agregarProducto(new Producto("Mouse inalámbrico", 250.0, 20));
        inventario.agregarProducto(new Producto("Monitor 24\"", 3200.0, 5));

        System.out.println("--- Listado ---");
        inventario.listarProductos();

        System.out.println("\n--- Con CalculoSimple ---");
        System.out.println("Valor total: $" + inventario.calcularValorTotalInventario());

        System.out.println("\n--- Cambiamos a CalculoConDescuento(10%), mismo Inventario ---");
        inventario.setPoliticaCalculo(new CalculoConDescuento(0.10));
        System.out.println("Valor total: $" + inventario.calcularValorTotalInventario());

        System.out.println("\n--- Búsqueda de \"mo\" ---");
        for (Producto p : inventario.buscarProductos("mo")) {
            System.out.println(p);
        }

        inventario.eliminarProducto("Mouse inalámbrico");
        System.out.println("\nValor total tras eliminar el mouse (sigue con descuento): $"
                + inventario.calcularValorTotalInventario());
    }
}
