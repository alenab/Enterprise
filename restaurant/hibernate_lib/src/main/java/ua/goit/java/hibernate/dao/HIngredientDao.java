package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Ingredient;
import ua.goit.java.db.dao.IngredientsDao;

import java.util.List;

public class HIngredientDao implements IngredientsDao {

    private SessionFactory sessionFactory;

    public HIngredientDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HIngredientDao() {
    }

    @Transactional
    @Override
    public int add(String name, String measurement) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setMeasurement(measurement);
        return (int) sessionFactory.getCurrentSession().save(ingredient);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Ingredient where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    @Transactional
    @Override
    public Ingredient getById(int id) {
        Query<Ingredient> query = sessionFactory.getCurrentSession().createQuery("select i from Ingredient i" +
                " where i.id = :id", Ingredient.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Transactional
    @Override
    public List<Ingredient> findByName(String name) {
        Query<Ingredient> query = sessionFactory.getCurrentSession().createQuery("select i from Ingredient i where" +
                " lower (i.name) like lower(:name)", Ingredient.class);
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Transactional
    @Override
    public List<Ingredient> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select i from Ingredient i", Ingredient.class).list();

    }
}
