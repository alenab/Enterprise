package ua.goit.java.db;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="orders_dish")
public class OrderedDish implements Serializable{

    private static final long serialVersionUID = 8615816117699429260L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @JsonView(Views.Orders.class)
    @OneToOne
    @JoinColumn(name="dish_id")
    private Dish dish;

    @JsonView(Views.Orders.class)
    @Column(name="amount_dish")
    private int quantity;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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
                "Id=" + Id +
                ", order=" + order +
                ", dish=" + dish +
                ", quantity=" + quantity +
                '}';
    }
}
