package ua.goit.java;

import ua.goit.java.db.Dish;
import ua.goit.java.db.dao.DishDao;

import java.util.List;

public class DishCommandHandler implements CommandHandler {

    private DishDao dishDao;

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }


    @Override
    public String getName() {
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

            case "find": {
                String name = commands[1];
                List<Dish> list = dishDao.findByName(name);
                return printDishes(list);
            }
            case "delete":
                Integer id = Integer.valueOf(commands[1]);
                if (dishDao.delete(id) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }
            case "add":
                String name = commands[1];
                int categoryId = Integer.parseInt(commands[2]);
                float price = Float.parseFloat(commands[3]);
                float weight = Float.parseFloat(commands[4]);

                if (dishDao.add(name, categoryId, price,
                        weight) > 0) {
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
                    dish.getDishId(), dish.getName(), dish.getCategory().getName(), dish.getPrice(), dish.getWeight());
        }
        return result;
    }
}
