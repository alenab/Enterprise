package ua.goit.calculator.operations;

public class LongMinusOperation implements Operation<Long> {

    @Override
    public String getOperationSign() {
        return "-";
    }

    @Override
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

    public Long parse(String number) {
        return Long.parseLong(number);
    }

    @Override
    public String perform(String... numbers) {
        if (!validateInput(numbers[0], numbers[1])) {
            throw new IllegalStateException("It is impossible to calculate");
        }

        long result = parse(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            result -= parse(numbers[i]);
        }
        return String.valueOf(result);
    }
}
