package ua.goit.java.dao;

import ua.goit.java.Employee;
import ua.goit.java.Position;
import ua.goit.java.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDao {
    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(int ingredientId, float quantity) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO STORE VALUES (DEFAULT, ?)")) {
            statement.setInt(1,ingredientId);
            statement.setFloat(2, quantity);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM STORE WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // List or Store
    public List<Store> findByName(String name) {
        List<Store> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM STORE WHERE  NAME = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Store store = createStore(resultSet);
                result.add(store);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Store> getAll() {
        List<Store> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM STORE";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Store store = createStore(resultSet);
                result.add(store);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Store createStore(ResultSet resultSet) throws SQLException {
        Store store  = new Store();
        store.setId(resultSet.getInt("id"));
        store.setIngredientId(resultSet.getInt("ingredient_id"));
        store.setQuantity(resultSet.getFloat("quantity"));
        return store;
    }
}
