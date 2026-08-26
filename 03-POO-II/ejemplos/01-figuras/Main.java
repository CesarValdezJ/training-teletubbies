public class Main {
    public static void main(String[] args) {
        Jardin jardinRedondo = new Jardin("Jardín redondo", new Circulo(3));
        Jardin jardinRectangular = new Jardin("Jardín rectangular", new Rectangulo(4, 5));

        jardinRedondo.mostrarArea();
        jardinRectangular.mostrarArea();

        System.out.println("\nMismo método mostrarArea() en Jardin, resultado distinto según la Forma que tiene cada uno.");
    }
}
