package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Employee;
import ua.goit.java.db.Position;
import ua.goit.java.db.dao.PositionDao;

import java.util.List;

public class HPositionDao implements PositionDao {

    private SessionFactory sessionFactory;

    public HPositionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HPositionDao() {
    }

    @Transactional
    @Override
    public int add(String name) {
        Position position = new Position();
        position.setName(name);
        return (int) sessionFactory.getCurrentSession().save(position);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Position where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public Position getById(int id) {
        Query<Position> query = sessionFactory.getCurrentSession().createQuery("select p from Position p" +
                " where p.id = :id", Position.class);
        query.setParameter("id", id);
        return query.uniqueResult();

    }

    @Transactional
    @Override
    public List<Position> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Position p", Position.class).list();

    }

    @Override
    public Position findByName(String name) {
        Query<Position> query = sessionFactory.getCurrentSession().createQuery("select p from Position p where " +
                "p.name = :name", Position.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

}

