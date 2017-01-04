package ua.goit.java.db.dao;

import ua.goit.java.db.Position;

import java.util.List;

public interface PositionDao {

    int add(String name);

    void delete(int id);

    Position getById(int id);

    List<Position> getAll();

    Position findByName(String name);
}
