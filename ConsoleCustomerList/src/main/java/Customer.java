import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private final String name;
    private final String phone;
    private final String email;
    public static final String NAME_EMAIL_NUMBER_REGEX = "([А-я]+)?([\\w]+[@][A-z]+\\.(ru|com))?([/+][7][0-9]{10})?";
    public static final Pattern PATTERN = Pattern.compile(NAME_EMAIL_NUMBER_REGEX);

    public String getName() {
        return name;
    }

    public String getPhone() {

        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String name, String phone, String email) {
        Matcher matcher = PATTERN.matcher(phone);
        Matcher matcher1 = PATTERN.matcher(email);
        this.name = name;
        this.phone = phone;
        this.email = email;

        if (matcher1.find()) {

            if (!email.equals(matcher1.group(2))) {
                throw new IllegalArgumentException("Wrong email format. Correct format: \n" +
                        "ivan.ivanom@mail.com");
            }
        }
        if (matcher.find()) {
            if (!phone.equals(matcher.group(4))) {
                throw new IllegalArgumentException("Wrong phone format. Correct format: \n" +
                        "+79777777777");
            }
        }
        else {
            throw new IllegalArgumentException("Wrong name, email or phone format. Correct format: \n" +
                    "Василий Петров vasily.petrov@gmail.com +79215637722\"");
        }
    }

    public String toString() {
        return name + " - " + email + " - " + phone;
    }
}