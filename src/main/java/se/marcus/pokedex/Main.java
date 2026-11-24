package se.marcus.pokedex;

import se.marcus.pokedex.model.PokemonCollection;
import se.marcus.pokedex.service.PokemonManager;
import se.marcus.pokedex.service.StatisticsService;
import se.marcus.pokedex.ui.ConsolePrinter;
import se.marcus.pokedex.ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PokemonCollection collection = new PokemonCollection();
        Scanner scanner = new Scanner(System.in);
        ConsolePrinter printer = new ConsolePrinter();
        StatisticsService stats = new StatisticsService();

        PokemonManager manager = new PokemonManager(scanner, collection, printer);

        collection.load();
        if (collection.size() == 0) {
            collection.seed();
        }

        Menu menu = new Menu(manager, scanner, printer, stats);
        menu.run(collection);
    }
}