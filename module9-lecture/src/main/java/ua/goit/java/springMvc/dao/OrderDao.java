package ua.goit.java.springMvc.dao;
import ua.goit.java.springMvc.model.Orders;

import java.util.List;

public interface OrderDao {

    void save(Orders orders);

    List<Orders> findAllOrders();

    void removeAll();

}
