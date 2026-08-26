// Composición: Vehiculo TIENE UN Motor. Un Auto no "es un" Motor, así que herencia no aplicaría aquí.
public class Vehiculo {
    private String nombre;
    private Motor motor;

    public Vehiculo(String nombre, Motor motor) {
        this.nombre = nombre;
        this.motor = motor;
    }

    public void acelerar() {
        System.out.print(nombre + " acelera: ");
        motor.generarPotencia();
    }
}
