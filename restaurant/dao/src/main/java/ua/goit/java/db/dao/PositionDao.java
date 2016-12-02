package ua.goit.java.db.dao;

import ua.goit.java.db.Position;

import java.util.List;

/**
 * Created by Alena on 30.11.2016.
 */
public interface PositionDao {
    int add(String name);

    int delete(int id);

    Position getById(int id);

    List<Position> getAll();
}
