public class Manager implements Employee {

    private static final int MANAGER_SALARY = 50000;
    private final static double MIN_BONUS = 115000 * 0.05;
    private final static double MAX_BONUS = 140000 * 0.05;
    private Company company;

    @Override
    public int getMonthSalary() {
        int monthBonus = (int) (MIN_BONUS + Math.random() * (MAX_BONUS - MIN_BONUS));
        return MANAGER_SALARY + monthBonus;
    }

    public Manager (Company company) {
        this.company = company;
    }
}