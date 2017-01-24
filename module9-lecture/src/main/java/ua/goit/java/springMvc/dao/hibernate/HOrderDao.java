package ua.goit.java.springMvc.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.java.springMvc.dao.OrderDao;
import ua.goit.java.springMvc.model.Orders;


import java.util.List;

public class HOrderDao implements OrderDao {

    private SessionFactory sessionFactory;

    @Override
    public void save(Orders orders) {
        sessionFactory.getCurrentSession().save(orders);

    }

    @Override
    public List<Orders> findAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("select o from Orders o").list();
    }

    @Override
    public void removeAll() {
        sessionFactory.getCurrentSession().createQuery("delete from Orders").executeUpdate();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
