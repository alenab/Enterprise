package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Category;
import ua.goit.java.db.Dish;
import ua.goit.java.db.Ingredients;
import ua.goit.java.db.dao.DishDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDishDao implements DishDao {

    private DataSource dataSource;
    private JDBCCategoryDao categoryDao;
    private JDBCIngredientsDao ingredientsDao;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setCategoryDao(JDBCCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void setIngredientsDao(JDBCIngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    @Override
    public int add(String name, int categoryId, float price, float weight) {
        try (Connection connection = dataSource.getConnection();
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

    @Override
    public int delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM DISH WHERE DISH_ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Dish> findByName(String name) {
        List<Dish> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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

    @Override
    public List<Dish> getAll() {
        List<Dish> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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

    @Override
    public Dish getById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISH WHERE  DISH_ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createDish(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ingredients> getIngredientsList(int dishId) {
        List<Ingredients> listOfIngredients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT INGREDIENT_ID FROM DISH_INGREDIENTS WHERE DISH_ID = ?")) {
            statement.setInt(1, dishId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listOfIngredients.add(ingredientsDao.getById(resultSet.getInt("INGREDIENT_ID")));
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
        Category category = categoryDao.getById(resultSet.getInt("category_id"));
        dish.setCategory(category);
        dish.setPrice(resultSet.getFloat("price"));
        dish.setWeight(resultSet.getFloat("weight"));
        dish.setIngredients(getIngredientsList(id));
        return dish;
    }
}
