package se.marcus.pokedex.model;
import java.util.Random;

public class DamageAttack extends Attack{
    public DamageAttack() {
        super();
    }

    public DamageAttack(String name, int baseDmg, int accuracy, ElementType type) {
        super(name, baseDmg, accuracy, type);
    }

    @Override
    public String execute(Pokemon attacker, Pokemon defender) {

        Random random = new Random();

        // Generate random number from 0-100 and check
        // if it's greater than the attacker's accuracy
        if (random.nextInt(100) >= getAccuracy()) {
            return attacker.getName() + " använde " + getName() + " men missade!";
        }

        // Attack multiplier
        double multiplier = TypeChart.getMultiplier(getType(), defender.getType());

        // Cast damage as int to lose the decimals
        int damage = (int) (getBaseDmg() * multiplier);

        // 1/16 chance of critical hit (chances that the rand == 1)
        boolean crit = random.nextInt(16) == 1;
        if (crit) {
            damage *= 2;
        }

        defender.takeDamage((damage));

        // Result string
        String result = attacker.getName() + " använde " + getName()
                + " och gjorde " + damage + " skada!";
        if (crit) result += " Kritisk träff!";
        if (multiplier > 1.0) result += " Supereffektivt!";
        if (multiplier < 1.0) result += " Inte jätteeffektivt...";

        return result;
    }
}
