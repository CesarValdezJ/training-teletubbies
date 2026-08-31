import java.util.ArrayList;
import java.util.List;

// Composición: Usuario TIENE UNA LISTA de Notificadores.
// A diferencia de los ejemplos anteriores, aquí compone varios objetos a la vez, no solo uno.
public class Usuario {
    private String nombre;
    private List<Notificador> notificadores = new ArrayList<>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void agregarNotificador(Notificador notificador) {
        notificadores.add(notificador);
    }

    public void notificar(String mensaje) {
        System.out.println("Notificando a " + nombre + ":");
        for (Notificador n : notificadores) {
            n.enviar(mensaje);
        }
    }
}
