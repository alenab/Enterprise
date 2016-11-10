package ua.goit.java.dao;

import ua.goit.java.MenuDish;
import ua.goit.java.OrdersDish;

import java.sql.*;

public class OrdersDishDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(int orderId, int dishId, int amountDish) {
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

    public int delete(int orderId, int dishId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM ORDERS_DISH WHERE ORDER_ID = ? AND DISH_ID = ? ")) {
            statement.setInt(1, orderId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private OrdersDish createOrdersDish(ResultSet resultSet) throws SQLException {
        OrdersDish ordersDish = new OrdersDish();
        ordersDish.setOrderId(resultSet.getInt("orders_id"));
        ordersDish.setDishId(resultSet.getInt("dish_id"));
        ordersDish.setAmountDish(resultSet.getInt("amount_dish"));
        return ordersDish;
    }
}
