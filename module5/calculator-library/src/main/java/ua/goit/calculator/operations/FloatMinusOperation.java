package ua.goit.calculator.operations;

public class FloatMinusOperation implements Operation<Float> {

    @Override
    public String getOperationSign() {
        return "-";
    }

    @Override
    public boolean validateInput(String... numbers) {
        if (numbers.length < 2) {
            throw new IllegalStateException("There should be at least 2 numbers!");
        }
        Float number1;
        Float number2;
        try {
            number1 = parse(numbers[0]);
            number2 = parse(numbers[1]);
            if (number1.compareTo(Float.MAX_VALUE) != -1 || number1.compareTo(Float.MIN_VALUE) != 1 ||
                    number2.compareTo(Float.MAX_VALUE) != -1 || number2.compareTo(Float.MIN_VALUE) != 1) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Float parse(String number) {
        return Float.parseFloat(number);
    }

    @Override
    public String perform(String... numbers) {
        if (!validateInput(numbers)) {
            throw new IllegalStateException("It is impossible to calculate");
        }
        float result = parse(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            result -= parse(numbers[i]);
        }
        return String.valueOf(result);
    }

}
