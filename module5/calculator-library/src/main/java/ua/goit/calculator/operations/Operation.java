package ua.goit.calculator.operations;

public interface Operation<T> {

    String getOperationSign();

    boolean validateInput(String... numbers);

    T parse(String number);

    String perform(String... numbers);
}
