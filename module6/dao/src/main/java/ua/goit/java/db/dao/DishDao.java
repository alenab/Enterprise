package ua.goit.java.db.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Ingredients;

import java.util.List;

/**
 * Created by Alena on 30.11.2016.
 */
public interface DishDao {
    int add(String name, int categoryId, float price, float weight);

    int delete(int id);

    List<Dish> findByName(String name);

    List<Dish> getAll();

    Dish getById(int id);

    List<Ingredients> getIngredientsList(int dishId);
}
