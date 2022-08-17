import java.util.ArrayList;

public class TodoList {
    ArrayList<String> deals = new ArrayList<>();

    public String add(String todo) {
        // TODO: добавьте переданное дело в конец списка

        deals.add(todo);
        return todo;
    }

    public String add(int index, String todo) {
        // TODO: добавьте дело на указанный индекс,
        //  проверьте возможность добавления

        if (index < deals.size()) {
            deals.add(index, todo);
            todo = deals.get(index);
        } else {
            deals.add(todo);
        }
        return todo;
    }

    public String edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения

        if (index < deals.size()) {
            String switchTodo = deals.get(index);
            deals.set(index,todo);
            todo = switchTodo;
        }
        return todo;
    }

    public String delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела

        String deleteReturn;

        if (index < deals.size()) {
            String removeTodo = deals.get(index);
            deals.remove(index);
            deleteReturn = removeTodo;
        }
        else {
            deleteReturn = "Дело с таким номером не существует";
        }
        return deleteReturn;
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        if (deals.isEmpty()) {
            System.out.println("Список дел пуст");
        }
        return new ArrayList<>(deals);
    }
}
