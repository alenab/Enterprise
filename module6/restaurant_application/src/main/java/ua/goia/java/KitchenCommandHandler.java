package ua.goia.java;

import ua.goit.java.PrepareDish;
import ua.goit.java.dao.DishDao;
import ua.goit.java.dao.EmployeeDao;
import ua.goit.java.dao.PositionDao;
import ua.goit.java.dao.PrepareDishDao;

import java.sql.Date;
import java.util.List;

public class KitchenCommandHandler implements CommandHandler {
    PrepareDishDao prepareDishDao = new PrepareDishDao();

    @Override
    public String getTableName() {
        return "kitchen";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];
        switch (command) {
            case "add":
                if (prepareDishDao.add(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]),
                        Date.valueOf(commands[4])) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }

            case "print":
                List<PrepareDish> list = prepareDishDao.getAll();
                String result = "";
                System.out.println( String.format("|| %5s | %15s | %15s | %5s | %20s\n", "id", "dish",
                        "employee", "order_id", "date"));
                for (PrepareDish prepareDish : list) {
                    String dish = new DishDao().getById(prepareDish.getDishId()).getName();
                    String employee = new EmployeeDao().getById(prepareDish.getEmployeeID()).getName();
                    result += String.format("|| %5d | %15s | %15s | %5d | %20s\n", prepareDish.getId(),
                            dish, employee, prepareDish.getOrderId(), prepareDish.getPrepareDate());
                }
                return result;
        }
        return "ok";
    }
}
