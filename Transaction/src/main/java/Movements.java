import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Movements {

    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private final ArrayList<Transaction> grupingTransactions = new ArrayList<>();
    private final String regexFoValue = "[\"]*[\\d]++[.]*[\\d]*[\"]*";

    public Movements(String pathMovementsCsv) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            for (String line : lines) {
                String[] fragments = line.split(",");
                if ((!fragments[6].matches(regexFoValue)) || !fragments[7].matches(regexFoValue)) {
                    continue;
                }
                if (fragments.length > 8) {
                    if (fragments[6].contains("\"") && fragments[7].contains("\"")) {
                        fragments[6] = fragments[6] + "." + fragments[7];
                        fragments[6] = fragments[6].substring(1, fragments[6].length() - 1);
                        transactions.add(new Transaction(fragments[5],Double.parseDouble(fragments[6]),
                                Double.parseDouble(fragments[8])));
                    }
                    if (fragments[7].contains("\"") && fragments[8].contains("\"")) {
                        fragments[7] = fragments[7] + "." + fragments[8];
                        fragments[7] = fragments[7].substring(1, fragments[7].length() - 1);
                        transactions.add(new Transaction(fragments[5],Double.parseDouble(fragments[6]),
                                Double.parseDouble(fragments[7])));
                    }
                } else {
                    transactions.add(new Transaction(fragments[5],Double.parseDouble(fragments[6]),
                            Double.parseDouble(fragments[7])));
                }
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public double getExpenseSum() {
        return transactions.stream().mapToDouble(Transaction::getExpense).sum();
    }

    public double getIncomeSum() {
        return transactions.stream().mapToDouble(Transaction::getIncome).sum();
    }

    public void getDescription() {
        ArrayList<String> operations = (ArrayList<String>) transactions.stream()
                .map(Transaction::getTransactionInfo)
                .collect(Collectors.toList());
        for (String description : operations) {
            String[] fragments =  description.split("[\\s]{3,}");
            if (!fragments[1].contains("/")) {
                String costs = fragments[1].substring(fragments[1].lastIndexOf("\\") + 1);
                double expense = Double.parseDouble(fragments[3].substring(0, fragments[3].indexOf(" ")));
                grupingTransactions.add(new Transaction(costs,expense));
            } else {
                continue;
            }
        }
        Map<String, Double> costGrouping = grupingTransactions.stream()
                .collect((Collectors.groupingBy(Transaction::getTransactionInfo, Collectors.summingDouble(Transaction::getExpense))));
        for(String key : costGrouping.keySet()){
            System.out.println(key + " - " + costGrouping.get(key) + " руб.");
        }
    }
}
