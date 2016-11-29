package ua.goit.java.dao;

import ua.goit.java.Employee;
import ua.goit.java.OrderedDish;
import ua.goit.java.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";


    public int add(int employeeId, int tableNumber, Date orderDate) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDERS VALUES (DEFAULT, ?, ?, ?, ?)")) {
            statement.setInt(1, employeeId);
            statement.setInt(2, tableNumber);
            statement.setDate(3, orderDate);
            statement.setString(4, "open");
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Orders> getAll() {
        List<Orders> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM ORDERS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Orders orders = createOrder(resultSet);
                result.add(orders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Orders getById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS WHERE ORDER_ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createOrder(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setClose(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("UPDATE ORDERS SET STATUS='close' WHERE ORDER_ID = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Orders createOrder(ResultSet resultSet) throws SQLException {
        Orders orders = new Orders();
        orders.setOrderId(resultSet.getInt("order_id"));
        Employee employee = new EmployeeDao().getById(resultSet.getInt("employee_id"));
        orders.setEmployee(employee);
        orders.setTableNumber(resultSet.getInt("table_number"));
        orders.setOrdersDate(resultSet.getDate("orders_date").toLocalDate());
        orders.setStatus(resultSet.getString("status"));
        return orders;
    }

    public int addDish(int orderId, int dishId, int amountDish) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDERS_DISH VALUES (?, ?, ?)")) {
            statement.setInt(1, orderId);
            statement.setInt(2, dishId);
            statement.setInt(3, amountDish);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteDish(int orderId, int dishId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM ORDERS_DISH WHERE ORDER_ID = ? AND DISH_ID = ? ")) {
            statement.setInt(1, orderId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<OrderedDish> getOrderedDishesByOrderId(int id) {
        List<OrderedDish> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT DISH_ID, AMOUNT_DISH FROM ORDERS_DISH WHERE ORDER_ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderedDish orderedDish = createOrderedDish(resultSet);
                result.add(orderedDish);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Dish> getDishesByOrderId(int orderId) {
        List<Dish> orderedDishes = new ArrayList<>();
        for (OrderedDish orderedDish : getOrderedDishesByOrderId(orderId)) {
            for (int quantity = 0; quantity < orderedDish.getQuantity(); quantity++) {
                orderedDishes.add(orderedDish.getDish());
            }
        }
        return orderedDishes;
    }

    private OrderedDish createOrderedDish(ResultSet resultSet) throws SQLException {
        OrderedDish orderedDish = new OrderedDish();
        Dish dish = new DishDao().getById(resultSet.getInt("dish_id"));
        orderedDish.setDish(dish);
        orderedDish.setQuantity(resultSet.getInt("amount_dish"));
        return orderedDish;
    }
}

