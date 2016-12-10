package ua.goit.java;

import ua.goit.java.db.Ingredient;
import ua.goit.java.db.Store;
import ua.goit.java.db.dao.IngredientsDao;
import ua.goit.java.db.dao.StoreDao;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class StoreCommandHandler implements CommandHandler {

    private StoreDao storeDao;
    private IngredientsDao ingredientsDao;

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public void setIngredientsDao(IngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    @Override
    public String getName() {
        return "store";
    }

    @Override
    public String handler(String... commands) {
        String command = commands[0];
        switch (command) {

            case "add": {
                String name = commands[1];
                String measurement = commands[2];
                float quantity = Float.parseFloat(commands[3]);

                int idAddedDish = ingredientsDao.add(name, measurement);

                if (idAddedDish > 0 && storeDao.add(idAddedDish,
                        quantity) > 0) {
                    return "added successfully";
                } else {
                    return "not added";
                }
            }

            case "delete": {
                int id = Integer.parseInt(commands[1]);

                if (storeDao.delete(id) > 0) {
                    return "deleted successfully";
                } else {
                    return "not deleted";
                }
            }

            case "correct_quantity": {
                int id = Integer.parseInt(commands[1]);
                int quantity = Integer.parseInt(commands[2]);

                storeDao.setQuantity(id, quantity);
                return "corrected successfully";
            }

            case "find": {
                String name = commands[1];

                List<Ingredient> ingredient = ingredientsDao.findByName(name);
                List<Store> store = new ArrayList<>();
                for (Ingredient ingred : ingredient) {
                    store.add(storeDao.findByIngredientId(ingred.getId()));
                }
                return printStore(store);
            }

            case "print": {
                return printStore(storeDao.getAll());
            }

            case "print_ends":
                int limit = Integer.parseInt(commands[1]);

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