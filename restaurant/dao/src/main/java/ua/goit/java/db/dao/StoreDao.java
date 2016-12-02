package ua.goit.java.db.dao;

import ua.goit.java.db.Store;

import java.util.List;

/**
 * Created by Alena on 30.11.2016.
 */
public interface StoreDao {
    int add(int ingredientId, float quantity);

    int delete(int id);

    Store findByIngredientId(int ingredientId);

    List<Store> getAll();

    void setQuantity(int id, float quantity);
}
