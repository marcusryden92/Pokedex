package se.marcus.pokedex.model;

import se.marcus.pokedex.exception.InvalidPokemonException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PokemonTest {

    @Test
    void constructor_validInput_createsPokemon() {
        Pokemon p = new Pokemon("Pikachu", ElementType.ELECTRIC, 35);
        assertEquals("Pikachu", p.getName());
        assertEquals(35, p.getMaxHp());
        assertEquals(35, p.getCurrentHp());
    }

    @Test
    void constructor_emptyName_throwsException() {
        assertThrows(InvalidPokemonException.class,
                () -> new Pokemon("", ElementType.FIRE, 50));
    }

    @Test
    void constructor_negativeHp_throwsException() {
        assertThrows(InvalidPokemonException.class, () -> new Pokemon("Pikachu", ElementType.ELECTRIC, -10));
    }

    @Test
    void takeDamage_normalAmount_reducesHp() {
        Pokemon p = new Pokemon("Pikachu", ElementType.ELECTRIC, 50);
        p.takeDamage(20);
        assertEquals(30, p.getCurrentHp());
    }

    @Test
    void takeDamage_overkill_floorsAtZero() {
        Pokemon p = new Pokemon("Pikachu", ElementType.ELECTRIC, 50);
        p.takeDamage(9999);
        assertEquals(0, p.getCurrentHp());
    }

    @Test
    void heal_overMax_capsAtMaxHp() {
        Pokemon p = new Pokemon("Pikachu", ElementType.ELECTRIC, 50);
        p.takeDamage(10);
        p.heal(9999);
        assertEquals(50, p.getCurrentHp());
    }

    @Test
    void isFainted_afterLethalDamage_returnsTrue() {
        Pokemon p = new Pokemon("Pikachu", ElementType.ELECTRIC, 50);
        p.takeDamage(9999);
        assertTrue(p.isFainted());
    }

}
