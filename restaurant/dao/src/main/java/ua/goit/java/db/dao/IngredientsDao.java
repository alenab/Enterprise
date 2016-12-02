package ua.goit.java.db.dao;

import ua.goit.java.db.Ingredients;

import java.util.List;

/**
 * Created by Alena on 30.11.2016.
 */
public interface IngredientsDao {
    int add(String name, String measurement);

    int delete(int id);

    Ingredients getById(int id);

    Ingredients findByName(String name);

    List<Ingredients> getAll();
}
