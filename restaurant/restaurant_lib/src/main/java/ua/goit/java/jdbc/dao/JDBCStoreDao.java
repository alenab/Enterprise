package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Ingredient;
import ua.goit.java.db.Store;
import ua.goit.java.db.dao.IngredientsDao;
import ua.goit.java.db.dao.StoreDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStoreDao implements StoreDao {

    private DataSource dataSource;
    private IngredientsDao ingredientsDao;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setIngredientsDao(JDBCIngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    @Override
    public int add(int ingredientId, float quantity) {
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM STORE WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Store findByIngredientId(int ingredientId) {
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
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
        Ingredient ingredient = ingredientsDao.getById(resultSet.getInt("ingredient_id"));
        store.setIngredient(ingredient);
        store.setQuantity(resultSet.getFloat("quantity"));
        return store;
    }
}
