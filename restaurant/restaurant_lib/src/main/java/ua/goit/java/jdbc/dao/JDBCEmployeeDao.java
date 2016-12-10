package ua.goit.java.jdbc.dao;

import ua.goit.java.db.Employee;
import ua.goit.java.db.Position;
import ua.goit.java.db.dao.EmployeeDao;
import ua.goit.java.db.dao.PositionDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEmployeeDao implements EmployeeDao {

    private DataSource dataSource;
    private PositionDao positionDao;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setPositionDao(JDBCPositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public int add(String surname, String name, Date birthday, String phoneNumber, int positionId, float salary) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO EMPLOYEE VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setDate(3, birthday);
            statement.setString(4, phoneNumber);
            statement.setInt(5, positionId);
            statement.setFloat(6, salary);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE ID = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE LOWER (NAME) LIKE LOWER (?)")) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Employee getById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE  ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createEmployee(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM EMPLOYEE";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setName(resultSet.getString("name"));
        employee.setBirthday(resultSet.getDate("birthday").toLocalDate());
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        Position position = positionDao.getById(resultSet.getInt("position_id"));
        employee.setPosition(position);
        employee.setSalary(resultSet.getFloat("salary"));
        return employee;
    }

}
