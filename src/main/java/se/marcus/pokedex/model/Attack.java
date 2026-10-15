package se.marcus.pokedex.model;

import se.marcus.pokedex.exception.InvalidPokemonException;

public class Attack {

    private String name;
    private int baseDmg;
    private int accuracy;
    private ElementType type;

    public Attack(String name, int baseDmg, int accuracy, ElementType type) {
        setName(name);
        setBaseDmg(baseDmg);
        setAccuracy(accuracy);
        setType(type);
    }

    public String getName()      { return name; }
    public int getBaseDmg()      { return baseDmg; }
    public int getAccuracy()     { return accuracy; }
    public ElementType getType() { return type; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidPokemonException("Attackens namn får inte vara tomt.");
        }
        this.name = name;
    }

    public void setBaseDmg(int baseDmg) {
        if (baseDmg < 0) {
            throw new InvalidPokemonException("Basskada kan inte vara negativ.");
        }
        this.baseDmg = baseDmg;
    }

    public void setAccuracy(int accuracy) {
        if (accuracy < 0 || accuracy > 100) {
            throw new InvalidPokemonException("Träffsäkerhet måste vara mellan 0 och 100.");
        }
        this.accuracy = accuracy;
    }

    public void setType(ElementType type) {
        if (type == null) {
            throw new InvalidPokemonException("Attackens typ får inte vara tom.");
        }
        this.type = type;
    }

    public String toString() {
        return String.format("%s (%d dmg, %d%% träff, %s)", name, baseDmg, accuracy, type.toSwedish());
    }
}
