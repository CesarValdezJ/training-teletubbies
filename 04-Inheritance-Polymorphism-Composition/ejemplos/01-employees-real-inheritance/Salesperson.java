public class Salesperson extends Employee {

    private final double commissionPerSale;
    private final int salesThisMonth;

    public Salesperson(final String name, final double baseSalary, final double commissionPerSale,
                        final int salesThisMonth) {
        super(name, baseSalary);
        this.commissionPerSale = commissionPerSale;
        this.salesThisMonth = salesThisMonth;
    }

    @Override
    public double calculatePayment() {
        return getBaseSalary() + (commissionPerSale * salesThisMonth);
    }
}