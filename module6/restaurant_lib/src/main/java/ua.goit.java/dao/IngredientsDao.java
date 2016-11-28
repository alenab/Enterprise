package ua.goit.java.dao;

import ua.goit.java.Employee;
import ua.goit.java.Ingredients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(String name, String measurement) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO INGREDIENTS VALUES (DEFAULT, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, measurement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM INGREDIENTS WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Ingredients getById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM INGREDIENTS WHERE  ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            return createIngredients(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Ingredients findByName(String name) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM  INGREDIENTS WHERE  NAME = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createIngredients(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ingredients> getAll() {
        List<Ingredients> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM INGREDIENTS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Ingredients ingredients = createIngredients(resultSet);
                result.add(ingredients);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Ingredients createIngredients(ResultSet resultSet) throws SQLException {
        Ingredients ingredients = new Ingredients();
        ingredients.setId(resultSet.getInt("id"));
        ingredients.setName(resultSet.getString("name"));
        ingredients.setMeasurement(resultSet.getString("measurement"));
        return ingredients;
    }
}
