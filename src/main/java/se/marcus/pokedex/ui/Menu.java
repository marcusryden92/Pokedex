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
                case 2 -> addPokemon(scanner, pokemonCollection);
                case 3 -> editPokemon(scanner, pokemonCollection);
                case 4 -> deletePokemon(scanner, pokemonCollection);
                /*case 5 -> ;
                case 6 -> ;
                case 7 -> ;*/
                default -> System.out.println("Avslutar...");
            }
        }
    }

    private static int pickPokemonIndex(Scanner scanner, PokemonCollection collection) {
        if (collection.size() == 0) {
            System.out.print("Inga Pokémon finns.");
            return -1;
        }
        collection.shortList();
        return ConsoleInput.readIntInRange(scanner, "Välj nummer: ", 1, collection.size()) - 1;
    }

    public static void addPokemon(Scanner scanner, PokemonCollection collection) {
        String name = ConsoleInput.readString(scanner, "Pokemonens namn: ");
        ElementType type = ConsoleInput.readElementType(scanner, "Typ:");
        int maxHp = ConsoleInput.readIntInRange(scanner, "Max HP: ", 1, 999);

        Pokemon pokemon = new Pokemon(name, type, maxHp);

        do {
            addAttack(scanner, pokemon);
        } while (pokemon.getAttacks().size() < 4
                && ConsoleInput.readIntInRange(scanner, "Lägg till en till attack? (1=ja, 2=nej): ", 1, 2) == 1);

        collection.add(pokemon);
        System.out.printf("%s tillagd.%n", pokemon.getName());
    }

    public static void editPokemon(Scanner scanner, PokemonCollection collection) {
        int index = pickPokemonIndex(scanner, collection);
        if (index == -1) return;
        Pokemon pokemon = collection.get(index);

        boolean editing = true;
        while (editing) {
            System.out.printf(
                    "1. Namn (%s)%n2. Typ (%s)%n3. Max HP (%d)%n" +
                            "4. Lägg till attack%n5. Ta bort attack%n6. Klar%n",
                    pokemon.getName(), pokemon.getType(), pokemon.getMaxHp());

            int choice = ConsoleInput.readIntInRange(scanner, "Val: ", 1, 6);
            switch (choice) {
                case 1 -> pokemon.setName(ConsoleInput.readString(scanner, "Nytt namn: "));
                case 2 -> pokemon.setType(ConsoleInput.readElementType(scanner, "Ny typ:"));
                case 3 -> pokemon.setMaxHp(ConsoleInput.readIntInRange(scanner, "Nytt max HP: ", 1, 999));
                case 4 -> addAttack(scanner, pokemon);
                case 5 -> {
                    int a = ConsoleInput.readIntInRange(scanner, "Attacknummer att ta bort: ", 1, pokemon.getAttacks().size());
                    try {
                        pokemon.removeAttack(a - 1);
                    } catch (InvalidPokemonException e) {
                        System.out.println(e.getMessage());
                    }
                }
                default -> editing = false;
            }
        }
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

    public static void deletePokemon(Scanner scanner, PokemonCollection collection) {
        int index = pickPokemonIndex(scanner, collection);
        if (index == -1) return;
        collection.remove(index);
        System.out.println("Borttagen.");
    }

}
