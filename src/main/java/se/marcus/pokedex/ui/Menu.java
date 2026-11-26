
package se.marcus.pokedex.ui;

import se.marcus.pokedex.battle.GameLoop;
import se.marcus.pokedex.model.PokemonCollection;
import se.marcus.pokedex.repository.BattleLogRepository;
import se.marcus.pokedex.service.PokemonManager;
import se.marcus.pokedex.service.StatisticsService;

import org.bson.Document;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final PokemonManager manager;
    private final Scanner scanner;
    private final ConsolePrinter printer;
    private final StatisticsService stats;
    private final BattleLogRepository battleLogRepo;

    public Menu(PokemonManager manager, Scanner scanner, ConsolePrinter printer, StatisticsService stats, BattleLogRepository battleLogRepo) {
        this.manager = manager;
        this.scanner = scanner;
        this.printer = printer;
        this.stats = stats;
        this.battleLogRepo = battleLogRepo;
    }

    public void run(PokemonCollection pokemonCollection) {

        boolean running = true;

        while (running) {
            printer.printMainMenu();

            int choice = ConsoleInput.readIntInRange(scanner, "Val: ", 1, 12);

            switch (choice) {
                case 1 -> pokemonCollection.list();
                case 2 -> manager.addPokemon();
                case 3 -> manager.editPokemon();
                case 4 -> manager.deletePokemon();
                case 5 -> manager.searchPokemon();
                case 6 -> GameLoop.run(pokemonCollection, manager, scanner, printer, stats, battleLogRepo);
                case 7 -> stats.printStats();
                case 8 -> printBattleHistory();
                case 9 -> pokemonCollection.save();
                case 10 -> pokemonCollection.load();
                case 11 -> pokemonCollection.seed();
                case 12 -> {
                    pokemonCollection.save();
                    printer.printExitMessage();
                    running = false;
                }
            }
        }
    }

    private void printBattleHistory() {
        List<Document> logs = battleLogRepo.findAll();

        if (logs.isEmpty()) {
            printer.printMessage("Ingen stridshistorik finns.");
            return;
        }

        System.out.println("\n=== Stridshistorik (MongoDB) ===");
        for (Document log : logs) {
            System.out.printf("%s — Vinnare: %s, Förlorare: %s%n",
                    log.getDate("playedAt"),
                    log.getString("winner"),
                    log.getString("loser"));
        }
    }
}
