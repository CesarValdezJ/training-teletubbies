package personajes;



public class Personaje {
    private String nombre;
    private Arma arma;

    public Personaje(String nombre, Arma arma) {
        this.nombre = nombre;
        this.arma = arma;
    }

    public void atacar(String objetivo) {
        System.out.println(nombre + " está atacando a " + objetivo + " con su arma.");
        arma.atacar(objetivo);
    }

    public void cambioArma(Arma nuevaArma) {
        this.arma = nuevaArma;
        System.out.println(nombre + " ha cambiado su arma.");
    }




}
