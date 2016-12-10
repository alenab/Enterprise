package ua.goit.java;

import ua.goit.java.db.dao.EmployeeDao;
import ua.goit.java.db.Employee;

import java.sql.Date;
import java.util.List;

public class EmployeeCommandHandler implements CommandHandler {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public String getName() {
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

            case "find": {
                String name = commands[1];

                List<Employee> list = employeeDao.findByName(name);
                return printEmployee(list);
            }

            case "delete":
                Integer id = Integer.valueOf(commands[1]);

                if (employeeDao.delete(id) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }

            case "add":
                String surname = commands[1];
                String name = commands[2];
                Date birthday = Date.valueOf(commands[3]);
                String phoneNumber = commands[4];
                int positionId = Integer.parseInt(commands[5]);
                int salary = Integer.parseInt(commands[6]);

                if (employeeDao.add(surname, name, birthday, phoneNumber,
                        positionId, salary) > 0) {
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
            result += String.format("|| %5d | %15s | %15s | %15s | %20s | %15s | %10.2f\n",
                    employee.getId(), employee.getSurname(), employee.getName(), employee.getBirthday(),
                    employee.getPhoneNumber(), employee.getPosition().getName(), employee.getSalary());
        }
        return result;
    }

}
