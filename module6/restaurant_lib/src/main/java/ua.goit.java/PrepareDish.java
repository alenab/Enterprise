package ua.goit.java;

import java.time.LocalDate;

public class PrepareDish {

    private int id;
    private Dish dish;
    private Employee employee;
    private Orders order;
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
