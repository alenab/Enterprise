package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.PrepareDish;
import ua.goit.java.db.dao.DishDao;
import ua.goit.java.db.dao.EmployeeDao;
import ua.goit.java.db.dao.OrderDao;
import ua.goit.java.db.dao.PrepareDishDao;

import java.sql.Date;
import java.util.List;

public class HPrepareDishDao implements PrepareDishDao {

    private SessionFactory sessionFactory;

    private DishDao dishDao;
    private EmployeeDao employeeDao;
    private OrderDao orderDao;

    public HPrepareDishDao(SessionFactory sessionFactory, DishDao dishDao, EmployeeDao employeeDao, OrderDao orderDao) {
        this.sessionFactory = sessionFactory;
        this.dishDao = dishDao;
        this.employeeDao = employeeDao;
        this.orderDao = orderDao;
    }

    public HPrepareDishDao() {
    }

    @Transactional
    @Override
    public int add(int dishId, int employeeId, int orderId, Date prepareDate) {
        PrepareDish prepareDish = new PrepareDish();
        prepareDish.setDish(dishDao.getById(dishId));
        prepareDish.setEmployee(employeeDao.getById(employeeId));
        prepareDish.setOrder(orderDao.getById(orderId));
        prepareDish.setPrepareDate(prepareDate.toLocalDate());
        return (int) sessionFactory.getCurrentSession().save(prepareDish);
    }

    @Transactional
    @Override
    public List<PrepareDish> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from PrepareDish p", PrepareDish.class).list();

    }

    @Transactional(readOnly = true)
    @Override
    public List<PrepareDish> getByOrderId(int orderId) {
        Query<PrepareDish> query = sessionFactory.getCurrentSession().createQuery("select d from PrepareDish d" +
                " where d.order.orderId = :id", PrepareDish.class);
        query.setParameter("id", orderId);
        return query.list();
    }
}
