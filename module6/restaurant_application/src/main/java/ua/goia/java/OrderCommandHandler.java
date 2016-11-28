package ua.goia.java;

import ua.goit.java.Orders;
import ua.goit.java.OrdersDish;
import ua.goit.java.dao.*;

import java.sql.Date;
import java.util.List;

public class OrderCommandHandler implements CommandHandler {
    OrdersDao ordersDao = new OrdersDao();
    OrdersDishDao ordersDishDao = new OrdersDishDao();

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
                        ordersDishDao.add(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), 1);
                    } else {
                        ordersDishDao.add(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]));
                    }
                } else {
                    System.out.println(String.format("Order %s is closed, you cannot add dish", commands[1]));
                }
                break;
            case "delete_dish":
                if (ordersDao.getById(Integer.parseInt(commands[1])).getStatus().equals("open")) {
                    ordersDishDao.delete(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
                } else {
                    System.out.println(String.format("Order %s is closed, you cannot delete dish", commands[1]));
                }
                break;
            case "delete":
                if (ordersDao.getById(Integer.parseInt(commands[1])).getStatus().equals("open")) {
                    ordersDao.delete(Integer.parseInt(commands[1]));
                } else {
                    System.out.println(String.format("Order %s is closed, you cannot delete it", commands[1]));
                }
                break;
            case "close":
                if (ordersDao.getById(Integer.parseInt(commands[1])).getStatus().equals("open")) {
                    ordersDao.setClose(Integer.parseInt(commands[1]));
                } else {
                    System.out.println(String.format("Order %s already closed before", commands[1]));
                }
                break;
            case "print_open":
                return printOrder(ordersDao.getAll());

            case "print_close":
                List<Orders> list = ordersDao.getAll();
                String result = "";
                System.out.println(String.format("|| %5s | %15s | %15s | %20s | %10s | %-30s\n", "id", "employee",
                        "table_number", "order_date", "status", "dishes"));
                for (Orders orders : list) {
                    if (orders.getStatus().equals("close")) {
                        String surnameOfEmployee = new EmployeeDao().getById(orders.getEmployeeID()).getSurname();
                        result += String.format("|| %5d | %15s | %15d |%20s | %10s |  ",
                                orders.getOrderId(), surnameOfEmployee, orders.getTableNumber(), orders.getOrdersDate(), orders.getStatus());
                        List<OrdersDish> listDish = ordersDishDao.getByOrderId(orders.getOrderId());
                        if (listDish != null) {
                            for (OrdersDish orderDish : listDish) {
                                result += new DishDao().getById(orderDish.getDishId()).getName() + ", ";

                            }
                            result += "\n";
                        }

                    }

                }
                return result;
        }
        return "ok";
    }

    private String printOrder(List<Orders> list) {
        String result = "";
        System.out.println(String.format("|| %5s | %15s | %15s | %20s | %10s | %-30s\n", "id", "employee",
                "table_number", "order_date", "status", "dishes"));
        for (Orders orders : list) {
            if (orders.getStatus().equals("open")) {
                String surnameOfEmployee = new EmployeeDao().getById(orders.getEmployeeID()).getSurname();
                result += String.format("|| %5d | %15s | %15d |%20s | %10s |  ",
                        orders.getOrderId(), surnameOfEmployee, orders.getTableNumber(), orders.getOrdersDate(), orders.getStatus());
                List<OrdersDish> listDish = ordersDishDao.getByOrderId(orders.getOrderId());
                if (listDish != null) {
                    for (OrdersDish orderDish : listDish) {
                        result += new DishDao().getById(orderDish.getDishId()).getName() + ", ";

                    }
                    result += "\n";
                }
            }

        }
        return result;
    }
}
