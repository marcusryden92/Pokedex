package se.marcus.pokedex.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class MongoConfig {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String URI = dotenv.get("MONGODB_URI");
    private static MongoClient client;

    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(URI);
        }
        return client.getDatabase("pokedex_battler");
    }

    public static void close() {
        if (client != null) {
            client.close();
            client = null;
        }
    }
}
