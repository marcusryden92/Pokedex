package se.marcus.pokedex;

import se.marcus.pokedex.model.PokemonCollection;
import se.marcus.pokedex.ui.Menu;

public class Main {
    public static void main(String[] args) {

        PokemonCollection collection = new PokemonCollection();
        collection.load();
        if (collection.size() == 0) {
            collection.seed();
        }
        Menu.run(collection);
    }
}