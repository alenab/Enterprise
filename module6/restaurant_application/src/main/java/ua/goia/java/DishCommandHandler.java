package ua.goia.java;

import ua.goit.java.db.Dish;
import ua.goit.java.db.dao.DishDao;

import java.util.List;

public class DishCommandHandler implements CommandHandler {

    private DishDao dishDao;

    public DishCommandHandler(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public String getTableName() {
        return "dish";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];
        switch (command) {
            case "print": {
                List<Dish> list = dishDao.getAll();
                return printDishes(list);
            }

            case "find":
                List<Dish> list = dishDao.findByName(commands[1]);
                return printDishes(list);

            case "delete":
                if (dishDao.delete(Integer.valueOf(commands[1])) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }
            case "add":
                if (dishDao.add(commands[2], Integer.parseInt(commands[3]), Float.parseFloat(commands[4]),
                        Float.parseFloat(commands[5])) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }
        }
        return "you enter incorrect command";
    }

    private String printDishes(List<Dish> list) {
        String result = "";
        System.out.println(String.format("|| %5s | %15s | %15s | %10s | %10s \n", "id", "name", "category",
                "price", "weight"));
        for (Dish dish : list) {
            result += String.format("|| %5d | %15s | %15s | %10.2f | %10.2f \n",
                    dish.getDishID(), dish.getName(), dish.getCategory().getName(), dish.getPrice(), dish.getWeight());
        }
        return result;
    }
}
