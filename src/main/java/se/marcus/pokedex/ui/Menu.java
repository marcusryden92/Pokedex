package se.marcus.pokedex.ui;

import se.marcus.pokedex.exception.InvalidPokemonException;
import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.ElementType;
import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;

import java.util.Scanner;

public class Menu {
    public static void run(PokemonCollection pokemonCollection) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Meny ===");
            System.out.print(  "1. Visa alla Pokémons%n" +
                    "2. Lägg till ny Pokemon%n" +
                    "3. Redigera Pokemon%n" +
                    "4. Ta bort Pokemon%n%n%n" +
                    "5. Spara till fil%n" +
                    "6. Ladda från fil%n%n" +
                    "7. Återställ till seedad data%n" +
                    "8. Avsluta%n%n");

            int choice = ConsoleInput.readIntInRange(scanner, "", 1,8);

           switch (choice) {
                case 1 -> pokemonCollection.list();
                /*case 2 -> pokemonCollection.add();
                case 3 -> ;
                case 4 -> ;
                case 5 -> ;
                case 6 -> ;
                case 7 -> ;*/
                default -> System.out.println("Avslutar...");
            }
        }
    }

    public static void addPokemon(Pokemon pokemon) {
    }

    public static void editPokemon() {

    }

    public static void addAttack(Scanner scanner, Pokemon pokemon) {
        String name = ConsoleInput.readString(scanner, "Attackens namn: ");
        int baseDmg = ConsoleInput.readIntInRange(scanner, "Baseskada: ", 0, 999);
        int accuracy = ConsoleInput.readIntInRange(scanner, "Träffsäkerhet (0-100): ", 0, 100);
        ElementType type = ConsoleInput.readElementType(scanner, "Attackens typ:");

        try {
            pokemon.addAttack(new Attack(name, baseDmg, accuracy, type));
        } catch (InvalidPokemonException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePokemon() {

    }

}
