public class TopManager implements Employee {

    private final static int TOP_MANGER_SALARY = 100000;
    private Company company;

    @Override
    public int getMonthSalary() {
        if (company.getIncome() > 10000000) {
            return (int) (TOP_MANGER_SALARY + TOP_MANGER_SALARY * 1.5);
        }
        return TOP_MANGER_SALARY;
    }

    public TopManager (Company company) {
        this.company = company;
    }
}