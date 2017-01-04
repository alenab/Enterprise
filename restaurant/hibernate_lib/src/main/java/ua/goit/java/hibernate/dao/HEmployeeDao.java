package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Cook;
import ua.goit.java.db.Employee;
import ua.goit.java.db.Position;
import ua.goit.java.db.Waiter;
import ua.goit.java.db.dao.EmployeeDao;
import ua.goit.java.db.dao.PositionDao;

import java.sql.Date;
import java.util.List;

public class HEmployeeDao implements EmployeeDao {

    private SessionFactory sessionFactory;
    private PositionDao positionDao;

    public HEmployeeDao(SessionFactory sessionFactory, PositionDao positionDao) {
        this.sessionFactory = sessionFactory;
        this.positionDao = positionDao;
    }

    @Transactional
    private void add(Employee employee, String surname, String name, Date birthday, String phoneNumber, float salary) {
        employee.setSurname(surname);
        employee.setName(name);
        employee.setBirthday(birthday.toLocalDate());
        employee.setPhoneNumber(phoneNumber);
        employee.setSalary(salary);
    }

    @Transactional
    @Override
    public int addEmployee(String surname, String name, Date birthday, String phoneNumber, int positionId, float salary) {
        Employee employee = new Employee();
        add(employee, surname, name, birthday, phoneNumber, salary);
        employee.setPosition(positionDao.getById(positionId));
        return (int) sessionFactory.getCurrentSession().save(employee);
    }

    @Transactional
    public int addWaiter(String surname, String name, Date birthday, String phoneNumber, float salary) {
        Waiter waiter = new Waiter();
        add(waiter, surname, name, birthday, phoneNumber, salary);
        waiter.setPosition(positionDao.findByName("waiter"));
        return (int) sessionFactory.getCurrentSession().save(waiter);
    }

    @Override
    public int addCook(String surname, String name, Date birthday, String phoneNumber, float salary) {
        Cook cook = new Cook();
        add(cook, surname, name, birthday, phoneNumber, salary);
        cook.setPosition(positionDao.findByName("cook"));
        return (int) sessionFactory.getCurrentSession().save(cook);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Employee where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public List<Employee> findByName(String name) {
        Query<Employee> query = sessionFactory.getCurrentSession().createQuery("select e from Employee e where " +
                "lower (e.name) like lower (:name)", Employee.class);
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }

    @Transactional
    @Override
    public Employee getById(int id) {
        Query<Employee> query = sessionFactory.getCurrentSession().createQuery("select e from Employee e" +
                " where e.id = :id", Employee.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Transactional
    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Employee e", Employee.class).list();
    }
}