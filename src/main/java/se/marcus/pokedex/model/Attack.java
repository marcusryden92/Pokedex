package se.marcus.pokedex.model;

public class Attack {

    private String name;
    private int baseDmg;
    private int accuracy;
    private ElementType type;

    public Attack() {}

    public Attack(String name, int baseDmg, int accuracy, ElementType type) {
        this.name = name;
        this.baseDmg = baseDmg;
        this.accuracy = accuracy;
        this.type = type;
    }

    public String getName()      { return name; }
    public int getBaseDmg()      { return baseDmg; }
    public int getAccuracy()     { return accuracy; }
    public ElementType getType() { return type; }

    public void setName(String name)      { this.name = name; }
    public void setBaseDmg(int baseDmg)   { this.baseDmg = baseDmg; }
    public void setAccuracy(int accuracy) { this.accuracy = accuracy; }
    public void setType(ElementType type) { this.type = type; }

    public String toString() {
        return String.format("%s (%d dmg, %d%% träff, %s)", name, baseDmg, accuracy, type.toSwedish());
    }
}
