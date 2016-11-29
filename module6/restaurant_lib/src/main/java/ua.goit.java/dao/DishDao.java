package ua.goit.java.dao;

import ua.goit.java.Ingredients;

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
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISH WHERE LOWER(NAME) LIKE LOWER(?)")) {
            statement.setString(1, "%" + name + "%");
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

    public Dish getById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISH WHERE  DISH_ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createDish(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ingredients> getIngredientsList(int dishId) {
        List<Ingredients> listOfIngredients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT INGREDIENT_ID FROM DISH_INGREDIENTS WHERE DISH_ID = ?")) {
            statement.setInt(1, dishId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listOfIngredients.add(new IngredientsDao().getById(resultSet.getInt("INGREDIENT_ID")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfIngredients;
    }

    private Dish createDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        int id = resultSet.getInt("dish_id");
        dish.setDishID(id);
        dish.setName(resultSet.getString("name"));
        Category category = new CategoryDao().getById(resultSet.getInt("category_id"));
        dish.setCategory(category);
        dish.setPrice(resultSet.getFloat("price"));
        dish.setWeight(resultSet.getFloat("weight"));
        dish.setIngredients(getIngredientsList(id));
        return dish;
    }
}
