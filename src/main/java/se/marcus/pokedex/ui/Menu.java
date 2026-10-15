package se.marcus.pokedex.ui;

import se.marcus.pokedex.exception.InvalidPokemonException;
import se.marcus.pokedex.exception.PokemonNotFoundException;
import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.ElementType;
import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;
import se.marcus.pokedex.model.DamageAttack;
import se.marcus.pokedex.battle.BattleEngine;
import se.marcus.pokedex.battle.CpuStrategy;
import se.marcus.pokedex.battle.RandomStrategy;
import se.marcus.pokedex.battle.AggressiveStrategy;
import se.marcus.pokedex.service.StatisticsService;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    public static void run(PokemonCollection pokemonCollection) {
        Scanner scanner = new Scanner(System.in);
        StatisticsService stats = new StatisticsService();

        boolean running = true;

        while (running) {
            System.out.printf("%n=== Meny ===%n");
            System.out.printf(
                    "1. Visa alla Pokémons%n" +
                    "2. Lägg till ny Pokemon%n" +
                    "3. Redigera Pokemon%n" +
                    "4. Ta bort Pokemon%n" +
                    "5. Sök Pokémon%n" +
                    "%n" +
                    "6. Starta strid%n" +
                    "7. Visa statistik%n" +
                    "%n" +
                    "8. Spara till fil%n" +
                    "9. Ladda från fil%n" +
                    "10. Återställ till seedad data%n" +
                    "%n" +
                    "11. Avsluta%n%n");

            int choice = ConsoleInput.readIntInRange(scanner, "Val: ", 1, 11);

           switch (choice) {
               case 1 -> pokemonCollection.list();
               case 2 -> addPokemon(scanner, pokemonCollection);
               case 3 -> editPokemon(scanner, pokemonCollection);
               case 4 -> deletePokemon(scanner, pokemonCollection);
               case 5 -> searchPokemon(scanner, pokemonCollection);
               case 6 -> startBattle(scanner, pokemonCollection, stats);
               case 7 -> stats.printStats();
               case 8 -> pokemonCollection.save();
               case 9 -> pokemonCollection.load();
               case 10 -> pokemonCollection.seed();
               case 11 -> {
                   pokemonCollection.save();
                   System.out.println("Avslutar...");
                   running = false;
               }
               default -> running = false;
            }
        }
    }

    private static int pickPokemonIndex(Scanner scanner, PokemonCollection collection) {
        if (collection.size() == 0) {
            System.out.println("Inga Pokémon finns.");
            return -1;
        }
        System.out.printf("%n=== Välj Pokémon ===%n");
        collection.shortList();
        return ConsoleInput.readIntInRange(scanner, "Välj nummer: ", 1, collection.size()) - 1;
    }

    public static void addPokemon(Scanner scanner, PokemonCollection collection) {
        System.out.printf("%n=== Ny Pokémon ===%n");
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
            System.out.printf("%n=== Redigera Pokémon ===%n");
            System.out.printf(
                    "1. Namn (%s)%n" +
                    "2. Typ (%s)%n" +
                    "3. Max HP (%d)%n" +
                    "4. Lägg till attack%n" +
                    "5. Ta bort attack%n" +
                    "6. Klar%n%n",
                    pokemon.getName(), pokemon.getType().toSwedish(), pokemon.getMaxHp());

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
        System.out.printf("%n=== Ny attack ===%n");
        String name = ConsoleInput.readString(scanner, "Attackens namn: ");
        int baseDmg = ConsoleInput.readIntInRange(scanner, "Baseskada: ", 0, 999);
        int accuracy = ConsoleInput.readIntInRange(scanner, "Träffsäkerhet (0-100): ", 0, 100);
        ElementType type = ConsoleInput.readElementType(scanner, "Attackens typ:");

        try {
            pokemon.addAttack(new DamageAttack(name, baseDmg, accuracy, type));
        } catch (InvalidPokemonException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchPokemon(Scanner scanner, PokemonCollection collection) {
        System.out.printf("%n=== Sök Pokémon ===%n");
        String name = ConsoleInput.readString(scanner, "Namn att söka efter: ");
        try {
            Pokemon pokemon = collection.findByName(name);
            System.out.printf("%nNamn: %s%n", pokemon.getName());
            System.out.printf("Typ: %s%n", pokemon.getType().toSwedish());
            System.out.printf("Max HP: %d%n", pokemon.getMaxHp());
            System.out.printf("Nuvarande HP: %d%n", pokemon.getCurrentHp());
            for (Attack attack : pokemon.getAttacks()) {
                System.out.printf("   - %s%n", attack);
            }
        } catch (PokemonNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePokemon(Scanner scanner, PokemonCollection collection) {
        int index = pickPokemonIndex(scanner, collection);
        if (index == -1) return;
        collection.remove(index);
        System.out.println("Borttagen.");
    }

    private static void startBattle(Scanner scanner, PokemonCollection collection, StatisticsService stats) {
        if (collection.size() < 2) {
            System.out.println("Du behöver minst 2 Pokémon för att starta en strid.");
            return;
        }

        // Player picks Pokémon
        System.out.println("\nVälj din Pokémon");
        int playerIndex = pickPokemonIndex(scanner, collection);
        if (playerIndex == -1) return;
        Pokemon player = collection.get(playerIndex);

        // CPU picks random
        Random random = new Random();
        int cpuIndex;
        do { cpuIndex = random.nextInt(collection.size());
        } while (cpuIndex == playerIndex);

        Pokemon cpu = collection.get(cpuIndex);
        System.out.println("CPU valde " + cpu.getName() + "!");

        // Pick difficulty
        System.out.println("Svårighetsgrad:");
        System.out.println("1. Lätt (slumpmässig)");
        System.out.println("2. Svår (aggressiv)");
        int diff = ConsoleInput.readIntInRange(scanner, "Val: ", 1, 2);

        CpuStrategy strategy = diff == 1 ? new RandomStrategy() : new AggressiveStrategy();

        // Run battle
        BattleEngine engine = new BattleEngine(player, cpu, strategy);
        engine.run(scanner);
        stats.addResult(engine.getResult());

        // Print log
        System.out.println("\n=== Stridslogg ===");
        engine.getLog().print();
    }

}
