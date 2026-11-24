package se.marcus.pokedex.repository;

import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;

import java.util.ArrayList;


public interface PokemonDao {

    public static boolean createPokemon(Pokemon pokemon);

    public static boolean updatePokemon(Pokemon pokemon);

    public static boolean upsertPokemon(Pokemon pokemon);

    public static boolean deletePokemon(Pokemon pokemon);

    public static boolean savePokemonCollection(ArrayList<Pokemon> pokemonCollection);

    public static ArrayList<Pokemon> getPokemonCollection();

    public boolean deletePokemonCollection(int collectionId);
}
