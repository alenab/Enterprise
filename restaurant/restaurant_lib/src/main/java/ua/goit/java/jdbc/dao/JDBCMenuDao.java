package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Menu;
import ua.goit.java.db.dao.MenuDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMenuDao implements MenuDao {

    private DataSource dataSource;
    private JDBCDishDao dishDao;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDishDao(JDBCDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public int add(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO MENU VALUES (DEFAULT, ?)")) {
            statement.setString(1, name);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM MENU WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Menu> findByName(String name) {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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

    @Override
    public List<Menu> getAll() {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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

    @Override
    public int addDish(int menuId, int dishId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO MENU_DISH VALUES (?, ?)")) {
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteDish(int menuId, int dishId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM MENU_DISH WHERE MENU_ID = ? AND DISH_ID = ? ")) {
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Dish> getDishesList(int menuId) {
        List<Dish> listOfDishes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT DISH_ID FROM MENU_DISH WHERE MENU_ID = ?")) {
            statement.setInt(1, menuId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listOfDishes.add(dishDao.getById(resultSet.getInt("dish_id")));
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
