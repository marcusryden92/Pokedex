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
    results.add(result);}

}
