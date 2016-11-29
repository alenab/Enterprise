package ua.goit.java;

public class Store {

    private int id;
    private Ingredients ingredient;
    private float quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredients getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredients ingredient) {
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
