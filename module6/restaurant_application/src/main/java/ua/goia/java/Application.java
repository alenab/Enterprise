package ua.goia.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        List<CommandHandler> tableNames = new ArrayList<>();
        tableNames.add(new EmployeeCommandHandler());
        tableNames.add(new DishCommandHandler());
        tableNames.add(new MenuCommandHandler());

        System.out.println("Please, enter a command from the command set and press 'enter': \n\n" +
                "employee print - to show a list of employees \nemployee find 'employee_name'- to find employee by name\n" +
                "employee add 'surname, name, birthday 'yyyy-mm-dd', phoneNumber,positionId, salary' - to add an employee \n" +
                "employee delete 'id_of_employee' - to delete an employee  \n\ndish print - to show a list of dishes\n" +
                "dish find 'dish_name'- to find dish by name\ndish add 'dish_name category_id price weight' - to add dish\n" +
                "dish delete 'dish_id'- to delete dish\n... \n stop - to exit" );
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
                    if (table.getTableName().equals(tableName)) {
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
}
