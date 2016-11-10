package ua.goit.java.dao;

import ua.goit.java.Category;
import ua.goit.java.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(String name) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CATEGORY VALUES (DEFAULT, ?)")) {
            statement.setString(1, name);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM CATEGORY WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Category getById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CATEGORY WHERE  ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createCategory(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getAll() {
        List<Category> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM CATEGORY";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = createCategory(resultSet);
                result.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Category createCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}