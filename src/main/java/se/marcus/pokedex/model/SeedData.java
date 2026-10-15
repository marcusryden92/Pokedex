package se.marcus.pokedex.model;

import java.util.ArrayList;
import java.util.List;

public class SeedData {

    public static List<Pokemon> create() {
        List<Pokemon> list = new ArrayList<>();

        Pokemon charmander = new Pokemon("Charmander", ElementType.FIRE, 39);
        charmander.addAttack(new DamageAttack("Scratch", 40, 100, ElementType.NORMAL));
        charmander.addAttack(new DamageAttack("Ember", 40, 100, ElementType.FIRE));
        list.add(charmander);

        Pokemon squirtle = new Pokemon("Squirtle", ElementType.WATER, 44);
        squirtle.addAttack(new DamageAttack("Tackle", 40, 100, ElementType.NORMAL));
        squirtle.addAttack(new DamageAttack("Water Gun", 40, 100, ElementType.WATER));
        list.add(squirtle);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", ElementType.GRASS, 45);
        bulbasaur.addAttack(new DamageAttack("Tackle", 40, 100, ElementType.NORMAL));
        bulbasaur.addAttack(new DamageAttack("Vine Whip", 45, 100, ElementType.GRASS));
        list.add(bulbasaur);

        Pokemon pikachu = new Pokemon("Pikachu", ElementType.ELECTRIC, 35);
        pikachu.addAttack(new DamageAttack("Quick Attack", 40, 100, ElementType.NORMAL));
        pikachu.addAttack(new DamageAttack("Thunder Shock", 40, 100, ElementType.ELECTRIC));
        list.add(pikachu);

        Pokemon charizard = new Pokemon("Charizard", ElementType.FIRE, 78);
        charizard.addAttack(new DamageAttack("Slash", 70, 100, ElementType.NORMAL));
        charizard.addAttack(new DamageAttack("Flamethrower", 90, 100, ElementType.FIRE));
        list.add(charizard);

        Pokemon blastoise = new Pokemon("Blastoise", ElementType.WATER, 79);
        blastoise.addAttack(new DamageAttack("Bite", 60, 100, ElementType.NORMAL));
        blastoise.addAttack(new DamageAttack("Water Pulse", 60, 100, ElementType.WATER));
        list.add(blastoise);

        return list;
    }
}
