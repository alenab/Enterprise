package ua.goit.java;

import java.time.LocalDate;

public class Orders {

    private int orderId;
    private int employeeID;
    private int tableNumber;
    private LocalDate ordersDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", employeeID=" + employeeID +
                ", tableNumber=" + tableNumber +
                ", ordersDate=" + ordersDate +
                '}';
    }
}
