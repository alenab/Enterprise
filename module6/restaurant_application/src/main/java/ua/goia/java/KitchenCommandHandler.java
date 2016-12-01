package ua.goia.java;

import ua.goit.java.db.PrepareDish;
import ua.goit.java.db.dao.PrepareDishDao;

import java.sql.Date;
import java.util.List;

public class KitchenCommandHandler implements CommandHandler {

    private PrepareDishDao prepareDishDao;

    public KitchenCommandHandler(PrepareDishDao prepareDishDao) {
        this.prepareDishDao = prepareDishDao;
    }

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
                    result += String.format("|| %5d | %15s | %15s | %5d | %20s\n", prepareDish.getId(),
                            prepareDish.getDish().getName(), prepareDish.getEmployee().getSurname(), prepareDish.getOrder().getOrderId(),
                            prepareDish.getPrepareDate());
                }
                return result;
        }
        return "ok";
    }
}
