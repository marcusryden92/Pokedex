package se.marcus.pokedex.battle;

import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.Pokemon;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements CpuStrategy {

    private final Random random = new Random();

    @Override
    public Attack chooseAttack(Pokemon self, Pokemon enemy, List<Attack> attacks) {
        return attacks.get(random.nextInt(attacks.size()));
    }
}
