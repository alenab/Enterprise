package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Dish;
import ua.goit.java.db.Menu;
import ua.goit.java.db.dao.DishDao;
import ua.goit.java.db.dao.MenuDao;

import java.util.List;

public class HMenuDao implements MenuDao {

    private SessionFactory sessionFactory;
    private DishDao dishDao;

    public HMenuDao(SessionFactory sessionFactory, DishDao dishDao) {
        this.sessionFactory = sessionFactory;
        this.dishDao = dishDao;
    }

    @Transactional
    @Override
    public int add(String name) {
        Menu menu = new Menu();
        menu.setName(name);
        return (int) sessionFactory.getCurrentSession().save(menu);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Menu where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public List<Menu> findByName(String name) {
        Query<Menu> query = sessionFactory.getCurrentSession().createQuery("select m from Menu m where lower (m.name) like lower(:name)", Menu.class);
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Menu> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select m from Menu m", Menu.class).list();

    }

    @Transactional
    public Menu getById(int id) {
        Query<Menu> query = sessionFactory.getCurrentSession().createQuery("select m from Menu m" +
                " where m.id = :id", Menu.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Transactional
    @Override
    public int addDish(int menuId, int dishId) {
        Menu menu = getById(menuId);
        Dish dish = dishDao.getById(dishId);
        menu.getDishes().add(dish);
        return (int) sessionFactory.getCurrentSession().save(menu);

    }

    @Transactional
    @Override
    public void deleteDish(int menuId, int dishId) {
        Menu menu = getById(menuId);
        Dish dish = dishDao.getById(dishId);
        menu.getDishes().remove(dish);
        sessionFactory.getCurrentSession().save(menu);
    }

    @Transactional
    @Override
    public List<Dish> getDishesList(int menuId) {
        return getById(menuId).getDishes();
    }
}
