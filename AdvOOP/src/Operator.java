public class Operator implements Employee {

    private static final int OPERATOR_SALARY = 40000;
    private Company company;

    @Override
    public int getMonthSalary() {
        return OPERATOR_SALARY;
    }

    public Operator (Company company) {
        this.company = company;
    }
}