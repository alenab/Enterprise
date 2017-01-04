package ua.goit.java.db;

import ua.goit.java.db.dao.PositionDao;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Waiter extends Employee {


    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Order> order;

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "Waiter{" +
                "order=" + order +
                '}';
    }
}


