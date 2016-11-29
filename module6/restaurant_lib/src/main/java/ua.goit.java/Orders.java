package ua.goit.java;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Orders {

    private int orderId;
    private Employee employee;
    private int tableNumber;
    private LocalDate ordersDate;
    private String status;
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
        return "Orders{" +
                "orderId=" + orderId +
                ", employee=" + employee +
                ", tableNumber=" + tableNumber +
                ", ordersDate=" + ordersDate +
                ", status='" + status + '\'' +
                ", orderedDish=" + orderedDish +
                '}';
    }
}
