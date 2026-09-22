package se.marcus.pokedex.ui;
import se.marcus.pokedex.model.ElementType;

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
                System.out.println("Inmatningen får inte vara tom. Försök igen.");
                continue;
            }
            if (!input.matches("[\\p{L}\\p{M} ]+")) {
                System.out.println("Endast bokstäver och mellanslag är tillåtna.");
                continue;
            }
            if (input.length() > 80) {
                System.out.printf("Ange mellan %d och %d tecken.%n", minLength, maxLength);
                continue;
            }
            return input;
        }
    }

    public static ElementType readElementType(Scanner scanner, String prompt) {
        ElementType[] values = ElementType.values();
        System.out.println(prompt);
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%d. %s%n", i + 1, values[i].toSwedish());
        }
        int choice = readIntInRange(scanner, "Val: ", 1, values.length);
        return values[choice - 1];
    }
}
