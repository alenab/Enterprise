package ua.goit.java.db;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="prepare_dish")
public class PrepareDish {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name="dish_id")
    private Dish dish;

    @OneToMany
    @JoinColumn(name="employee_id")
    private Employee employee;

    @OneToMany
    @JoinColumn(name="order_id")
    private Orders order;

    @Column(name="prepare_date")
    private LocalDate prepareDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public LocalDate getPrepareDate() {
        return prepareDate;
    }

    public void setPrepareDate(LocalDate prepareDate) {
        this.prepareDate = prepareDate;
    }

    @Override
    public String toString() {
        return "PrepareDish{" +
                "id=" + id +
                ", dish=" + dish +
                ", employee=" + employee +
                ", order=" + order +
                ", prepareDate=" + prepareDate +
                '}';
    }
}
