package ua.goit.java.dao;

import ua.goit.java.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "19fktyrf";

    public int add(String surname, String name, Date birthday, String phoneNumber, int positionId, float salary) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
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

    public int delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE ID = ?")) {
            statement.setInt(1, Integer.valueOf(id));
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Employee> findByName(String name) {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE  NAME = ?")) {
            statement.setString(1, name);
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

    public Employee getById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE  ID = ?")) {
            statement.setInt(1, Integer.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createEmployee(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
        employee.setPositionId(resultSet.getInt("position_id"));
        employee.setSalary(resultSet.getFloat("salary"));
        return employee;
    }

}
