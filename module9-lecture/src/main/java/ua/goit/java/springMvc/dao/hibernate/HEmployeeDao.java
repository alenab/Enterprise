package ua.goit.java.springMvc.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.springMvc.dao.EmployeeDao;
import ua.goit.java.springMvc.model.Employee;

import java.util.List;


public class HEmployeeDao implements EmployeeDao {

    private SessionFactory sessionFactory;


    @Transactional
    @Override
    public void save(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public Employee load(Long id) {
        Employee result = sessionFactory.getCurrentSession().load(Employee.class, id);
        if (result == null) {
            throw new RuntimeException("Cannot find Employee with id:" + id);
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    @Override
    public Employee findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.name like :name");
        query.setParameter("name", name);
        return (Employee) query.uniqueResult();
    }

    @Override
    public void removeAll() {
        sessionFactory.getCurrentSession().createQuery("delete from Employee").executeUpdate();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
