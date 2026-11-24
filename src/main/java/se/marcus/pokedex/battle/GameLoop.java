package se.marcus.pokedex.battle;

import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;
import se.marcus.pokedex.service.PokemonManager;
import se.marcus.pokedex.service.StatisticsService;
import se.marcus.pokedex.ui.ConsoleInput;
import se.marcus.pokedex.ui.ConsolePrinter;

import java.util.Random;
import java.util.Scanner;

public class GameLoop {

    public static void run(PokemonCollection collection, PokemonManager manager, Scanner scanner, ConsolePrinter printer, StatisticsService stats){

            if (collection.size() < 2) {
                printer.printBattleNotPossible();
                return;
            }

            // Player picks Pokémon
            printer.printMessage("\nVälj din Pokémon");

            int playerIndex = manager.pickPokemonIndex(scanner, collection);
            if (playerIndex == -1) return;

            Pokemon player = collection.get(playerIndex);

            // CPU picks random
            Random random = new Random();
            int cpuIndex;

            do {
                cpuIndex = random.nextInt(collection.size());
            } while (cpuIndex == playerIndex);

            Pokemon cpu = collection.get(cpuIndex);
            printer.printCpuChoice(cpu.getName());

            // Pick difficulty
            printer.printDifficultyMenu();

            int diff = ConsoleInput.readIntInRange(scanner, "Val: ", 1, 2);

            CpuStrategy strategy = diff == 1
                    ? new RandomStrategy()
                    : new AggressiveStrategy();

            // Run battle
            BattleEngine engine = new BattleEngine(player, cpu, strategy);
            engine.run(scanner);

            stats.addResult(engine.getResult());

            // Print log
            printer.printBattleLog();
            engine.getLog().print();
    }
}
