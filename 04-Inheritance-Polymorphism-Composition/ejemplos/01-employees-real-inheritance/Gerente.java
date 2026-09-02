public class Gerente extends Empleado {

    private final double monthlyBonus;

    public Gerente(final String name, final double baseSalary, final double monthlyBonus) {
        super(name, baseSalary);
        this.monthlyBonus = monthlyBonus;
    }


    @Override
    public double calculatePayment() {
        return getBaseSalary() + monthlyBonus;
    }
}