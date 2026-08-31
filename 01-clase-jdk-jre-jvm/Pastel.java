
public class Pastel {

    private String sabor;
    private int rebanadas;
    private boolean horneado;

    public Pastel(final String sabor, final int rebanadas) {
        this.sabor = sabor;
        this.rebanadas = rebanadas;
        this.horneado = false;
        System.out.println("Se preparo la mezcla de un pastel de " + sabor); // log
    }

    public void hornear() {
        this.horneado = true;
        System.out.println("El pastel de " + sabor + " ya esta horneado.");
    }

    public void servirRebanada() {
        if (!horneado) {
            System.out.println("No se puede servir, el pastel aun no esta horneado.");
            return;
        }
        if (rebanadas <= 0) {
            System.out.println("Ya no quedan rebanadas de pastel de " + sabor);
            return;
        }
        rebanadas--;
        System.out.println("Sirviendo una rebanada de " + sabor + ". Quedan " + rebanadas);
    }

    public static void main(String[] args) {
        System.out.println("=== Iniciando la pasteleria ===");

        final Pastel pastelDeChocolate = new Pastel("chocolate", 8);
        pastelDeChocolate.hornear();
        pastelDeChocolate.servirRebanada();
        pastelDeChocolate.servirRebanada();

        final Pastel pastelDeVainilla = new Pastel("vainilla", 6);
        pastelDeVainilla.servirRebanada();
        pastelDeVainilla.hornear();
        pastelDeVainilla.servirRebanada();

        System.out.println("=== Fin del dia en la pasteleria ===");
    }
}
