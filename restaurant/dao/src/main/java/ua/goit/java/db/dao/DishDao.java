package ua.goit.java.db.dao;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Ingredient;

import java.util.List;

public interface DishDao {

    int add(String name, int categoryId, float price, float weight);

    void delete(int id);

    List<Dish> findByName(String name);

    List<Dish> getAll();

    Dish getById(int id);

    List<Ingredient> getIngredientsList(int dishId);
}
