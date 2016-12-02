package ua.goit.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.java.jdbc.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private EmployeeCommandHandler employeeCommandHandler;
    private DishCommandHandler dishCommandHandler;
    private MenuCommandHandler menuCommandHandler;
    private OrderCommandHandler orderCommandHandler;
    private KitchenCommandHandler kitchenCommandHandler;
    private StoreCommandHandler storeCommandHandler;


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Application application = applicationContext.getBean(Application.class);
        application.start();

    }
    public void start() {
        List<CommandHandler> tableNames = new ArrayList<>();
        tableNames.add(employeeCommandHandler);
        tableNames.add(dishCommandHandler);
        tableNames.add(menuCommandHandler);
        tableNames.add(orderCommandHandler);
        tableNames.add(kitchenCommandHandler);
        tableNames.add(storeCommandHandler);

        System.out.println("Please, enter a command from the command set and press 'enter': \n\n" +
                "employee print - to show a list of employees \nemployee find 'employee_name'- to find employee by name\n" +
                "employee add 'surname, name, birthday 'yyyy-mm-dd', phoneNumber,positionId, salary' - to add an employee \n" +
                "employee delete 'id_of_employee' - to delete an employee  \n\ndish print - to show a list of dishes\n" +
                "dish find 'dish_name'- to find dish by name\ndish add 'dish_name category_id price weight' - to add dish\n" +
                "dish delete 'dish_id'- to deleteDish dish\n... \n stop - to exit");
        String command;
        String tableName;
        String[] commandToExecute;
        do {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.isEmpty()) {
                throw new IllegalStateException("It is an empty string");
            } else {
                String[] splitedCommand = command.split("\\s+");
                tableName = splitedCommand[0];
                commandToExecute = new String[splitedCommand.length - 1];
                System.arraycopy(splitedCommand, 1, commandToExecute, 0, commandToExecute.length);
                boolean isCommandFound = false;
                for (CommandHandler table : tableNames) {
                    if (table.getName().equals(tableName)) {
                        System.out.println(table.handler(commandToExecute));
                        isCommandFound = true;
                    }
                }
                if (!isCommandFound) {
                    System.out.println("you enter incorrect command");
                }
            }
        }
        while (!command.equals("stop"));
    }

    public void setEmployeeCommandHandler(EmployeeCommandHandler employeeCommandHandler) {
        this.employeeCommandHandler = employeeCommandHandler;
    }

    public void setDishCommandHandler(DishCommandHandler dishCommandHandler) {
        this.dishCommandHandler = dishCommandHandler;
    }

    public void setMenuCommandHandler(MenuCommandHandler menuCommandHandler) {
        this.menuCommandHandler = menuCommandHandler;
    }

    public void setOrderCommandHandler(OrderCommandHandler orderCommandHandler) {
        this.orderCommandHandler = orderCommandHandler;
    }

    public void setKitchenCommandHandler(KitchenCommandHandler kitchenCommandHandler) {
        this.kitchenCommandHandler = kitchenCommandHandler;
    }

    public void setStoreCommandHandler(StoreCommandHandler storeCommandHandler) {
        this.storeCommandHandler = storeCommandHandler;
    }
}
