import java.util.List;

public class CalculoConDescuento implements PoliticaCalculo {
    private double porcentajeDescuento; // ej. 0.10 = 10%

    public CalculoConDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double calcular(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularValorTotal();
        }
        return total * (1 - porcentajeDescuento);
    }
}
