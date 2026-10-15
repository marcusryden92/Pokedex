package se.marcus.pokedex.battle;

import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.Pokemon;
import se.marcus.pokedex.ui.ConsoleInput;
import se.marcus.pokedex.model.BattleResult;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BattleEngine {

    private final Pokemon player;
    private final Pokemon cpu;
    private final CpuStrategy strategy;
    private final BattleLog log;
    private final List<String> attacksUsed = new ArrayList<>();

    private String winner;
    private String loser;



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
            // Show HP for both players
            System.out.println();
            System.out.println("Din: " + player);
            System.out.println("CPU: " + cpu);

            // List available attacks
            List<Attack> attacks = player.getAttacks();
            for (int i = 0; i < attacks.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, attacks.get(i));
            }

            // Player picks an attack
            int choice = ConsoleInput.readIntInRange(scanner, "Välj attack: ", 1, attacks.size());
            Attack playerAttack = attacks.get(choice - 1);

            // Execute player's attack - returns description string
            String playerResult = playerAttack.execute(player, cpu);
            log.add(playerResult);
            attacksUsed.add(playerAttack.getName());
            System.out.println(playerResult);

            // CPU picks an attack via its strategy
            Attack cpuAttack = strategy.chooseAttack(cpu, player, cpu.getAttacks());

            // Execute CPU's attack
            String cpuResult = cpuAttack.execute(cpu, player);
            log.add(cpuResult);
            attacksUsed.add(cpuAttack.getName());
            System.out.println(cpuResult);
        }

        // Announce winner
        if (player.isFainted()) {
            winner = cpu.getName();
            loser = player.getName();
        } else {
            winner = player.getName();
            loser = cpu.getName();
        }

        String endMessage = winner + " vann striden!";
        log.add(endMessage);
        System.out.println("\n" + endMessage);

        // Reset HP for both players
        player.resetHp();
        cpu.resetHp();
    }

    public BattleResult getResult() {
        return new BattleResult(winner, loser, LocalDateTime.now().toString(), attacksUsed);
    }
}
