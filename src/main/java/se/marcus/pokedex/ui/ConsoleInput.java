package se.marcus.pokedex.ui;
import java.util.Scanner;

public class ConsoleInput {

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt tal. Försök igen.");
            }
        }

    }
}
