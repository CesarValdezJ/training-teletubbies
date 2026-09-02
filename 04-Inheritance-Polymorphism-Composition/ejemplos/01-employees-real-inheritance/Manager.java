public class Manager extends Employee {

    private final double monthlyBonus;

    public Manager(final String name, final double baseSalary, final double monthlyBonus) {
        super(name, baseSalary);
        this.monthlyBonus = monthlyBonus;
    }


    @Override
    public double calculatePayment() {
        return getBaseSalary() + monthlyBonus;
    }
}