

public class Email implements Notificador{

    @Override
    public void notificar(String mensaje){

        System.out.println("Mensaje enviado por java.Email: "+ mensaje);

    }
}
