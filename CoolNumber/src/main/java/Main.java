import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static final String LETTERS_LIST = "[АВЕКМНОРСТУХ]";
    public static final String CAR_NUMBER_REGEX = LETTERS_LIST + "[0-9]{3}" + LETTERS_LIST + "{2}[0-9]{1,3}";
    public static final HashSet<String> SET_OF_CAR_NUMBER = new HashSet<>();
    public static final TreeSet<String> TREE_OF_CAR_NUMBER = new TreeSet<>();
    public static final String BRUTE_FORCE_SEARCH = "Поиск переборм";
    public static final String BINARY_SEARCH_IN_LIST = "Бинарный поиск";
    public static final String SEARCH_IN_HASH_SET = "Поиск в HashSet";
    public static final String SEARCH_IN_TREE_SET = "Поиск в TreeSet";
    public static void main(String[] args) {
        CoolNumbers.generateCoolNumbers();
        for (int i = 0; i < CoolNumbers.LIST_OF_CAR_NUMBER.size(); i++) {
            SET_OF_CAR_NUMBER.add(CoolNumbers.LIST_OF_CAR_NUMBER.get(i));
            TREE_OF_CAR_NUMBER.add(CoolNumbers.LIST_OF_CAR_NUMBER.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите автомобильный номер");
            String input = scanner.nextLine();
            Pattern pattern = Pattern.compile(CAR_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                long bruteForceSearchInList = System.nanoTime();
                foundNumber(input,BRUTE_FORCE_SEARCH, bruteForceSearchInList);

                long binarySearchInList = System.nanoTime();
                foundNumber(input,BINARY_SEARCH_IN_LIST, binarySearchInList);

                long searchInHashSet = System.nanoTime();
                foundNumber(input,SEARCH_IN_HASH_SET, searchInHashSet);

                long searchInTreeSet = System.nanoTime();
                foundNumber(input,SEARCH_IN_TREE_SET, searchInTreeSet);;
            }
            else System.out.println("Неверный формат ввода");
        }
    }
    public static void foundNumber (String number, String nameOfSearch, long timeOfSearch) {
        if (CoolNumbers.bruteForceSearchInList(CoolNumbers.LIST_OF_CAR_NUMBER, number)) {
            System.out.println(nameOfSearch + ": номер найден, поиск занял " +
                    (System.nanoTime() - timeOfSearch) + " нс");
        }
        else System.out.println(nameOfSearch + ": номер не найден, поиск занял " +
                (System.nanoTime() - timeOfSearch) + " нс");
    }
}