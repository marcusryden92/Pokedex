package se.marcus.pokedex.model;

import se.marcus.pokedex.exception.PokemonNotFoundException;
import se.marcus.pokedex.persistence.JsonStorage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PokemonCollection {
    private ArrayList<Pokemon> pokemonCollection = new ArrayList<>();

    private static final Path DATA_FILE = Path.of("pokedex.json");

    public void seed() {
        pokemonCollection.clear();
        pokemonCollection.addAll(SeedData.create());
    }

    public void list() {
        for (int i = 0; i < pokemonCollection.size(); i++) {
            Pokemon pokemon = pokemonCollection.get(i);
            System.out.printf("%d. Namn: %s%n", i + 1, pokemon.getName());
            System.out.printf("Typ: %s%n", pokemon.getType().toSwedish());
            System.out.printf("Max HP: %d%n", pokemon.getMaxHp());
            System.out.printf("Nuvarande HP: %d%n", pokemon.getCurrentHp());
            System.out.printf("Attacker: %d st%n", pokemon.getAttacks().size());
            for (Attack attack : pokemon.getAttacks()) {
                System.out.printf("   - %s%n", attack);
            }
            System.out.printf("========%n%n");
        }
    }

    public void shortList() {
        for (int i = 0; i < pokemonCollection.size(); i++) {
            Pokemon pokemon = pokemonCollection.get(i);
            System.out.printf("%d. Namn: %s%n", i + 1, pokemon.getName());
        }
    }

    public void save() {
        JsonStorage.save(DATA_FILE, pokemonCollection);
        System.out.println("Sparat till " + DATA_FILE.toAbsolutePath());
    }

    public void load() {
        List<Pokemon> loaded = JsonStorage.load(DATA_FILE);
        pokemonCollection.clear();
        pokemonCollection.addAll(loaded);
    }

    public void add(Pokemon pokemon) {
        pokemonCollection.add(pokemon);
    }

    public int size() {
        return pokemonCollection.size();
    }

    public Pokemon get(int index) {
        return pokemonCollection.get(index);
    }

    public Pokemon findByName(String name) {
        for (Pokemon pokemon : pokemonCollection) {
            if (pokemon.getName().equalsIgnoreCase(name)) {
                return pokemon;
            }
        }
        throw new PokemonNotFoundException("Ingen Pokémon med namnet \"" + name + "\" hittades.");
    }

    public void remove(int index) {
        pokemonCollection.remove(index);
    }
}
