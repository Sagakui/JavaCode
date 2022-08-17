import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CourseSelector {
    protected  void courseSelector () {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/skillbox",
                    "root",
                    "6843242793Dimon!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, students_count FROM courses;");
            while (resultSet.next()) {
                if (resultSet.getString("students_count") == null) {
                    System.out.println(resultSet.getString("name") + " КУРС ЕЩЕ НЕ ЗАПУЩЕН");
                }
                System.out.println("Id " +  resultSet.getString("id") + "  " + resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}