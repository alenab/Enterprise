package ua.goit.java.db.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Menu;

import java.util.List;

public interface MenuDao {

    int add(String name);

    void delete(int id);

    List<Menu> findByName(String name);

    List<Menu> getAll();

    int addDish(int menuId, int dishId);

    void deleteDish(int menuId, int dishId);

    List<Dish> getDishesList(int menuId);
}
