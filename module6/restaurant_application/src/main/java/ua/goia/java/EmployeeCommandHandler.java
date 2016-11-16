package ua.goia.java;

import ua.goit.java.Employee;
import ua.goit.java.dao.EmployeeDao;
import ua.goit.java.dao.PositionDao;

import java.sql.Date;
import java.util.List;

public class EmployeeCommandHandler implements CommandHandler {

    private EmployeeDao employeeDao = new EmployeeDao();

    public String getTableName() {
        return "employee";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];
        switch (command) {
            case "print": {
                List<Employee> list = employeeDao.getAll();
                return printEmployee(list);
            }
            case "find":
                List<Employee> list = employeeDao.findByName(commands[1]);
                return printEmployee(list);
            case "delete":
                if (employeeDao.delete(Integer.valueOf(commands[1])) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }
            case "add":
                if (employeeDao.add(commands[1], commands[2], Date.valueOf(commands[3]), commands[4],
                        Integer.parseInt(commands[5]), Integer.parseInt(commands[6])) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }
        }
        return "you enter incorrect command";
    }

    private String printEmployee(List<Employee> list) {
        String result = "";
        System.out.println( String.format("|| %5s | %15s | %15s | %15s | %20s | %15s | %10s\n", "id", "surname",
                "name", "birthday", "phone_number", "position", "salary"));
        for (Employee employee : list) {
            String position = new PositionDao().getById(employee.getPositionId()).getName();
            result += String.format("|| %5d | %15s | %15s | %15s | %20s | %15s | %10.2f\n",
                    employee.getId(), employee.getSurname(), employee.getName(), employee.getBirthday(),
                    employee.getPhoneNumber(), position, employee.getSalary());
        }
        return result;
    }

}
