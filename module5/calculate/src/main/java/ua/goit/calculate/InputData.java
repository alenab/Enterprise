package ua.goit.calculate;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

public class InputData {

    public static String readInputString() {
        System.out.println("Usage example: + 1 2 3");
        System.out.printf("Enter command: ");
        FilterInputStream inStream = new FilterInputStream(System.in) {
            @Override
            public void close() throws IOException {
            }
        };
        try (Scanner in = new Scanner(inStream)) {
            return in.nextLine();
        }
    }

}