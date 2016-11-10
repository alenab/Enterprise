package ua.goit.java;

public class MenuDish {

    private int menuId;
    private int dishId;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Override
    public String toString() {
        return "MenuDish{" +
                "menuId=" + menuId +
                ", dishId=" + dishId +
                '}';
    }
}
