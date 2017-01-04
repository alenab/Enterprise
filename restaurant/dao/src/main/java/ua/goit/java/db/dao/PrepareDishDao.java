package ua.goit.java.db.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.PrepareDish;

import java.sql.Date;
import java.util.List;

public interface PrepareDishDao {

    int add(int dishId, int employeeId, int orderId, Date prepareDate);

    List<PrepareDish> getAll();

    List<PrepareDish> getByOrderId(int orderId);

    List<Dish> needToPrepare();
}
