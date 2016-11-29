package ua.goit.java;

public class OrderedDish {
    private Dish dish;
    private int quantity;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderedDish{" +
                "dish=" + dish +
                ", quantity=" + quantity +
                '}';
    }
}
