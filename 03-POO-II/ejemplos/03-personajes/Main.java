public class Main {
    public static void main(String[] args) {
        Personaje heroe = new Personaje("Héroe", new Espada());
        heroe.atacar();

        heroe.cambiarArma(new Arco());
        heroe.atacar();

        System.out.println("\nEl mismo objeto Personaje cambió de comportamiento al reemplazar su Arma en tiempo de ejecución.");
    }
}
