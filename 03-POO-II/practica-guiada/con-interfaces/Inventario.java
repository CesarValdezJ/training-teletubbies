import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;
    private PoliticaCalculo politicaCalculo; // composición: Inventario TIENE UNA PoliticaCalculo

    public Inventario(PoliticaCalculo politicaCalculo) {
        this.productos = new ArrayList<>();
        this.politicaCalculo = politicaCalculo;
    }

    public void setPoliticaCalculo(PoliticaCalculo politicaCalculo) {
        this.politicaCalculo = politicaCalculo;
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
        return politicaCalculo.calcular(productos);
    }
}
