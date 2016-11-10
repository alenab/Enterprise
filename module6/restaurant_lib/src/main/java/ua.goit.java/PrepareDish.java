package ua.goit.java;

import java.time.LocalDate;

public class PrepareDish {

    private int id;
    private int dishId;
    private int employeeID;
    private int orderId;
    private LocalDate prepareDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
                ", dishId=" + dishId +
                ", employeeID=" + employeeID +
                ", orderId=" + orderId +
                ", prepareDate=" + prepareDate +
                '}';
    }
}
