package ua.goit.java;

import ua.goit.java.db.Dish;
import ua.goit.java.db.Menu;
import ua.goit.java.db.dao.DishDao;
import ua.goit.java.db.dao.MenuDao;

import java.util.List;

public class MenuCommandHandler implements CommandHandler {

    private MenuDao menuDao;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }


    @Override
    public String getName() {
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

            case "find": {
                String name = commands[1];

                List<Menu> list = menuDao.findByName(name);
                return printMenu(list);
            }

            case "delete":
                Integer id = Integer.valueOf(commands[1]);

                if (menuDao.delete(id) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }

            case "add":
                String name = commands[1];

                if (menuDao.add(name) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }

            case "add_dish": {
                int menuId = Integer.parseInt(commands[1]);
                int dishId = Integer.parseInt(commands[2]);

                try {
                    if (menuDao.addDish(menuId, dishId) > 0) {
                        return "added successfully";
                    } else {
                        return "not added";
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You enter nonexistent dish, please add this dish before and try again");
                    throw new RuntimeException();
                }
            }

            case "delete_dish":
                Integer menuId = Integer.valueOf(commands[1]);
                Integer dishId = Integer.valueOf(commands[2]);

                if (menuDao.deleteDish(menuId, dishId) > 0) {
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
