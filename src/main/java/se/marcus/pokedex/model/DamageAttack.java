package se.marcus.pokedex.model;

public class DamageAttack extends Attack{
    public DamageAttack() {
        super();
    }

    public DamageAttack(String name, int baseDmg, int accuracy, ElementType type) {
        super(name, baseDmg, accuracy, type);
    }

    @Override
    public String execute(Pokemon attacker, Pokemon defender) {
        return attacker.getName() + " använde " + getName();
    }
}
