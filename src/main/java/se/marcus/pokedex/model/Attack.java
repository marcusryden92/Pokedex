package se.marcus.pokedex.model;

public class Attack {

    private String name;
    private int baseDmg;
    private int accuracy;
    private ElementType type;

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
}
