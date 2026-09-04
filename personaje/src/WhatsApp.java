

public class WhatsApp implements Notificador{

    @Override
    public void notificar(String mensaje){
        System.out.println("Mensaje enviado por java.WhatsApp: " + mensaje);
    }
}
