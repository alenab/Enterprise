package ua.goit.calculator.operations;

public class DoubleSquareRootOperation implements Operation<Double> {

    @Override
    public String getOperationSign() {
        return "sqrt";
    }

    @Override
    public boolean validateInput(String... numbers) {
        if (numbers.length != 1) {
            throw new IllegalStateException("There should be one numbers!");
        }
        try {
            parse(numbers[0]);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Double parse(String number) {
        return Double.parseDouble(number);
    }

    @Override
    public String perform(String... numbers) {
        if (!validateInput(numbers)) {
            throw new IllegalStateException("It is impossible to calculate");
        }
        return String.valueOf(Math.sqrt(parse(numbers[0])));
    }
}
