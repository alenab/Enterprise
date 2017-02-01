package ua.goit.java.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Order;
import ua.goit.java.db.dao.OrderDao;

import java.util.List;

@Service
public class OrderService {

    private OrderDao orderDao;

    @Transactional
    public List<Order> getOrders() {
        return orderDao.getAll();
    }

    @Transactional
    public List<Order> getOpen() {
        return orderDao.getOpen();
    }

    @Transactional
    public List<Order> getClosed() {
        return orderDao.getClosed();
    }

    @Transactional
    public Order getOrderById(int id) {
        return orderDao.getById(id);
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


}
