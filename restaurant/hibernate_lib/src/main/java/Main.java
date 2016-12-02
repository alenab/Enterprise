import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.goit.java.hidernate.dao.HCategoryDao;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            HCategoryDao hCategoryDao = new HCategoryDao(sessionFactory);

            hCategoryDao.delete(4);

            System.out.println("deleted");
            hCategoryDao.getAll().forEach(System.out::println);
        }
    }
}
