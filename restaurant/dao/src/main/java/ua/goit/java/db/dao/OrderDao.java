package ua.goit.java.db.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.OrderedDish;
import ua.goit.java.db.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao {

    int add(int employeeId, int tableNumber, Date orderDate);

    void delete(int id);

    List<Order> getAll();

    Order getById(int id);

    void setClose(int id);

    int addDish(int orderId, int dishId, int amountDish);

    void deleteDish(int orderId, int dishId);

    List<OrderedDish> getOrderedDishesByOrderId(int id);

}
