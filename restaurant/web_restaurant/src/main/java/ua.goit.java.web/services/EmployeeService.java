package ua.goit.java.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Employee;
import ua.goit.java.db.Waiter;
import ua.goit.java.db.dao.EmployeeDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDao employeeDao;

    @Transactional
    public List<Waiter> getWaiters() {
        List<Waiter> result = new ArrayList<>();
        List<Employee> list = employeeDao.getAll();
        for( Employee employee : list) {
            if(employee.getPosition().getName().equals("waiter")) {
                result.add((Waiter) employee);
            }
        }
        return result;
    }
    @Transactional
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Transactional
    public Employee getById(int id) {
        return employeeDao.getById(id);
    }

    @Transactional
    public List<Employee> findByName(String name) {
        return employeeDao.findByName(name);
    }

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
