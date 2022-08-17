import java.util.*;

public class PhoneBook {

    public HashMap<String, String> phoneBook = new HashMap<>();
    public static final String PHONE_REGEX = "[7][0-9]{10}";
    public static final String NAME_REGEX = "[А-я]+";

    public String addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (phone.matches(PHONE_REGEX) && name.matches(NAME_REGEX)) {
            phoneBook.put(phone, name);
            return "Имя контакта: " + name + "\n" +  "Номер контакта: " + phone;
        }
        return "Контакт не может быть добвален";
    }

    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        for (String number : phoneBook.keySet()) {
            if (phone.equals(number)) {
                return phoneBook.get(number) + " - " + number;
                }
            }
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet contactSet = new TreeSet();
        for (String number : phoneBook.keySet()) {
            if (name.equals(phoneBook.get(number))) {
                contactSet.add(phoneBook.get(number) + " - " + number);
                return contactSet;
            }
        }
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet <String> contactSet = new TreeSet<>();
        for (String name : phoneBook.values()) {
            for (String number : phoneBook.keySet()) {
                if (name.equals(phoneBook.get(number))){
                    contactSet.add(name + " - " + number);
                }
            }
        } return contactSet;
    }
}