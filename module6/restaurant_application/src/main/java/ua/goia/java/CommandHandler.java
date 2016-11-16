package ua.goia.java;

public interface CommandHandler {

    String getTableName();

    String handler(String... commands);

}
