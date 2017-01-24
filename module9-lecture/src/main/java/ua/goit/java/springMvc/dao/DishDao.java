package ua.goit.java.springMvc.dao;

import ua.goit.java.springMvc.model.Dish;

import java.util.List;

public interface DishDao {

    void save(Dish dish);

    List<Dish> findAll();

    Dish findByName (String name);

    void removeAll();
}
