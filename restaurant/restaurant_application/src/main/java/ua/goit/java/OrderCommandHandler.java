package ua.goit.java;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Order;
import ua.goit.java.db.OrderedDish;
import ua.goit.java.db.PrepareDish;
import ua.goit.java.db.dao.DishDao;
import ua.goit.java.db.dao.OrderDao;
import ua.goit.java.db.dao.PrepareDishDao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OrderCommandHandler implements CommandHandler {

    private OrderDao orderDao;
    private DishDao dishDao;
    private PrepareDishDao prepareDishDao;

    @Override
    public String getName() {
        return "order";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];

        switch (command) {

            case "add": {

                int employeeId = Integer.parseInt(commands[1]);
                int tableNumber = Integer.parseInt(commands[2]);
                Date orderDate = Date.valueOf(commands[3]);

                if (orderDao.add(employeeId, tableNumber, orderDate) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }
            }

            case "add_dish": {

                int orderId = Integer.parseInt(commands[1]);
                int dishId = Integer.parseInt(commands[2]);

                if (orderDao.getById(orderId).getStatus().equals("open")) {
                    if (commands.length == 3) {
                        orderDao.addDish(orderId, dishId, 1);
                    } else {
                        int amountDish = Integer.parseInt(commands[3]);
                        orderDao.addDish(orderId, dishId, amountDish);
                    }
                    return "added successfully";
                } else {
                    System.out.println(String.format("Order %d is closed, you cannot add dish", orderId));
                }
            }

            case "delete_dish": {

                int orderId = Integer.parseInt(commands[1]);
                int dishId = Integer.parseInt(commands[2]);

                if (orderDao.getById(orderId).getStatus().equals("open")) {
                    orderDao.deleteDish(orderId, dishId);
                    return "deleted successfully";
                } else {
                    System.out.println(String.format("Order %d is closed, you cannot delete dish", orderId));
                }

            }

            case "delete": {

                int orderId = Integer.parseInt(commands[1]);

                if (orderDao.getById(orderId).getStatus().equals("open")) {
                    orderDao.delete(orderId);
                    return "deleted successfully";
                } else {
                    System.out.println(String.format("Order %d is closed, you cannot delete it", orderId));
                }
            }

            // needs to include check that all dishes from this order are prepared
            case "close":
                int orderId = Integer.parseInt(commands[1]);

                if (orderDao.getById(orderId).getStatus().equals("open")) {
                    Order order = orderDao.getById(orderId);

                    List<OrderedDish> orderedDish = order.getOrderedDish();
                    List<Dish> orderedDishes = new ArrayList<>();
                    for(OrderedDish oDish : orderedDish) {
                        orderedDishes.add(oDish.getDish());
                    }

                    List<PrepareDish> prepareDish = prepareDishDao.getByOrderId(orderId);
                    List<Dish> preparedDishes = new ArrayList<>();
                    for(PrepareDish pDish : prepareDish) {
                        preparedDishes.add(pDish.getDish());
                    }

                    orderedDishes.removeAll(preparedDishes);
                    if (!orderedDishes.isEmpty()) {
                        return String.format("Next dishes were ordered, but not prepared: %s", orderedDishes);
                    }
                    orderDao.setClose(Integer.parseInt(commands[1]));
                    return "closed successfully";
                }
                return String.format("Order %d cannot be closed (this order was closed before)", orderId);


            case "print_open":

                return printOrder(orderDao.getAll(), "open");

            case "print_close":

                return printOrder(orderDao.getAll(), "close");
        }

        return "you enter incorrect command";
    }

    private String printOrder(List<Order> list, String status) {

        String result = "";
        System.out.println(String.format("|| %5s | %15s | %15s | %20s | %10s | %-30s\n", "id", "employee",
                "table_number", "order_date", "status", "dishes"));
        for (Order order : list) {

            if (order.getStatus().equals(status)) {
                result += String.format("|| %5d | %15s | %15d |%20s | %10s |  ",
                        order.getOrderId(), order.getEmployee().getSurname(), order.getTableNumber(), order.getOrdersDate(), order.getStatus());
                List<OrderedDish> listDish = orderDao.getOrderedDishesByOrderId(order.getOrderId());
                if (listDish != null) {
                    for (OrderedDish orderedDish : listDish) {
                        result += dishDao.getById(orderedDish.getDish().getDishId()).getName() + " - " + orderedDish.getQuantity() + ", ";

                    }
                    result += "\n";
                }
            }

        }
        return result;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setPrepareDishDao(PrepareDishDao prepareDishDao) {
        this.prepareDishDao = prepareDishDao;
    }
}
