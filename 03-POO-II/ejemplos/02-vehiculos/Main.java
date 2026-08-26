public class Main {
    public static void main(String[] args) {
        Vehiculo autoDeportivo = new Vehiculo("Auto deportivo", new MotorGasolina());
        Vehiculo autoElectrico = new Vehiculo("Auto eléctrico", new MotorElectrico());

        autoDeportivo.acelerar();
        autoElectrico.acelerar();

        System.out.println("\nMismo método acelerar() en Vehiculo, comportamiento distinto según el Motor que tienen: el vehículo acelera delegando en la potencia que su motor genera.");
    }
}
