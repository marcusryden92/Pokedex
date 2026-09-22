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
            System.out.printf("Attacker: %d%n%n", pokemon.getAttacks().size());
            System.out.println("========");
        }
    }

    public void save() {}

    public void load() {}
}
