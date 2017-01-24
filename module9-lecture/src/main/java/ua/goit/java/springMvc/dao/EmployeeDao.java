package ua.goit.java.springMvc.dao;
import ua.goit.java.springMvc.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void save(Employee employee);

    Employee load(Long id);

    List<Employee> findAll ();

    Employee findByName(String name);

    void removeAll ();
}
