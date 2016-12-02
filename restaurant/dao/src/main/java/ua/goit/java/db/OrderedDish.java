package ua.goit.java.db;

import javax.persistence.*;

@Entity
@Table(name="orders_dish")
public class OrderedDish {

    @OneToMany
    @JoinColumn(name="dish_id")
    private Dish dish;

    @Column(name="amount_dish")
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
