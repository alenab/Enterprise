package ua.goit.calculator;

import ua.goit.calculator.operations.*;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private List<Operation> operations = new ArrayList<>();

    public Calculator() {
        init();
    }

    public void init() {
        operations.add(new IntegerPlusOperation());
        operations.add(new IntegerMinusOperation());
        operations.add(new LongPlusOperation());
        operations.add(new LongMinusOperation());
        operations.add(new FloatPlusOperation());
        operations.add(new FloatMinusOperation());
        operations.add(new DoublePlusOperation());
        operations.add(new DoubleMinusOperation());
        operations.add(new DoubleSquareRootOperation());
    }

    public String calculate(String inputString) {
        if (inputString.isEmpty()) {
            throw new IllegalStateException("It is an empty string");
        } else {
            String[] splited = inputString.split("\\s+");
            String operationSign = splited[0];
            String[] numbers = new String[splited.length - 1];
            System.arraycopy(splited, 1, numbers, 0, splited.length - 1);
            for (Operation operation : operations) {
                if (operation.getOperationSign().equals(operationSign) && operation.validateInput(numbers)) {
                    return operation.perform(numbers);
                }
            }
        }
        throw new IllegalStateException("You enter incorrect operationSign or it is impossible to calculate entered data type");
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
