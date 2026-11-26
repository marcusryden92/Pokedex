package se.marcus.pokedex.repository;

import org.bson.Document;

import java.util.List;

public interface BattleLogRepository {

    void save(Document log);

    List<Document> findAll();

    List<Document> findByPokemonId(int pokemonId);

    boolean deleteByMatchId(String matchId);
}
