package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Employee;
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
    @Override
    public int add(String surname, String name, Date birthday, String phoneNumber, int positionId, float salary) {
        Employee employee = new Employee();
        employee.setSurname(surname);
        employee.setName(name);
        employee.setBirthday(birthday.toLocalDate());
        employee.setPhoneNumber(phoneNumber);
        employee.setPosition(positionDao.getById(positionId));
        employee.setSalary(salary);
        return (int) sessionFactory.getCurrentSession().save(employee);
    }

    @Transactional
    @Override
    public int delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Employee where id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
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