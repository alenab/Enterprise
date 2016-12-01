package ua.goit.java.db.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Menu;

import java.util.List;

/**
 * Created by Alena on 30.11.2016.
 */
public interface MenuDao {
    int add(String name);

    int delete(int id);

    List<Menu> findByName(String name);

    List<Menu> getAll();

    int addDish(int menuId, int dishId);

    int deleteDish(int menuId, int dishId);

    List<Dish> getDishesList(int menuId);
}
