import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int rebanadasDisponibles = 8;
        boolean salir = false;

        while (!salir) {
            System.out.println();
            System.out.println("=== Pasteleria: menu principal ===");
            System.out.println("1. Ver rebanadas disponibles");
            System.out.println("2. Servir una rebanada");
            System.out.println("3. Hornear un pastel nuevo (reinicia a 8 rebanadas)");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");

            final String entrada = scanner.nextLine();
            int opcion;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada invalida, escribe un numero del 1 al 4.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Rebanadas disponibles: " + rebanadasDisponibles);
                    break;
                case 2:
                    if (rebanadasDisponibles > 0) {
                        rebanadasDisponibles--;
                        System.out.println("Rebanada servida. Quedan " + rebanadasDisponibles);
                    } else {
                        System.out.println("Ya no quedan rebanadas.");
                    }
                    break;
                case 3:
                    rebanadasDisponibles = 8;
                    System.out.println("Se horneo un pastel nuevo con " + rebanadasDisponibles + " rebanadas.");
                    break;
                case 4:
                    salir = true;
                    System.out.println("Cerrando la pasteleria...");
                    break;
                default:
                    System.out.println("Opcion invalida, elige un numero del 1 al 4.");
            }
        }

        scanner.close();
        System.out.println("=== Fin del programa ===");
    }
}