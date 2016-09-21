public interface Validator <X> {

    // Валидирует переданое значение
    boolean isValid(X result);


}