package se.marcus.pokedex.battle;

import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.ui.ConsoleInput;

import java.util.List;
import java.util.Scanner;


public class BattleEngine {

    private final Pokemon player;
    private final Pokemon cpu;
    private final CpuStrategy strategy;
    private final BattleLog log;

    public BattleEngine(Pokemon player, Pokemon cpu, CpuStrategy strategy) {
        this.player = player;
        this.cpu = cpu;
        this.strategy = strategy;
        this.log = new BattleLog();
    }

    public BattleLog getLog() {
        return log;
    }

    public void run(Scanner scanner) {
        player.resetHp();
        cpu.resetHp();
        log.add("=== " + player.getName() + " vs " + cpu.getName() + " ===");

        while (!player.isFainted() && cpu.isFainted()) {

        }
    }
}
