import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final PhoneBook PHONE_BOOK = new PhoneBook();
    public static final String NAME_REGEX = "([А-я]+)?([7][0-9]{10})?";
    public static final Pattern PATTERN = Pattern.compile(NAME_REGEX);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            Matcher matcher = PATTERN.matcher(input);

            if (matcher.find()) {
                if (input.equals(matcher.group(1)) && PHONE_BOOK.getPhonesByName(matcher.group(1)).isEmpty()) {
                    System.out.println("Введите номер телефона для абонента " + matcher.group(1));
                    String newInput = scanner.nextLine();
                    Matcher newMatcher = PATTERN.matcher(newInput);
                    if (newMatcher.find() && newInput.equals(newMatcher.group(2))) {
                        System.out.println("Контакт сохранен" + "\n" + PHONE_BOOK.addContact(newInput, input) + "\n");
                    } else {
                        System.out.println("Неверный формат номера");
                    }
                }
                else if (PHONE_BOOK.phoneBook.containsValue(matcher.group(1))) {
                    System.out.println("Контакт с таким именем уже существует: " + PHONE_BOOK.getPhonesByName(matcher.group(1)));
                }
                if (input.equals(matcher.group(2)) && PHONE_BOOK.getNameByPhone(matcher.group(2)).equals("")) {
                    System.out.println("Введите имя абонента для номера  " + matcher.group(2));
                    String newInput = scanner.nextLine();
                    Matcher newMatcher = PATTERN.matcher(newInput);
                    if (newMatcher.find() && newInput.equals(newMatcher.group(1))) {
                        System.out.println("Контакт сохранен" + "\n" + PHONE_BOOK.addContact(input, newInput) + "\n");
                    } else {
                        System.out.println("Неверный формат имени");
                    }
                }
                else if (PHONE_BOOK.phoneBook.containsKey(matcher.group(2))) {
                    System.out.println("Контакт с таким номером уже существует: " + PHONE_BOOK.getNameByPhone(matcher.group(2)));
                }
            }
            if (input.equals("LIST")) {
                System.out.println(PHONE_BOOK.getAllContacts());
            }
            if (!matcher.matches() && !input.equals("LIST")) {
                System.out.println("Неверный формат ввода");
            }
        }
    }
}