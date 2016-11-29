package ua.goia.java;

import ua.goit.java.OrderedDish;
import ua.goit.java.Orders;
import ua.goit.java.dao.*;

import java.sql.Date;
import java.util.List;


public class OrderCommandHandler implements CommandHandler {
    OrdersDao ordersDao = new OrdersDao();

    @Override
    public String getTableName() {
        return "order";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];
        switch (command) {

            case "add":
                if (ordersDao.add(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Date.valueOf(commands[3])) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }
            case "add_dish":
                if (ordersDao.getById(Integer.parseInt(commands[1])).getStatus().equals("open")) {
                    if (commands.length == 3) {
                        ordersDao.addDish(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), 1);
                    } else {
                        ordersDao.addDish(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]));
                    }
                    return "added successfully";
                } else {
                    System.out.println(String.format("Order %s is closed, you cannot add dish", commands[1]));
                }

            case "delete_dish":
                if (ordersDao.getById(Integer.parseInt(commands[1])).getStatus().equals("open")) {
                    ordersDao.deleteDish(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
                    return "deleted successfully";
                } else {
                    System.out.println(String.format("Order %s is closed, you cannot delete dish", commands[1]));
                }

            case "deleteDish":
                if (ordersDao.getById(Integer.parseInt(commands[1])).getStatus().equals("open")) {
                    ordersDao.delete(Integer.parseInt(commands[1]));
                    return "deleted successfully";
                } else {
                    System.out.println(String.format("Order %s is closed, you cannot delete it", commands[1]));
                }

                // needs to include check that all dishes from this order are prepared
            case "close":
                int orderId = Integer.parseInt(commands[1]);
                if (ordersDao.getById(orderId).getStatus().equals("open")) {

                    List<Dish> orderedDishes = ordersDao.getDishesByOrderId(orderId);
                    orderedDishes.removeAll(new PrepareDishDao().getByOrderId(orderId));
                    if (!orderedDishes.isEmpty()) {
                        return String.format("Next dishes were ordered, but not prepared: %s", orderedDishes);
                    }
                    ordersDao.setClose(Integer.parseInt(commands[1]));
                    return "closed successfully";
                }
                return String.format("Order %s cannot be closed (this order was closed before)", commands[1]);
            case "print_open":
                return printOrder(ordersDao.getAll(), "open");

            case "print_close":
                return printOrder(ordersDao.getAll(), "close");
        }
        return "you enter incorrect command";
    }

    private String printOrder(List<Orders> list, String status) {
        String result = "";
        System.out.println(String.format("|| %5s | %15s | %15s | %20s | %10s | %-30s\n", "id", "employee",
                "table_number", "order_date", "status", "dishes"));
        for (Orders orders : list) {
            if (orders.getStatus().equals(status)) {
                result += String.format("|| %5d | %15s | %15d |%20s | %10s |  ",
                        orders.getOrderId(), orders.getEmployee().getSurname(), orders.getTableNumber(), orders.getOrdersDate(), orders.getStatus());
                List<OrderedDish> listDish = ordersDao.getOrderedDishesByOrderId(orders.getOrderId());
                if (listDish != null) {
                    for (OrderedDish orderedDish : listDish) {
                        result += new DishDao().getById(orderedDish.getDish().getDishID()).getName() + " - " + orderedDish.getQuantity() + ", ";

                    }
                    result += "\n";
                }
            }

        }
        return result;
    }
}
