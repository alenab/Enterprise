package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Dish;
import ua.goit.java.db.Ingredient;
import ua.goit.java.db.dao.CategoryDao;
import ua.goit.java.db.dao.DishDao;

import java.util.List;

public class HDishDao implements DishDao {

    private SessionFactory sessionFactory;
    private CategoryDao categoryDao;

    public HDishDao(SessionFactory sessionFactory, CategoryDao categoryDao) {
        this.sessionFactory = sessionFactory;
        this.categoryDao = categoryDao;
    }

    @Transactional
    @Override
    public int add(String name, int categoryId, float price, float weight) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setCategory(categoryDao.getById(categoryId));
        dish.setPrice(price);
        dish.setWeight(weight);
        return (int) sessionFactory.getCurrentSession().save(dish);
    }

    @Transactional
    @Override
    public int delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Dish where dishId = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Transactional
    @Override
    public List<Dish> findByName(String name) {
        Query<Dish> query = sessionFactory.getCurrentSession().createQuery("select d from Dish d where " +
                "lower(d.name) like lower (:name)", Dish.class);
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Dish> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Dish d", Dish.class).list();

    }

    @Transactional
    @Override
    public Dish getById(int id) {
        Query<Dish> query = sessionFactory.getCurrentSession().createQuery("select d from Dish d" +
                " where d.dishId = :id", Dish.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Transactional
    @Override
    public List<Ingredient> getIngredientsList(int dishId) {
        return getById(dishId).getIngredients();
    }
}
