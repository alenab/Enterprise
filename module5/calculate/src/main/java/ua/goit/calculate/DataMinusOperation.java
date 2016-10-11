package ua.goit.calculate;

import ua.goit.calculator.operations.Operation;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataMinusOperation implements Operation<Instant> {

    public String getOperationSign() {
        return "-";
    }

    public boolean validateInput(String... numbers) {
        try {
            parse(numbers[0]);
            parse(numbers[1]);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public Instant parse(String number) {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").parse(number, LocalDate::from)
                .atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public String perform(String... numbers) {
        if (!validateInput(numbers)) {
            throw new IllegalStateException("It is impossible to calculate");
        }
        Duration.between(parse(numbers[1]), parse(numbers[0])).toDays();
        return String.format("%d days", Duration.between(parse(numbers[1]), parse(numbers[0])).toDays());
    }
}
