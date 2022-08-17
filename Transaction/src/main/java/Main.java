public class Main {

    private static final String DATA_FILE = "C:\\Users\\Dima\\IdeaProjects\\java_basics\\FilesAndNetwork\\files\\movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(DATA_FILE);
        System.out.println("Сумма доходов: " + movements.getIncomeSum());
        System.out.println("Сумма расходов: " + movements.getExpenseSum());
        System.out.println("Расходы по организациям: " + "\n");
        movements.getDescription();
    }
}
