package ua.goit.java.dao;

import ua.goit.java.Orders;
import ua.goit.java.OrdersDish;
import ua.goit.java.PrepareDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareDishDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(int dishId, int employeeId, int orderId, Date prepareDate) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
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

    public List<PrepareDish> getAll() {
        List<PrepareDish> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
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

    private PrepareDish createPrepareDish(ResultSet resultSet) throws SQLException {
        PrepareDish prepareDish = new PrepareDish();
        prepareDish.setId(resultSet.getInt("id"));
        prepareDish.setDishId(resultSet.getInt("dish_id"));
        prepareDish.setEmployeeID(resultSet.getInt("employee_id"));
        prepareDish.setOrderId(resultSet.getInt("order_id"));
        prepareDish.setPrepareDate(resultSet.getDate("preare_date").toLocalDate());
        return prepareDish;
    }
}
