

public class SMS implements Notificador {

    @Override
    public void notificar(String mensaje) {

        System.out.println("Mensaje enviado por java.SMS: "+ mensaje);

    }
}
