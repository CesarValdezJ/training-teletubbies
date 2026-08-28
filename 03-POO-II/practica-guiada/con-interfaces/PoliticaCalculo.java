import java.util.List;

// Cómo se calcula el valor del inventario es responsabilidad de la política, no del inventario.
public interface PoliticaCalculo {
    double calcular(List<Producto> productos);
}
