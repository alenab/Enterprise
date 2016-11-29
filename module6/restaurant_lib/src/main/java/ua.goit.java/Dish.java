package ua.goit.java;

import java.util.List;
import java.util.Objects;

public class Dish {

    private int dishID;
    private String name;
    private Category category;
    private float price;
    private float weight;
    private List<Ingredients> ingredients;

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoryId) {
        this.category = categoryId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return getDishID() == dish.getDishID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDishID());
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishID=" + dishID +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", weight=" + weight +
                ", ingredients=" + ingredients +
                '}';
    }
}
