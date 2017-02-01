package ua.goit.java.db;

public class Views {

    public interface Menus{}
    public interface DishesInMenu extends Menus {}
    public interface Orders{}
    public interface OpenOrders extends Orders{}
    public interface ClosedOrders extends Orders{}
    public interface Employees {}
}
