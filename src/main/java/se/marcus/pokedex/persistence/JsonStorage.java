package se.marcus.pokedex.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.marcus.pokedex.model.Pokemon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void save(Path path, List<Pokemon> pokemons) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), pokemons);
        } catch (IOException e) {
            System.out.println("Kunde inte spara JSON: " + e.getMessage());
        }
    }

    public static List<Pokemon> load(Path path) {
        if (!Files.exists(path)) return new ArrayList<>();
        try {
            Pokemon[] arr = mapper.readValue(path.toFile(), Pokemon[].class);
            return new ArrayList<>(List.of(arr));
        } catch (IOException e) {
            System.out.println("Kunde inte ladda JSON (korrupt eller fel format): " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
