public class Main {
    private static Company company = new Company(20000000);
    public static void main(String[] args) {
        //company.setCompanyIncome(9000000);
        hireNewPersonal(180, new Operator(company));
        hireNewPersonal(80, new Manager(company));
        hireNewPersonal(10, new TopManager(company));
        printHighestSalaries(company, 10);
        printLowestSalaries(company,30);
        company.fire();
        printHighestSalaries(company, 15);
        printLowestSalaries(company, 30);
    }

    private static void printLowestSalaries(Company company, int count) {
        if (count > 0 && count < company.salaryStaff.size()) {
            System.out.println("Самые низкие зарплаты: ");
            for (Employee employee : company.getLowestSalaryStaff(count - 1)) {
                System.out.println(employee.getMonthSalary() + " руб.");
            }
        }
        else {
            System.out.println("Неверно указанно количество сотрудников");
        }
    }

    private static void printHighestSalaries(Company company, int count) {
        if (count > 0 && count < company.salaryStaff.size()) {
            System.out.println("Самые высокие зарплаты: ");
            for (Employee employee : company.getTopSalaryStaff(count - 1)) {
                System.out.println(employee.getMonthSalary() + " руб.");
            }
        }
        else {
            System.out.println("Неверно указанно количество сотрудников");
        }
    }

    private static void hireNewPersonal (int count, Employee employee) {
        for (int i = 0; i < count; i++) {
            company.hire(employee);
        }
    }
}