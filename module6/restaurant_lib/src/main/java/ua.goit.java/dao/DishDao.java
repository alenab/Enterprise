package ua.goit.java.dao;

import ua.goit.java.Dish;
import ua.goit.java.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(String name, int categoryId, float price, float weight) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO DISH VALUES (DEFAULT, ?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, categoryId);
            statement.setFloat(3, price);
            statement.setFloat(4, weight);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM DISH WHERE DISH_ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Dish> findByName(String name) {
        List<Dish> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISH WHERE NAME = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dish dish = createDish(resultSet);
                result.add(dish);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Dish> getAll() {
        List<Dish> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM DISH";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Dish dish = createDish(resultSet);
                result.add(dish);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Dish createDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setDishID(resultSet.getInt("dish_id"));
        dish.setName(resultSet.getString("name"));
        dish.setCategoryId(resultSet.getInt("category_id"));
        dish.setPrice(resultSet.getFloat("price"));
        dish.setWeight(resultSet.getFloat("weight"));
        return dish;
    }
}
