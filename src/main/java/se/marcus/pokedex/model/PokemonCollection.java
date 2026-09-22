package se.marcus.pokedex.model;

import java.util.ArrayList;

public class PokemonCollection {
    private ArrayList<Pokemon> pokemonCollection = new ArrayList<>();

    public void seed() {}

    public void list() {
        for (Pokemon pokemon : pokemonCollection) {
            System.out.printf("Namn: %s%n", pokemon.getName());
            System.out.printf("Typ: %s%n", pokemon.getType());
            System.out.printf("Max HP: %d%n", pokemon.getMaxHp());
            System.out.printf("Nuvarande HP: %d%n", pokemon.getCurrentHp());
            System.out.printf("Attacker: %n st", pokemon.getAttacks().size());
            System.out.println("========");
        }
    }

    public void save() {}

    public void load() {}

    public void add(Pokemon pokemon) {
        pokemonCollection.add(pokemon);
    }

    public Pokemon get(int index) {
        return pokemonCollection.get(index);
    }

    public void remove(int index) {
        pokemonCollection.remove(index);
    }
}
