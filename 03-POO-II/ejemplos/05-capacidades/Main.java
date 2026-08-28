import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pato pato = new Pato();
        PatoDeGoma patoDeGoma = new PatoDeGoma();
        Avion avion = new Avion();

        System.out.println("--- Todo lo que puede volar (Pato y Avion, pero no PatoDeGoma) ---");
        List<Volador> voladores = List.of(pato, avion);
        for (Volador v : voladores) {
            v.volar();
        }

        System.out.println("\n--- Todo lo que puede nadar (Pato y PatoDeGoma, pero no Avion) ---");
        List<Nadador> nadadores = List.of(pato, patoDeGoma);
        for (Nadador n : nadadores) {
            n.nadar();
        }

        System.out.println("\n--- Objetos mezclados: cada uno reacciona solo a lo que sabe hacer ---");
        List<Object> objetos = List.of(pato, patoDeGoma, avion);
        for (Object o : objetos) {
            if (o instanceof Volador) {
                ((Volador) o).volar();
            }
            if (o instanceof Nadador) {
                ((Nadador) o).nadar();
            }
        }

        System.out.println("\nPato implementa Volador y Nadador a la vez: dos capacidades independientes en una sola clase, sin herencia de por medio.");
    }
}
