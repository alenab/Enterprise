package ua.goit.java;

public class OrdersDish {

    private int orderId;
    private int dishId;
    private int amountDish;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getAmountDish() {
        return amountDish;
    }

    public void setAmountDish(int amountDish) {
        this.amountDish = amountDish;
    }

    @Override
    public String toString() {
        return "OrdersDish{" +
                "orderId=" + orderId +
                ", dishId=" + dishId +
                ", amountDish=" + amountDish +
                '}';
    }
}
