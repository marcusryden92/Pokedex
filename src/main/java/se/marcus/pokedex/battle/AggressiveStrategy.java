package se.marcus.pokedex.battle;

import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.Pokemon;
import java.util.List;
import java.util.Random;

public class AggressiveStrategy implements CpuStrategy {

    private final Random random = new Random();

    @Override
    public Attack chooseAttack(Pokemon self, Pokemon enemy, List<Attack> attacks) {
        Attack best = attacks.getFirst();
        for (Attack a : attacks) {
            if (a.getBaseDmg() > best.getBaseDmg()) {
                best = a;
            }
        }

        return best;
    }
}
