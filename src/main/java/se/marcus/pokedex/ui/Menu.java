
package se.marcus.pokedex.ui;

import se.marcus.pokedex.battle.GameLoop;
import se.marcus.pokedex.model.PokemonCollection;
import se.marcus.pokedex.service.PokemonManager;
import se.marcus.pokedex.service.StatisticsService;

import java.util.Scanner;

public class Menu {

    private final PokemonManager manager;
    private final Scanner scanner;
    private final ConsolePrinter printer;
    private final StatisticsService stats;

    public Menu(PokemonManager manager, Scanner scanner, ConsolePrinter printer, StatisticsService stats) {
        this.manager = manager;
        this.scanner = scanner;
        this.printer = printer;
        this.stats = stats;
    }

    public void run(PokemonCollection pokemonCollection) {

        boolean running = true;

        while (running) {
            printer.printMainMenu();

            int choice = ConsoleInput.readIntInRange(scanner, "Val: ", 1, 11);

            switch (choice) {
                case 1 -> pokemonCollection.list();
                case 2 -> manager.addPokemon();
                case 3 -> manager.editPokemon();
                case 4 -> manager.deletePokemon();
                case 5 -> manager.searchPokemon();
                case 6 -> GameLoop.run(pokemonCollection, manager, scanner, printer, stats);
                case 7 -> stats.printStats();
                case 8 -> pokemonCollection.save();
                case 9 -> pokemonCollection.load();
                case 10 -> pokemonCollection.seed();
                case 11 -> {
                    pokemonCollection.save();
                    printer.printExitMessage();
                    running = false;
                }
            }
        }
    }
}
