package se.marcus.pokedex.repository;

import se.marcus.pokedex.config.DatabaseConfig;
import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonJdbc implements PokemonDao {

    @Override
    public boolean createPokemon(Pokemon pokemon) {

        String sql = """
                INSERT INTO pokemon (name, element_type, max_hp, current_hp)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, pokemon.getName());
            ps.setInt(2, pokemon.getElementType());
            ps.setInt(3, pokemon.getMaxHp());
            ps.setInt(4, pokemon.getCurrentHp());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Could't create pokemon" + pokemon.getName() + ":" + e.getMessage());
            return false;
        }

    };

    @Override
    public boolean updatePokemon(Pokemon pokemon) {

        String sql = """
                UPDATE pokemon
                SET name = ?,
                    element_type = ?::element_type,
                    max_hp = ?,
                    current_hp = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pokemon.getName());
            ps.setString(2, pokemon.getElementType().name());
            ps.setInt(3, pokemon.getMaxHp());
            ps.setInt(4, pokemon.getCurrentHp());
            ps.setInt(5, pokemon.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Couldn't update pokemon " + pokemon.getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean upsertPokemon(Pokemon pokemon) {
        return false;
    }

    @Override
    public boolean deletePokemon(Pokemon pokemon) {
        String sql = """
                DELETE FROM pokemon WHERE id = ?;
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pokemon.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Couldn't delete pokemon " + pokemon.getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean savePokemonCollection(ArrayList<Pokemon> pokemonCollection) {
        return false;
    }

    @Override
    public ArrayList<Pokemon> getPokemonCollection() {
        return null;
    }

    @Override
    public boolean deletePokemonCollection(int collectionId) {
        return false;
    }

    ;


    }






