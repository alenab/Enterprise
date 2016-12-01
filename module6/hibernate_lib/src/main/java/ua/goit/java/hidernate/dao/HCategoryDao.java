package ua.goit.java.hidernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.goit.java.db.Category;
import ua.goit.java.db.dao.CategoryDao;

import java.util.List;

public class HCategoryDao implements CategoryDao {

    private SessionFactory sessionFactory;

    public HCategoryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public int add(String name) {
        Category category = new Category();
        category.setName(name);
        return (int) sessionFactory.getCurrentSession().save(category);

    }

    @Override
    public int delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("delete Category where id = :ID");
        query.setParameter("ID", id);

        int result = query.executeUpdate();

        session.getTransaction().commit();
        return result;
    }

    @Override
    public Category getById(int id) {
        Query<Category> query = sessionFactory.getCurrentSession().createQuery("select c from Category c" +
                " where c.id = :id", Category.class);
        query.setParameter("id", id);
        return query.uniqueResult();

    }

    @Override
    public List<Category> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Category> result= session.createQuery("select c from Category c", Category.class).list();
        session.getTransaction().commit();
        return result;


    }
}
