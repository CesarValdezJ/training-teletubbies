// Composición: Personaje TIENE UN Arma, y puede CAMBIARLA en tiempo de ejecución.
public class Personaje {
    private String nombre;
    private Arma arma;

    public Personaje(String nombre, Arma arma) {
        this.nombre = nombre;
        this.arma = arma;
    }

    public void cambiarArma(Arma nuevaArma) {
        this.arma = nuevaArma;
        System.out.println(nombre + " cambia de arma.");
    }

    public void atacar() {
        System.out.print(nombre + " ataca -> ");
        arma.infligirDano();
    }
}
