package ua.goit.java.db.dao;

import ua.goit.java.db.Ingredient;

import java.util.List;

public interface IngredientsDao {

    int add(String name, String measurement);

    int delete(int id);

    Ingredient getById(int id);

    List<Ingredient> findByName(String name);

    List<Ingredient> getAll();
}
