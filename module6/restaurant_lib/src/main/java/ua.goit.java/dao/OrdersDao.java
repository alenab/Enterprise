package ua.goit.java.dao;

import ua.goit.java.Dish;
import ua.goit.java.Employee;
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
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDERS VALUES (DEFAULT, ?, ?, ?)")) {
            statement.setInt(1, employeeId);
            statement.setInt(2, tableNumber);
            statement.setDate(3,orderDate) ;
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

    private Orders createOrder(ResultSet resultSet) throws SQLException {
        Orders orders = new Orders();
        orders.setOrderId(resultSet.getInt("order_id"));
        orders.setEmployeeID(resultSet.getInt("employee_id"));
        orders.setTableNumber(resultSet.getInt("table_number"));
        orders.setOrdersDate(resultSet.getDate("orders_date").toLocalDate());
        return orders;
    }
}
