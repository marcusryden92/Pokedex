package se.marcus.pokedex.model;

import se.marcus.pokedex.exception.InvalidPokemonException;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private String name;
    private ElementType type;
    private int maxHp;
    private int currentHp;
    private List<Attack> attacks;

    public Pokemon() {
        this.attacks = new ArrayList<>();
    }

    public Pokemon(String name, ElementType type, int maxHp) {
        this.attacks = new ArrayList<>();
        setName(name);
        setType(type);
        setMaxHp(maxHp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidPokemonException("Namnet får inte vara tomt.");
        }
        this.name = name;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        if (type == null) {
            throw new InvalidPokemonException("Typen får inte vara tom.");
        }
        this.type = type;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp <= 0) {
            throw new InvalidPokemonException("Max HP måste vara större än 0.");
        }
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp < 0 || currentHp > maxHp) {
            throw new InvalidPokemonException("Nuvarande HP måste vara mellan 0 och max HP.");
        }
        this.currentHp = currentHp;
    }

    public List<Attack> getAttacks() {
        return List.copyOf(attacks);
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = new ArrayList<>(attacks);
    }

    public void addAttack(Attack attack) {
        if (attacks.size() >= 4) {
            throw new InvalidPokemonException("En Pokemon kan ha max 4 attacker.");
        }
        attacks.add(attack);
    }

    public void removeAttack(int index) {
        if (attacks.size() <= 1) {
            throw new InvalidPokemonException("En Pokemon måste ha minst 1 attack.");
        }
        attacks.remove(index);
    }
}