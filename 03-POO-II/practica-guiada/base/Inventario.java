import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(String nombre) {
        productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    public List<Producto> buscarProductos(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public void listarProductos() {
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public double calcularValorTotalInventario() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularValorTotal();
        }
        return total;
    }
}
