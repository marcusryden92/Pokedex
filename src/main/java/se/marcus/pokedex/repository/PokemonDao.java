package se.marcus.pokedex.repository;

import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;

import java.util.ArrayList;


public interface PokemonDao {

    public boolean createPokemon(Pokemon pokemon);

    public boolean updatePokemon(Pokemon pokemon);

    public boolean upsertPokemon(Pokemon pokemon);

    public boolean deletePokemon(Pokemon pokemon);

    public boolean savePokemonCollection(ArrayList<Pokemon> pokemonCollection);

    public ArrayList<Pokemon> getPokemonCollection();

    public boolean deletePokemonCollection(int collectionId);
}
