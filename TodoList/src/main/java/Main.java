import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final TodoList TODO_LIST = new TodoList();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду:");
            String input = scanner.nextLine();

            String regexIndex = "(ADD|LIST|EDIT|DELETE)\\s*([0-9]*)\\s*(.+)*";
            int index;
            Pattern pattern = Pattern.compile(regexIndex);
            Matcher matcher = pattern.matcher(input);

            if (!matcher.matches()) {
                System.out.println("Неверная команда");
                continue;
            }
            if (matcher.matches()) {
                if (Command.valueOf(matcher.group(1)).equals(Command.ADD) && !matcher.group(2).isEmpty()) {
                    index = Integer.parseInt(matcher.group(2));
                    System.out.println("Добавлено дело " + TODO_LIST.add(index, matcher.group(3)));
                } else if (Command.valueOf(matcher.group(1)).equals(Command.ADD)) {
                    System.out.println("Добавлено дело " + TODO_LIST.add(matcher.group(3)));
                } else if (Command.valueOf(matcher.group(1)).equals(Command.EDIT)) {
                    index = Integer.parseInt(matcher.group(2));
                    System.out.println("Дело " + TODO_LIST.edit(matcher.group(3), index) +
                            " заменено на " + matcher.group(3));
                } else if (Command.valueOf(matcher.group(1)).equals(Command.DELETE)) {
                    index = Integer.parseInt(matcher.group(2));
                    System.out.println("Дело " + TODO_LIST.delete(index) + " удалено");
                } else if (Command.valueOf(matcher.group(1)).equals(Command.LIST)) {
                    for (int i = 0; i < TODO_LIST.getTodos().size(); i++) {
                        System.out.println(i + " - " + TODO_LIST.getTodos().get(i));
                    }
                }
            }
        }
    }
}
