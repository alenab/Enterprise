package ua.goit.java.db.dao;

import ua.goit.java.db.Category;

import java.util.List;

public interface CategoryDao {
    int add(String name);

    int delete(int id);

    Category getById(int id);

    List<Category> getAll();
}
