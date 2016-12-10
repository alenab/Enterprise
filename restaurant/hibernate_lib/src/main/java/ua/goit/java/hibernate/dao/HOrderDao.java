package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Dish;
import ua.goit.java.db.Employee;
import ua.goit.java.db.Order;
import ua.goit.java.db.OrderedDish;
import ua.goit.java.db.dao.DishDao;
import ua.goit.java.db.dao.EmployeeDao;
import ua.goit.java.db.dao.OrderDao;

import java.sql.Date;
import java.util.List;

public class HOrderDao implements OrderDao {

    private SessionFactory sessionFactory;

    private DishDao dishDao;
    private EmployeeDao employeeDao;

    public HOrderDao(SessionFactory sessionFactory, DishDao dishDao, EmployeeDao employeeDao) {
        this.sessionFactory = sessionFactory;
        this.dishDao = dishDao;
        this.employeeDao = employeeDao;
    }

    @Transactional
    @Override
    public int add(int employeeId, int tableNumber, Date orderDate) {
        Order order = new Order();
        order.setEmployee(employeeDao.getById(employeeId));
        order.setTableNumber(tableNumber);
        order.setOrdersDate(orderDate.toLocalDate());
        order.setStatus("open");
        return (int) sessionFactory.getCurrentSession().save(order);
    }

    @Transactional
    @Override
    public int delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Order o where o.orderId = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Transactional
    @Override
    public List<Order> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Order o", Order.class).list();
    }

    @Transactional
    @Override
    public Order getById(int id) {
        Query<Order> query = sessionFactory.getCurrentSession().createQuery("select o from Order o" +
                " where o.orderId = :id", Order.class);
        query.setParameter("id", id);
        return query.uniqueResult();

    }

    @Transactional
    @Override
    public void setClose(int id) {
        Order order = getById(id);
        order.setStatus("close");
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    public int addDish(int orderId, int dishId, int amountDish) {
        OrderedDish orderedDish = new OrderedDish();

        Dish dish = dishDao.getById(dishId);
        orderedDish.setOrder(getById(orderId));
        orderedDish.setDish(dish);
        orderedDish.setQuantity(amountDish);

        return (int) sessionFactory.getCurrentSession().save(orderedDish);
    }

    @Transactional
    @Override
    public int deleteDish(int orderId, int dishId) {
        Order order = getById(orderId);

        Dish dish = dishDao.getById(dishId);
        List<OrderedDish> dishList = order.getOrderedDish();
        for (OrderedDish oDish : dishList) {
            if (oDish.getDish().equals(dish)) {
                sessionFactory.getCurrentSession().remove(oDish);
                break;
            }
        }
        return 1;
    }

    @Transactional
    @Override
    public List<OrderedDish> getOrderedDishesByOrderId(int id) {
        return getById(id).getOrderedDish();
    }

}
