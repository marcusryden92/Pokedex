package se.marcus.pokedex.repository;

import se.marcus.pokedex.model.Pokemon;

import java.util.ArrayList;


public interface PokemonDao {

    boolean createPokemon(Pokemon pokemon);

    boolean updatePokemon(Pokemon pokemon);

    boolean upsertPokemon(Pokemon pokemon);

    boolean deletePokemon(Pokemon pokemon);

    boolean savePokemonCollection(ArrayList<Pokemon> pokemonCollection);

    ArrayList<Pokemon> getPokemonCollection();

    boolean deletePokemonCollection(int collectionId);
}
