import java.util.*;

public class Company {

    protected int companyIncome;
    protected List<Employee> salaryStaff = new ArrayList<>();

    protected void hire(Employee worker) {
            salaryStaff.add(worker);
    }

    protected void hireAll(List<Employee> listOfWorker) {
        salaryStaff.addAll(listOfWorker);
    }

    protected void fire() {
        int fireCount = salaryStaff.size() / 2;
        for (int i = 0; i < fireCount; i++) {
            int randomWorker = (int) (Math.random() * salaryStaff.size());
            salaryStaff.remove(randomWorker);
        }
        System.out.println("Уволено сотрудников: " + fireCount);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return listForSort(count, new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                return o2.getMonthSalary() - o1.getMonthSalary();
            }
        });
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return listForSort(count, new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                return o1.getMonthSalary() - o2.getMonthSalary();
            }
        });
    }

    private List<Employee> listForSort(int count, Comparator<Employee> comparator) {
        Collections.sort(salaryStaff, comparator);
        List<Employee> newSortedList = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            newSortedList.add(salaryStaff.get(i));
        } return newSortedList;
    }

    public Company(int companyIncome) {
        this.companyIncome = companyIncome;
    }

    protected int getIncome() {
        return companyIncome;
    }
    public void setCompanyIncome(int companyIncome) {
        this.companyIncome = companyIncome;
    }
}