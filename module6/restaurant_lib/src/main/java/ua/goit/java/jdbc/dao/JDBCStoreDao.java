package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Ingredients;
import ua.goit.java.db.Store;
import ua.goit.java.db.dao.StoreDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStoreDao implements StoreDao {
    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    @Override
    public int add(int ingredientId, float quantity) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO STORE VALUES (DEFAULT, ?, ?)")) {
            statement.setInt(1, ingredientId);
            statement.setFloat(2, quantity);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM STORE WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Store findByIngredientId(int ingredientId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM STORE WHERE  INGREDIENT_ID = ?")) {
            statement.setInt(1, ingredientId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createStore(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public void setQuantity(int id, float quantity) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("UPDATE STORE SET QUANTITY = ? WHERE ID = ?")) {
            statement.setInt(2, id);
            statement.setFloat(1, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Store createStore(ResultSet resultSet) throws SQLException {
        Store store = new Store();
        store.setId(resultSet.getInt("id"));
        Ingredients ingredients = new JDBCIngredientsDao().getById(resultSet.getInt("ingredient_id"));
        store.setIngredient(ingredients);
        store.setQuantity(resultSet.getFloat("quantity"));
        return store;
    }
}
