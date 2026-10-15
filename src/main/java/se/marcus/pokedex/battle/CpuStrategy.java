package se.marcus.pokedex.battle;

import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.Pokemon;
import java.util.List;

public interface CpuStrategy {
    Attack chooseAttack(Pokemon self, Pokemon enemy, List<Attack> attacks);
}
