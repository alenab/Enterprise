package ua.goit.java.dao;

import ua.goit.java.Menu;
import ua.goit.java.MenuDish;

import java.sql.*;

public class MenuDishDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";


    public int add(int menuId, int dishId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO MENU_DISH VALUES (?, ?)")) {
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int menuId, int dishId) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM MENU_DISH WHERE MENU_ID = ? AND DISH_ID = ? ")) {
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private MenuDish createMenuDish(ResultSet resultSet) throws SQLException {
        MenuDish menuDish = new MenuDish();
        menuDish.setMenuId(resultSet.getInt("menu_id"));
        menuDish.setDishId(resultSet.getInt("dish_id"));
        return menuDish;
    }
}
