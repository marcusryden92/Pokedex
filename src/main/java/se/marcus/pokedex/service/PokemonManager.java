package se.marcus.pokedex.service;

import se.marcus.pokedex.exception.InvalidPokemonException;
import se.marcus.pokedex.exception.PokemonNotFoundException;
import se.marcus.pokedex.model.DamageAttack;
import se.marcus.pokedex.model.ElementType;
import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;
import se.marcus.pokedex.ui.ConsoleInput;
import se.marcus.pokedex.ui.ConsolePrinter;

import java.util.Scanner;

public class PokemonManager {

    private final Scanner scanner;
    private final PokemonCollection collection;
    private final ConsolePrinter printer;

    public PokemonManager(Scanner scanner, PokemonCollection collection, ConsolePrinter printer) {
        this.scanner = scanner;
        this.collection = collection;
        this.printer = printer;
    }

    public void addPokemon() {
        this.printer.printNewPokemon();

        String name = ConsoleInput.readString(scanner, "Pokemonens namn: ");
        ElementType type = ConsoleInput.readElementType(scanner, "Typ:");
        int maxHp = ConsoleInput.readIntInRange(scanner, "Max HP: ", 1, 999);

        Pokemon pokemon = new Pokemon(name, type, maxHp);

        do {
            addAttack(scanner, pokemon);
        } while (pokemon.getAttacks().size() < 4
                && ConsoleInput.readIntInRange(
                scanner, "Lägg till en till attack? (1=ja, 2=nej): ", 1, 2) == 1);

        collection.add(pokemon);
        printer.printPokemonAdded(pokemon.getName());
    }

    public void editPokemon() {
        int index = pickPokemonIndex(scanner, collection);
        if (index == -1) return;

        Pokemon pokemon = collection.get(index);

        boolean editing = true;

        while (editing) {
            printer.printEditPokemon(pokemon);

            int choice = ConsoleInput.readIntInRange(scanner, "Val: ", 1, 6);

            switch (choice) {
                case 1 -> pokemon.setName(
                        ConsoleInput.readString(scanner, "Nytt namn: "));

                case 2 -> pokemon.setType(
                        ConsoleInput.readElementType(scanner, "Ny typ:"));

                case 3 -> pokemon.setMaxHp(
                        ConsoleInput.readIntInRange(scanner, "Nytt max HP: ", 1, 999));

                case 4 -> addAttack(scanner, pokemon);

                case 5 -> {
                    int a = ConsoleInput.readIntInRange(
                            scanner, "Attacknummer att ta bort: ",
                            1, pokemon.getAttacks().size());

                    try {
                        pokemon.removeAttack(a - 1);
                    } catch (InvalidPokemonException e) {
                        printer.printMessage(e.getMessage());
                    }
                }

                default -> editing = false;
            }
        }
    }


    public void deletePokemon() {
        int index = pickPokemonIndex(scanner, collection);
        if (index == -1) return;

        collection.remove(index);
        printer.printPokemonRemoved();
    }

    public void searchPokemon() {
        printer.printSearchPokemon();

        String name = ConsoleInput.readString(scanner, "Namn att söka efter: ");

        try {
            Pokemon pokemon = collection.findByName(name);
            printer.printPokemon(pokemon);
        } catch (PokemonNotFoundException e) {
            printer.printMessage(e.getMessage());
        }
    }

    public void addAttack(Scanner scanner, Pokemon pokemon) {
        printer.printNewAttack();

        String name = ConsoleInput.readString(scanner, "Attackens namn: ");
        int baseDmg = ConsoleInput.readIntInRange(scanner, "Baseskada: ", 0, 999);
        int accuracy = ConsoleInput.readIntInRange(
                scanner, "Träffsäkerhet (0-100): ", 0, 100);
        ElementType type = ConsoleInput.readElementType(scanner, "Attackens typ:");

        try {
            pokemon.addAttack(
                    new DamageAttack(name, baseDmg, accuracy, type));
        } catch (InvalidPokemonException e) {
            printer.printMessage(e.getMessage());
        }
    }

    public int pickPokemonIndex(Scanner scanner, PokemonCollection collection) {
        if (collection.size() == 0) {
            printer.printNoPokemon();
            return -1;
        }

        printer.printPokemonSelection();
        collection.shortList();

        return ConsoleInput.readIntInRange(
                scanner, "Välj nummer: ", 1, collection.size()) - 1;
    }
}