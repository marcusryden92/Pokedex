package se.marcus.pokedex.ui;
import java.util.Scanner;

public class ConsoleInput {

    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.printf("Ange ett tal mellan %d och %d.%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt tal. Försök igen.");
            }
        }

    }

    public static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            int minLength = 0;
            int maxLength = 80;

            if (input.isEmpty()) {
                System.out.print("Inmatningen får inte vara tom. Försök igen.");
                continue;
            }
            if (!input.matches("[\\p{L}\\p{M} ]+")) {
                System.out.print("Endast bokstäver och mellanslag är tillåtna.");
                continue;
            }
            if (input.length() > 80) {
                System.out.printf("Ange mellan %d och %d tecken.%n", minLength, maxLength);
                continue;
            }
            return input;
        }
    }
}
