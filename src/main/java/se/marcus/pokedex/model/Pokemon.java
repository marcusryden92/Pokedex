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

    public Pokemon(String name, ElementType type, int maxHp) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attacks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public List<Attack> getAttacks() {
        return List.copyOf(attacks);
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