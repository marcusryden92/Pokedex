package se.marcus.pokedex.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.marcus.pokedex.model.BattleResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {

    private List<BattleResult> results;
    private static final Path STATS_FILE = Path.of("battle_stats.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    public StatisticsService() {
        load();
    }

    public void addResult(BattleResult result) {
    results.add(result);
    save();
    }

    public void printStats() {
        if (results.isEmpty()) {
            System.out.println("Inga strider har spelats.");
            return;
        }

        System.out.println("\n=== Statistik ===");
        System.out.println("Antal strider: " + results.size());

        // Count wins per Pokemon
        Map<String, Integer> wins = new HashMap<>();
        for (BattleResult r : results ) {
            String winner = r.getWinner();
            if (wins.containsKey(winner)) {
                wins.put(winner, wins.get(winner) + 1);
            } else wins.put(winner, 1);
        }

        System.out.println("\nVinster per Pokémon:");
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        // Count most used attacks for one pattern
        Map<String, Integer> attackCount = new HashMap<>();
        for (BattleResult r : results) {
            for (String attackName : r.getAttacksUsed()) {
                if (attackCount.containsKey(attackName)) {
                    attackCount.put(attackName, attackCount.get(attackName) + 1);
                } else {
                    attackCount.put(attackName, 1);
                }
            }
        }


    }

}
