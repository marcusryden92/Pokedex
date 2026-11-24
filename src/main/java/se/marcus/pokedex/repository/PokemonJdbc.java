package se.marcus.pokedex.repository;

import se.marcus.pokedex.config.DatabaseConfig;
import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.DamageAttack;
import se.marcus.pokedex.model.ElementType;
import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.model.PokemonCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonJdbc implements PokemonDao {

    @Override
    public static boolean createPokemon(Pokemon pokemon) {

        String pokemonSql = """
                INSERT INTO pokemon (name, element_type_id, max_hp, current_hp)
                VALUES (?, (SELECT id FROM element_type WHERE name = ?), ?, ?)
                RETURNING id
                """;

        String attackSql = """
                INSERT INTO attacks (name, base_dmg, accuracy, element_type_id, pokemon_id)
                VALUES (?, ?, ?, (SELECT id FROM element_type WHERE name = ?), ?)
                RETURNING id
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pokemonPs = conn.prepareStatement(pokemonSql);
             PreparedStatement attackPs = conn.prepareStatement(attackSql)) {

            pokemonPs.setString(1, pokemon.getName());
            pokemonPs.setString(2, pokemon.getElementType().name());
            pokemonPs.setInt(3, pokemon.getMaxHp());
            pokemonPs.setInt(4, pokemon.getCurrentHp());

            try (ResultSet rs = pokemonPs.executeQuery()) {
                rs.next();
                pokemon.setId(rs.getInt("id"));
            }

            for (Attack attack : pokemon.getAttacks()) {
                attackPs.setString(1, attack.getName());
                attackPs.setInt(2, attack.getBaseDmg());
                attackPs.setInt(3, attack.getAccuracy());
                attackPs.setString(4, attack.getType().name());
                attackPs.setInt(5, pokemon.getId());

                try (ResultSet rs = attackPs.executeQuery()) {
                    rs.next();
                    attack.setId(rs.getInt("id"));
                    attack.setPokemonId(pokemon.getId());
                }
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't create pokemon " + pokemon.getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public static boolean updatePokemon(Pokemon pokemon) {

        String sql = """
                UPDATE pokemon
                SET name = ?,
                    element_type_id = (SELECT id FROM element_type WHERE name = ?),
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
    public static boolean upsertPokemon(Pokemon pokemon) {
        if (pokemon.getId() == 0) {
            return createPokemon(pokemon);
        }
        return updatePokemon(pokemon);
    }

    @Override
    public static boolean deletePokemon(Pokemon pokemon) {
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
    public static boolean savePokemonCollection(ArrayList<Pokemon> pokemonCollection) {
        boolean allSaved = true;
        for (Pokemon pokemon : pokemonCollection) {
            if (!upsertPokemon(pokemon)) {
                allSaved = false;
            }
        }
        return allSaved;
    }

    @Override
    public static ArrayList<Pokemon> getPokemonCollection() {
        String pokemonSql = """
                SELECT p.id, p.name, et.name AS element_type, p.max_hp, p.current_hp
                FROM pokemon p
                JOIN element_type et ON et.id = p.element_type_id
                ORDER BY p.id
                """;

        String attackSql = """
                SELECT a.id, a.name, a.base_dmg, a.accuracy, et.name AS element_type, a.pokemon_id
                FROM attacks a
                JOIN element_type et ON et.id = a.element_type_id
                ORDER BY a.id
                """;

        ArrayList<Attack> attackList = new ArrayList<>();
        ArrayList<Pokemon> pokemonList = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement attackPs = conn.prepareStatement(attackSql);
             ResultSet attackRs = attackPs.executeQuery();
             PreparedStatement pokemonPs = conn.prepareStatement(pokemonSql);
             ResultSet pokemonRs = pokemonPs.executeQuery()) {

            while (attackRs.next()) {
                Attack attack = new DamageAttack(
                        attackRs.getString("name"),
                        attackRs.getInt("base_dmg"),
                        attackRs.getInt("accuracy"),
                        ElementType.valueOf(attackRs.getString("element_type")));
                attack.setId(attackRs.getInt("id"));
                attack.setPokemonId(attackRs.getInt("pokemon_id"));
                attackList.add(attack);
            }

            while (pokemonRs.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(pokemonRs.getInt("id"));
                pokemon.setName(pokemonRs.getString("name"));
                pokemon.setType(ElementType.valueOf(pokemonRs.getString("element_type")));
                pokemon.setMaxHp(pokemonRs.getInt("max_hp"));
                pokemon.setCurrentHp(pokemonRs.getInt("current_hp"));
                pokemonList.add(pokemon);
            }

            for (Pokemon pokemon : pokemonList) {
                for (Attack attack : attackList) {
                    if (attack.getPokemonId() == pokemon.getId()) {
                        pokemon.addAttack(attack);
                    }
                }
            }

            return pokemonList;
        } catch (SQLException e) {
            System.err.println("Couldn't load pokemon: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public static boolean deletePokemonCollection(int collectionId) {
        String sql = """
                DELETE FROM pokemon
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't delete pokemon collection: " + e.getMessage());
            return false;
        }
    }
}






