package ua.goia.java;

import ua.goit.java.db.Store;
import ua.goit.java.db.dao.IngredientsDao;
import ua.goit.java.db.dao.StoreDao;

import java.util.ArrayList;
import java.util.List;

public class StoreCommandHandler implements CommandHandler {

    private StoreDao storeDao;
    private IngredientsDao ingredientsDao;

    public StoreCommandHandler(StoreDao storeDao, IngredientsDao ingredientsDao) {
        this.storeDao = storeDao;
        this.ingredientsDao = ingredientsDao;
    }

    @Override
    public String getTableName() {
        return "store";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];
        switch (command) {
            case "add":
                // transaction
                if (ingredientsDao.add(commands[1], commands[2]) > 0 && storeDao.add(ingredientsDao.findByName(commands[1]).getId(),
                        Float.parseFloat(commands[3])) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }
            case "delete":
                if (storeDao.delete(storeDao.findByIngredientId(ingredientsDao.findByName(commands[1]).getId()).getId()) > 0 &&
                        ingredientsDao.delete(ingredientsDao.findByName(commands[1]).getId()) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }
            case "correct_quantity":
                storeDao.setQuantity(ingredientsDao.findByName(commands[1]).getId(), Integer.parseInt(commands[2]));
                return "corrected successfully";

            case "find": {
                Store store = storeDao.findByIngredientId(ingredientsDao.findByName(commands[1]).getId());
                String result = "";
                System.out.println(String.format("|| %5s | %20s | %8s | %8s \n", "id", "ingredient", "measurement", "quantity"));
                result += String.format("|| %5d | %20s | %8s | %8.2f |  \n", store.getId(), store.getIngredient().getName(),
                       store.getIngredient().getMeasurement(), store.getQuantity());
                return result;
            }
            case "print": {
                return printStore(storeDao.getAll());
            }
            case "print_ends":
                int limit = 3;
                List<Store> list = storeDao.getAll();
                List<Store> result = new ArrayList<>();
                for (Store store : list) {
                    if (store.getQuantity() < limit) {
                        result.add(store);
                    }
                }
                return printStore(result);
        }
        return "you enter incorrect command";
    }

    private String printStore(List<Store> list) {
        String result = "";
        System.out.println(String.format("|| %5s | %20s | %8s | %8s \n", "id", "ingredient", "measurement", "quantity"));
        for (Store store : list) {
            result += String.format("|| %5d | %20s | %8s | %8.2f |  \n", store.getId(), store.getIngredient().getName(),
                    store.getIngredient().getMeasurement(), store.getQuantity());
        }
        return result;
    }
}