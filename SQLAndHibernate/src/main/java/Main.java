import entities.LinkedPurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        try {
//            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
//            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//            Session session = sessionFactory.openSession();
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.println("Укажите Id курса \n " +
//                        "Чтобы узнать Id напишите \"Help\" \n " +
//                        "Чтобы завершить работу введите \"Stop\"");
//                String input = scanner.nextLine();
//                if (input.equals("Help")) {
//                    CourseSelector courseSelector = new CourseSelector();
//                    courseSelector.courseSelector();
//                }
//                else if (input.matches("[\\d]++")) {
//                    Course course = session.get(Course.class, Integer.parseInt(input));
//                    System.out.println("Курс  " + course.getName() + "  Количество студентов " + course.getStudentsCount() + "\n");
//                }
//                else if (input.equals("Stop")) {
//                    sessionFactory.close();
//                    break;
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("insert into LinkedPurchaseList as lpl (lpl.courseId, lpl.studentId) select c.id, s.id " +
                "from Course as c, Student as s join PurchaseList as pl " +
                "where c.name = pl.courseName AND s.name = pl.studentName").executeUpdate();
        List<LinkedPurchaseList> linkedPurchaseLists = session.createQuery("FROM LinkedPurchaseList").getResultList();
        for (LinkedPurchaseList linkedPurchaseList : linkedPurchaseLists) {
            System.out.println("Course Id: " + linkedPurchaseList.getCourseId() + " " + "Student Id: " + linkedPurchaseList.getStudent_id());
        }
        transaction.commit();
        sessionFactory.close();
    }
}