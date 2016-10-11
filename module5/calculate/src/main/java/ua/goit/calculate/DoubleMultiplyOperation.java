package ua.goit.calculate;

import ua.goit.calculator.operations.Operation;

public class DoubleMultiplyOperation implements Operation<Double> {

    public String getOperationSign() {
        return "*";
    }

    public boolean validateInput(String... numbers) {
        if (numbers.length < 2) {
            throw new IllegalStateException("There should be at least 2 numbers!");
        }
        try {
            parse(numbers[0]);
            parse(numbers[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Double parse(String number) {
        return Double.parseDouble(number);
    }

    public String perform(String... numbers) {
        if (!validateInput(numbers)) {
            throw new IllegalStateException("It is impossible to calculate");
        }
        double result = parse(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            result *= parse(numbers[i]);
        }
        return String.valueOf(result);
    }

}
