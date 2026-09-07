package personajes;

public class Arco implements Arma {

    public void atacar(String objetivo) {
        System.out.println("¡Atacando a " + objetivo + " con el arco!");
    }
}
