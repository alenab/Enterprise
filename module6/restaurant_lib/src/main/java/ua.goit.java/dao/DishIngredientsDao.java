package ua.goit.java.dao;

import ua.goit.java.DishIngredients;
import ua.goit.java.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishIngredientsDao {
    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(int dishId, int ingredientId, float ingredientQuantity ) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO DISH_INGREDIENTS VALUES (?, ?, ?)")) {
            statement.setInt(1, dishId);
            statement.setInt(2, ingredientId);
            statement.setFloat(3, ingredientQuantity);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int dishId, int ingredientId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM DISH_INGREDIENTS WHERE DISH_ID = ? AND INGREDIENT_QUANTITY = ? ")) {
            statement.setInt(1, dishId);
            statement.setInt(2, ingredientId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<DishIngredients> findById(int dishId) {
        List<DishIngredients> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISH_INGREDIENTS WHERE  DISH_ID = ?")) {
            statement.setInt(1, dishId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DishIngredients dishIngredients = createDishIngredients(resultSet);
                result.add(dishIngredients);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private DishIngredients createDishIngredients(ResultSet resultSet) throws SQLException {
        DishIngredients dishIngredients = new DishIngredients();
        dishIngredients.setDishId(resultSet.getInt("dish_id"));
        dishIngredients.setIngredientsId(resultSet.getInt("ingredient_id"));
        dishIngredients.setIngredientsQuantity(resultSet.getFloat("ingredient_quantity"));
        return dishIngredients;
    }
}
