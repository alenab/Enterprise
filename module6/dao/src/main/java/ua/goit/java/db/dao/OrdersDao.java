package ua.goit.java.db.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.OrderedDish;
import ua.goit.java.db.Orders;

import java.sql.Date;
import java.util.List;

/**
 * Created by Alena on 30.11.2016.
 */
public interface OrdersDao {
    int add(int employeeId, int tableNumber, Date orderDate);

    int delete(int id);

    List<Orders> getAll();

    Orders getById(int id);

    void setClose(int id);

    int addDish(int orderId, int dishId, int amountDish);

    int deleteDish(int orderId, int dishId);

    List<OrderedDish> getOrderedDishesByOrderId(int id);

    List<Dish> getDishesByOrderId(int orderId);
}
