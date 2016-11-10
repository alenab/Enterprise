package ua.goit.java;

public class DishIngredients {

    private int dishId;
    private int ingredientsId;
    private float ingredientsQuantity;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public float getIngredientsQuantity() {
        return ingredientsQuantity;
    }

    public void setIngredientsQuantity(float ingredientsQuantity) {
        this.ingredientsQuantity = ingredientsQuantity;
    }

    @Override
    public String toString() {
        return "DishIngredients{" +
                "dishId=" + dishId +
                ", ingredientsId=" + ingredientsId +
                ", ingredientsQuantity=" + ingredientsQuantity +
                '}';
    }
}

