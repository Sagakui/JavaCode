import java.util.*;

public class CoolNumbers {
    public static final char [] LETTERS = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
    public static final int FIRST_REGION = 1;
    public static final int LAST_REGION = 199;
    public static final int FIRST_VALUE = 1;
    public static final int LAST_VALUE = 10;
    public static final ArrayList <String> LIST_OF_CAR_NUMBER = new ArrayList<>();


    public static List<String> generateCoolNumbers() {
        for (int i = 0; i < 2000100; i++) {
            LIST_OF_CAR_NUMBER.add(numberFormat());
        }
        Collections.sort(LIST_OF_CAR_NUMBER);
        return LIST_OF_CAR_NUMBER;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        return list.contains(number);
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        int result = Collections.binarySearch(sortedList, number);
        return result >= 0;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

    // Генерация автомобильных номеров
    public static char firstRandomLetter() {
        int firstLetter = (int) (Math.random() * LETTERS.length);
        return LETTERS[firstLetter];
    }

    public static int randomDigits() {
        return (int) ((Math.random() * (LAST_VALUE - FIRST_VALUE)) + FIRST_VALUE);
    }

    public static char secondRandomLetter() {
        int secondLetter = (int) (Math.random() * LETTERS.length);
        return LETTERS[secondLetter];
    }

    public static char thirdRandomLetter() {
        int thirdLetter = (int) (Math.random() * LETTERS.length);
        return LETTERS[thirdLetter];
    }

    public static int randomRegion() {
        return (int) ((Math.random() * (LAST_REGION - FIRST_REGION)) + FIRST_REGION);
    }

    public static String numberFormat() {
        return String.format("%s%d%d%d%s%s%d",
                firstRandomLetter(),
                randomDigits(), randomDigits(), randomDigits(),
                secondRandomLetter(),
                thirdRandomLetter(),
                randomRegion());
    }
}