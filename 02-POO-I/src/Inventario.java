import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventario {

    private final List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (buscarProducto(producto.getNombre()).isPresent()) {
            throw new IllegalStateException("Ya existe un producto con ese nombre: " + producto.getNombre());
        }
        productos.add(producto);
    }

    public void eliminarProducto(String nombre) {
        Producto producto = buscarProducto(nombre)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado: " + nombre));
        productos.remove(producto);
    }

    public Optional<Producto> buscarProducto(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public double calcularValorTotalInventario() {
        return productos.stream()
                .mapToDouble(Producto::calcularValorTotal)
                .sum();
    }

    public int totalProductos() {
        return productos.size();
    }
}
