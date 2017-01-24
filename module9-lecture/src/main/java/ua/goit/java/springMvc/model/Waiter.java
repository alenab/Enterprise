package ua.goit.java.springMvc.model;

import org.hibernate.FlushMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Waiter extends Employee {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @Fetch(FetchMode.JOIN)
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiter{ \n");
        sb.append("  id = ").append(getId());
        sb.append("  name = ").append(getName());
        sb.append("  sutname = ").append(getSurname());
        sb.append("  orders  = {");
        orders.forEach(order -> sb.append("  ").append(order).append("  "));
        sb.append("  } \n");
        sb.append("}/n");
        return sb.toString();

    }
}