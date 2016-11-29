package ua.goit.java.dao;

import ua.goit.java.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(String name) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO MENU VALUES (DEFAULT, ?)")) {
            statement.setString(1, name);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM MENU WHERE DISH_ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Menu> findByName(String name) {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM MENU WHERE LOWER(NAME) LIKE (?)")) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Menu menu = createMenu(resultSet);
                result.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Menu> getAll() {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM MENU";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Menu menu = createMenu(resultSet);
                result.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int addDish(int menuId, int dishId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO MENU_DISH VALUES (?, ?)")) {
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteDish(int menuId, int dishId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM MENU_DISH WHERE MENU_ID = ? AND DISH_ID = ? ")) {
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Dish> getDishesList(int menuId) {
        List<Dish> listOfDishes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT DISH_ID FROM MENU_DISH WHERE MENU_ID = ?")) {
            statement.setInt(1, menuId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listOfDishes.add(new DishDao().getById(resultSet.getInt("dish_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfDishes;
    }

    private Menu createMenu(ResultSet resultSet) throws SQLException {
        Menu menu = new Menu();
        int id = resultSet.getInt("id");
        menu.setId(id);
        menu.setDishes(getDishesList(id));
        menu.setName(resultSet.getString("name"));
        return menu;
    }
}
