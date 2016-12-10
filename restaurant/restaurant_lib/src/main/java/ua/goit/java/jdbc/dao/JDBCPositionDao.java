package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Position;
import ua.goit.java.db.dao.PositionDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPositionDao implements PositionDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO POSITION VALUES (DEFAULT, ?)")) {
            statement.setString(1, name);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM POSITION WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Position getById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM POSITION WHERE  ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createPosition(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Position> getAll() {
        List<Position> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM POSITION";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Position position = createPosition(resultSet);
                result.add(position);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Position createPosition(ResultSet resultSet) throws SQLException {
        Position position = new Position();
        position.setId(resultSet.getInt("id"));
        position.setName(resultSet.getString("name"));
        return position;
    }
}