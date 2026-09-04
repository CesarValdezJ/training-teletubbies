

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    List<Notificador>notificadores;

    public Usuario(){
        notificadores = new ArrayList<>();

    }

    public void agregarNotificador(Notificador notificador){
        notificadores.add(notificador);


    }

    public void notificar(String mensaje){

        for (Notificador notificador : notificadores){
            notificador.notificar(mensaje);

        }

    }


}