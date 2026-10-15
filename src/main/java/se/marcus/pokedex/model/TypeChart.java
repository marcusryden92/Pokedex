package se.marcus.pokedex.model;

import java.util.HashMap;
import java.util.Map;

public class TypeChart {
    private static final Map<String, Double> TABLE = Map.of(
            "FIRE>GRASS", 2.0,
            "WATER>FIRE", 2.0,
            "GRASS>WATER", 2.0,
            "ELECTRIC>WATER", 2.0,
            "FIRE>WATER", 0.5,
            "WATER>GRASS", 0.5,
            "GRASS>FIRE", 0.5
    );

    public static double getMultiplier(ElementType attackType, ElementType defenderType) {
        String attackKey = attackType + ">" + defenderType;
        return TABLE.getOrDefault(attackKey, 1.0);
    }
}
