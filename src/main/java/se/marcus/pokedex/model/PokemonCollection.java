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

        Pokemon charmander = new Pokemon("Charmander", ElementType.FIRE, 39);
        charmander.addAttack(new Attack("Scratch", 40, 100, ElementType.NORMAL));
        charmander.addAttack(new Attack("Ember", 40, 100, ElementType.FIRE));
        add(charmander);

        Pokemon squirtle = new Pokemon("Squirtle", ElementType.WATER, 44);
        squirtle.addAttack(new Attack("Tackle", 40, 100, ElementType.NORMAL));
        squirtle.addAttack(new Attack("Water Gun", 40, 100, ElementType.WATER));
        add(squirtle);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", ElementType.GRASS, 45);
        bulbasaur.addAttack(new Attack("Tackle", 40, 100, ElementType.NORMAL));
        bulbasaur.addAttack(new Attack("Vine Whip", 45, 100, ElementType.GRASS));
        add(bulbasaur);

        Pokemon pikachu = new Pokemon("Pikachu", ElementType.ELECTRIC, 35);
        pikachu.addAttack(new Attack("Quick Attack", 40, 100, ElementType.NORMAL));
        pikachu.addAttack(new Attack("Thunder Shock", 40, 100, ElementType.ELECTRIC));
        add(pikachu);

        Pokemon charizard = new Pokemon("Charizard", ElementType.FIRE, 78);
        charizard.addAttack(new Attack("Slash", 70, 100, ElementType.NORMAL));
        charizard.addAttack(new Attack("Flamethrower", 90, 100, ElementType.FIRE));
        add(charizard);

        Pokemon blastoise = new Pokemon("Blastoise", ElementType.WATER, 79);
        blastoise.addAttack(new Attack("Bite", 60, 100, ElementType.NORMAL));
        blastoise.addAttack(new Attack("Water Pulse", 60, 100, ElementType.WATER));
        add(blastoise);
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
