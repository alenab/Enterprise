package ua.goit.java;

public interface CommandHandler {

    String getName();

    String handler(String... commands);

}
