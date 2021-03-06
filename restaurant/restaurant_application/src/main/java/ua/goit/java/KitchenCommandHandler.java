package ua.goit.java;

import ua.goit.java.db.Dish;
import ua.goit.java.db.PrepareDish;
import ua.goit.java.db.dao.PrepareDishDao;

import java.sql.Date;
import java.util.List;

public class KitchenCommandHandler implements CommandHandler {

    private PrepareDishDao prepareDishDao;

    public void setPrepareDishDao(PrepareDishDao prepareDishDao) {
        this.prepareDishDao = prepareDishDao;
    }

    @Override
    public String getName() {
        return "kitchen";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];

        switch (command) {

            case "need_prepare": {
                List<Dish> list = prepareDishDao.needToPrepare();
                String result = "";
                System.out.println(String.format("|| %5s | %15s |\n", "id", "dish"));
                for (Dish dish : list) {
                    result += String.format("|| %5d | %15s |\n", dish.getDishId(),
                            dish.getName());
                }
                return result;
            }

            case "prepare":
                int dishId = Integer.parseInt(commands[1]);
                int employeeId = Integer.parseInt(commands[2]);
                int orderId = Integer.parseInt(commands[3]);
                Date prepareDate = Date.valueOf(commands[4]);

                if (prepareDishDao.add(dishId, employeeId, orderId,
                        prepareDate) > 0) {
                    return "added successfully";
                } else {
                    if (prepareDishDao.add(dishId, employeeId, orderId,
                            prepareDate) == 0) {
                        return "not added, there are no enough ingredients on the store";
                    } else {
                        return "not added";
                    }
                }

            case "print":
                List<PrepareDish> list = prepareDishDao.getAll();
                String result = "";
                System.out.println(String.format("|| %5s | %15s | %15s | %5s | %20s\n", "id", "dish",
                        "employee", "order_id", "date"));
                for (PrepareDish prepareDish : list) {
                    result += String.format("|| %5d | %15s | %15s | %5d | %20s\n", prepareDish.getId(),
                            prepareDish.getDish().getName(), prepareDish.getEmployee().getSurname(), prepareDish.getOrder().getOrderId(),
                            prepareDish.getPrepareDate());
                }
                return result;
        }
        return "ok";
    }
}
