

public class Main2 {
    public static void main(String[] args) {

        Usuario usuario = new Usuario();
        usuario.agregarNotificador(new Email());
        usuario.agregarNotificador(new SMS());
        usuario.agregarNotificador(new WhatsApp());

        usuario.notificar("Tienes una nueva tarea");


    }
}
