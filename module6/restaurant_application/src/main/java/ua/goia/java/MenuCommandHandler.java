package ua.goia.java;

import ua.goit.java.Menu;
import ua.goit.java.dao.DishDao;
import ua.goit.java.dao.MenuDao;

import java.util.List;

public class MenuCommandHandler implements CommandHandler {

    MenuDao menuDao = new MenuDao();

    @Override
    public String getTableName() {
        return "menu";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];
        switch (command) {
            case "print": {
                List<Menu> list = menuDao.getAll();
                return printMenu(list);
            }
            case "find":
                List<Menu> list = menuDao.findByName(commands[1]);
                return printMenu(list);
            case "delete":
                if (menuDao.delete(Integer.valueOf(commands[1])) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }
            case "add":
                if (menuDao.add(commands[1]) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }
            case "add_dish":
                try {
                    int dish_id = new DishDao().findByName(commands[2]).get(0).getDishID();
                    if (menuDao.addDish(Integer.parseInt(commands[1]), dish_id) > 0) {
                        return "added successfully";
                    } else {
                        return "not added";
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You enter nonexistent dish, please add this dish before and try again");
                    throw new RuntimeException();
                }
            case "delete_dish":
                if (menuDao.deleteDish(Integer.valueOf(commands[1]), new DishDao().findByName(commands[2]).get(0).getDishID()) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }

        }
        return "you enter incorrect command";
    }

    private String printMenu(List<Menu> list) {
        String result = "";
        System.out.println(String.format("|| %5s | %15s", "id", "name"));
        for (Menu menu : list) {
            result += String.format("|| %5d | %15s | \n", menu.getId(), menu.getName());
            List<Dish> listOfDish = menu.getDishes();
            for (Dish dish : listOfDish) {
                result += String.format(("%5s %-15s \n"), " ", dish.getName());
            }
        }
        return result;
    }

}
