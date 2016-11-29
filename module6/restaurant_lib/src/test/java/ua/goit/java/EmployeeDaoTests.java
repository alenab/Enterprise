package ua.goit.java;

import org.junit.Test;
import ua.goit.java.dao.EmployeeDao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class EmployeeDaoTests {

    private EmployeeDao employeeDao = new EmployeeDao();

//    @Test
//    public void checkGetAll() {
//        List<Employee> list = employeeDao.getAll();
//        assertThat(list, notNullValue());
//        assertThat(list, hasSize(greaterThan(0)));
//        for (Employee item : list) {
//            assertThat(item.getId(), greaterThan(0));
//            assertThat(item.getName(), not(isEmptyOrNullString()));
//            assertThat(item.getSurname(), not(isEmptyOrNullString()));
//            assertThat(item.getBirthday(), notNullValue());
//            assertThat(item.getPosition(), greaterThan(0));
//        }
//    }
//
//    @Test
//    public void checkAdd() {
//        final String surname = "surname";
//        final String name = "name";
//        final Date birthday = Date.valueOf(LocalDate.now());
//        final int positionId = 1;
//        final float salary = 100;
//        int result = employeeDao.addDish(surname, name, birthday, null, positionId, salary);
//        assertThat(result, equalTo(1));
//
//        List<Employee> list = employeeDao.getAll();
//        assertThat(list, notNullValue());
//        assertThat(list, hasSize(greaterThan(0)));
//
//        for (Employee item : list) {
//            if (item.getName().equals(name) && item.getSurname().equals(surname)) {
//                assertThat(item.getId(), greaterThan(0));
//                assertThat(item.getName(), equalTo(name));
//                assertThat(item.getSurname(), equalTo(surname));
//                assertThat(item.getBirthday(), equalTo(birthday.toLocalDate()));
//                assertThat(item.getPhoneNumber(), nullValue());
//                assertThat(item.getPosition(), equalTo(positionId));
//                assertThat(item.getSalary(), equalTo(salary));
//                return;
//            }
//        }
//        fail("New record was not found");
//    }
//
//    @Test
//    public void checkFindByName() {
//        List<Employee> list = employeeDao.getAll();
//        Employee employee = list.get(1);
//        String name = employee.getName();
//        List<Employee> foundEmployees = employeeDao.findByName(name);
//        for (Employee item : foundEmployees) {
//            assertThat(item.getName(), equalTo(employee.getName()));
//        }
//    }
//
//    @Test
//    public void checkGetById() {
//        List<Employee> list = employeeDao.getAll();
//        Employee employee = list.get(0);
//        int id = employee.getId();
//        Employee foundEmployee = employeeDao.getById(id);
//        assertThat(foundEmployee.getId(), equalTo(employee.getId()));
//    }
//
//
//    @Test
//    public void checkDelete() {
//        List<Employee> list = employeeDao.getAll();
//        Employee employee = list.get(7);
//        employeeDao.deleteDish(employee.getId());
//        list = employeeDao.getAll();
//        for (Employee item : list) {
//            assertNotEquals(item.getId(), employee.getId());
//        }
//
//    }
}
