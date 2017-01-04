package ua.goit.java.db.dao;

import ua.goit.java.db.Employee;

import java.sql.Date;
import java.util.List;

public interface EmployeeDao {

    int addEmployee(String surname, String name, Date birthday, String phoneNumber, int positionId, float salary);

    int addWaiter (String surname, String name, Date birthday, String phoneNumber, float salary);

    int addCook (String surname, String name, Date birthday, String phoneNumber, float salary);

    void delete(int id);

    List<Employee> findByName(String name);

    Employee getById(int id);

    List<Employee> getAll();
}
