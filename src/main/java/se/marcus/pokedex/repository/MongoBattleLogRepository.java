package se.marcus.pokedex.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import se.marcus.pokedex.config.MongoConfig;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoBattleLogRepository implements BattleLogRepository {

    private MongoCollection<Document> getCollection() {
        return MongoConfig.getDatabase().getCollection("battleLogs");
    }

    @Override
    public void save(Document log) {
        getCollection().insertOne(log);
    }

    @Override
    public List<Document> findAll() {
        return getCollection().find().into(new ArrayList<>());
    }

    @Override
    public List<Document> findByPokemonId(int pokemonId) {
        return getCollection()
                .find(eq("participants.pokemonId", pokemonId))
                .into(new ArrayList<>());
    }

    @Override
    public boolean deleteByMatchId(String matchId) {
        return getCollection()
                .deleteOne(eq("matchId", matchId))
                .getDeletedCount() > 0;
    }
}
