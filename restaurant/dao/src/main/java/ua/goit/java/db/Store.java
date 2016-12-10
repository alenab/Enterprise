package ua.goit.java.db;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="store")
public class Store implements Serializable{

    private static final long serialVersionUID = 6113497073094739885L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name="quantity")
    private float quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                '}';
    }
}
