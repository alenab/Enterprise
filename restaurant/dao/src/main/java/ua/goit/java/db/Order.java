package ua.goit.java.db;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -5434891835987681843L;

    @JsonView(Views.Orders.class)
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "orders_date")
    private LocalDate ordersDate;

    @JsonView(Views.Orders.class)
    @Column(name = "status")
    private String status;

    @JsonView(Views.Orders.class)
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderedDish> orderedDish;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDate getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(LocalDate ordersDate) {
        this.ordersDate = ordersDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderedDish> getOrderedDish() {
        return orderedDish;
    }

    public void setOrderedDish(List<OrderedDish> orderedDish) {
        this.orderedDish = orderedDish;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", employee=" + employee +
                ", tableNumber=" + tableNumber +
                ", ordersDate=" + ordersDate +
                ", status='" + status + '\'' +
                ", orderedDish=" + orderedDish +
                '}';
    }
}
