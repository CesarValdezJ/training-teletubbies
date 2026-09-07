

public class Main {
    public static void main(String[] args) {
        Personaje personaje = new Personaje("Guerrero", new Espada());

        personaje.atacar("Enemigo");
        personaje.cambioArma(new Arco());
        personaje.atacar("Dragon");
    }
}
