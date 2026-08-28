import java.util.List;

public class CalculoSimple implements PoliticaCalculo {
    public double calcular(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularValorTotal();
        }
        return total;
    }
}
