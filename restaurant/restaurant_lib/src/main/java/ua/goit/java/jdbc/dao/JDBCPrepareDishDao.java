package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Employee;
import ua.goit.java.db.Order;
import ua.goit.java.db.PrepareDish;
import ua.goit.java.db.dao.DishDao;
import ua.goit.java.db.dao.EmployeeDao;
import ua.goit.java.db.dao.OrderDao;
import ua.goit.java.db.dao.PrepareDishDao;
import ua.goit.java.hibernate.dao.HDishDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPrepareDishDao implements PrepareDishDao {

    private DataSource dataSource;
    private DishDao dishDao;
    private EmployeeDao employeeDao;
    private OrderDao ordersDao;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDishDao(HDishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setEmployeeDao(JDBCEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setOrdersDao(JDBCOrderDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    @Override
    public int add(int dishId, int employeeId, int orderId, Date prepareDate) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO PREPARE_DISH VALUES (default, ?, ?, ?, ?)")) {
            statement.setInt(1, dishId);
            statement.setInt(2, employeeId);
            statement.setInt(3, orderId);
            statement.setDate(4, prepareDate);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PrepareDish> getAll() {
        List<PrepareDish> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM PREPARE_DISH";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                PrepareDish prepareDish = createPrepareDish(resultSet);
                result.add(prepareDish);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<PrepareDish> getByOrderId(int orderId) {
        List<PrepareDish> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PREPARE_DISH WHERE ORDER_ID = ?")) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PrepareDish prepareDish = createPrepareDish(resultSet);
                result.add(prepareDish);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PrepareDish createPrepareDish(ResultSet resultSet) throws SQLException {
        PrepareDish prepareDish = new PrepareDish();
        prepareDish.setId(resultSet.getInt("id"));
        Dish dish = dishDao.getById(resultSet.getInt("dish_id"));
        prepareDish.setDish(dish);
        Employee employee = employeeDao.getById(resultSet.getInt("employee_id"));
        prepareDish.setEmployee(employee);
        Order order = ordersDao.getById(resultSet.getInt("order_id"));
        prepareDish.setOrder(order);
        prepareDish.setPrepareDate(resultSet.getDate("prepare_date").toLocalDate());
        return prepareDish;
    }
}
