package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Store;
import ua.goit.java.db.dao.IngredientsDao;
import ua.goit.java.db.dao.StoreDao;

import java.util.List;

public class HStoreDao implements StoreDao {

    private SessionFactory sessionFactory;
    private IngredientsDao ingredientsDao;


    public HStoreDao(SessionFactory sessionFactory, IngredientsDao ingredientsDao) {
        this.sessionFactory = sessionFactory;
        this.ingredientsDao = ingredientsDao;
    }

    @Transactional
    @Override
    public int add(int ingredientId, float quantity) {
        Store store = new Store();
        store.setIngredient(ingredientsDao.getById(ingredientId));
        store.setQuantity(quantity);
        return (int) sessionFactory.getCurrentSession().save(store);
    }

    @Transactional
    @Override
    public int delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from  Store where id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Transactional
    @Override
    public Store findByIngredientId(int ingredientId) {
        Query query = sessionFactory.getCurrentSession().createQuery("select s from Store s where s.ingredient.id = :id");
        query.setParameter("id", ingredientId);
        return (Store) query.uniqueResult();
    }

    @Transactional
    @Override
    public List<Store> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from Store s ", Store.class).list();
    }

    @Transactional
    private Store getById(int id) {
        Query<Store> query = sessionFactory.getCurrentSession().createQuery("select s from Store s" +
                " where s.id = :id", Store.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Transactional
    @Override
    public void setQuantity(int id, float quantity) {
        Session session = sessionFactory.getCurrentSession();
        Store store = getById(id);
        store.setQuantity(quantity);
        session.save(store);

    }
}
