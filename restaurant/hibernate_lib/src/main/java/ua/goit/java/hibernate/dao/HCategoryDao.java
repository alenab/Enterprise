package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Category;
import ua.goit.java.db.dao.CategoryDao;

import java.util.List;

public class HCategoryDao implements CategoryDao {

    private SessionFactory sessionFactory;

    public HCategoryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HCategoryDao() {
    }

    @Transactional
    @Override
    public int add(String name) {
        Category category = new Category();
        category.setName(name);
        return (int) sessionFactory.getCurrentSession().save(category);

    }

    @Transactional
    @Override
    public int delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Category where id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Transactional
    @Override
    public Category getById(int id) {
        Query<Category> query = sessionFactory.getCurrentSession().createQuery("select c from Category c" +
                " where c.id = :id", Category.class);
        query.setParameter("id", id);
        return query.uniqueResult();

    }

    @Transactional
    @Override
    public List<Category> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> result = session.createQuery("select c from Category c", Category.class).list();
        return result;


    }
}
