package ua.goit.java.db.dao;

import ua.goit.java.db.Employee;

import java.sql.Date;
import java.util.List;

public interface EmployeeDao {

    int add(String surname, String name, Date birthday, String phoneNumber, int positionId, float salary);

    int delete(int id);

    List<Employee> findByName(String name);

    Employee getById(int id);

    List<Employee> getAll();
}
