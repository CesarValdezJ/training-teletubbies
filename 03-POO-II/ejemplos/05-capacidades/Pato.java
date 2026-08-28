// Un pato real vuela Y nada: implementa las dos interfaces a la vez.
public class Pato implements Volador, Nadador {
    public void volar() { System.out.println("El pato vuela batiendo las alas."); }
    public void nadar() { System.out.println("El pato nada en el estanque."); }
}
