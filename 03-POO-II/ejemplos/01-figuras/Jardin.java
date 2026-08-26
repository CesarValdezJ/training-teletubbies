// Composición: Jardin TIENE UNA Forma. El jardín no sabe (ni le importa) cómo se calcula
// el área internamente; solo le pide el resultado a la forma que compone.
public class Jardin {
    private String nombre;
    private Forma forma;

    public Jardin(String nombre, Forma forma) {
        this.nombre = nombre;
        this.forma = forma;
    }

    public void mostrarArea() {
        System.out.println(nombre + " tiene un área de " + forma.calcularArea() + " m²");
    }
}
