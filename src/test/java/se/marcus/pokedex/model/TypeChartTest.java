package se.marcus.pokedex.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeChartTest {

    @ParameterizedTest
    @CsvSource({
            "FIRE,     GRASS,    2.0",
            "WATER,    FIRE,     2.0",
            "GRASS,    WATER,    2.0",
            "ELECTRIC, WATER,    2.0",
            "FIRE,     WATER,    0.5",
            "WATER,    GRASS,    0.5",
            "GRASS,    FIRE,     0.5",
            "NORMAL,   FIRE,     1.0",
            "FIRE,     FIRE,     1.0"
    })

    void typeEffectiveness(ElementType atk, ElementType def, double expected) {
        assertEquals(expected, TypeChart.getMultiplier(atk, def), 0.001);
    }}