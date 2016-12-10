package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Ingredient;
import ua.goit.java.db.dao.IngredientsDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCIngredientsDao implements IngredientsDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(String name, String measurement) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO INGREDIENTS VALUES (DEFAULT, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, measurement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM INGREDIENTS WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Ingredient getById(int id) {
        try (Connection connection = dataSource.getConnection();
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

    @Override
    public Ingredient findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM INGREDIENTS WHERE LOWER (NAME) like LOWER (?)")) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               return createIngredients(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM INGREDIENTS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Ingredient ingredient = createIngredients(resultSet);
                result.add(ingredient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Ingredient createIngredients(ResultSet resultSet) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setName(resultSet.getString("name"));
        ingredient.setMeasurement(resultSet.getString("measurement"));
        return ingredient;
    }
}
