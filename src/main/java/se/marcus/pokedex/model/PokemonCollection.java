package se.marcus.pokedex.model;

import java.util.ArrayList;

public class PokemonCollection {
    private ArrayList<Pokemon> pokemonCollection = new ArrayList<>();

    public void seed() {}

    public void list() {
        for (int i = 0; i < pokemonCollection.size(); i++) {
            Pokemon pokemon = pokemonCollection.get(i);
            System.out.printf("%d. Namn: %s%n", i, pokemon.getName());
            System.out.printf("Typ: %s%n", pokemon.getType());
            System.out.printf("Max HP: %d%n", pokemon.getMaxHp());
            System.out.printf("Nuvarande HP: %d%n", pokemon.getCurrentHp());
            System.out.printf("Attacker: %d st %n", pokemon.getAttacks().size());
            System.out.println("========%n%n");
        }
    }

    public void shortList() {
        for (int i = 0; i < pokemonCollection.size(); i++) {
            Pokemon pokemon = pokemonCollection.get(i);
            System.out.printf("%d. Namn: %s%n", i, pokemon.getName());
        }
    }

    public void save() {}

    public void load() {}

    public void add(Pokemon pokemon) {
        pokemonCollection.add(pokemon);
    }

    public int size() {
        return pokemonCollection.size();
    }

    public Pokemon get(int index) {
        return pokemonCollection.get(index);
    }

    public void remove(int index) {
        pokemonCollection.remove(index);
    }
}
