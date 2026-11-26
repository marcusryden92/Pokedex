
package se.marcus.pokedex.ui;

import se.marcus.pokedex.model.Attack;
import se.marcus.pokedex.model.Pokemon;

public class ConsolePrinter {

    public void printMainMenu() {
        System.out.printf("%n=== Meny ===%n");
        System.out.printf(
                "1. Visa alla Pokémons%n" +
                        "2. Lägg till ny Pokemon%n" +
                        "3. Redigera Pokemon%n" +
                        "4. Ta bort Pokemon%n" +
                        "5. Sök Pokémon%n" +
                        "%n" +
                        "6. Starta strid%n" +
                        "7. Visa statistik%n" +
                        "8. Visa stridshistorik (MongoDB)%n" +
                        "%n" +
                        "9. Spara till fil%n" +
                        "10. Ladda från fil%n" +
                        "11. Återställ till seedad data%n" +
                        "%n" +
                        "12. Avsluta%n%n");
    }

    public void printPokemonSelection() {
        System.out.printf("%n=== Välj Pokémon ===%n");
    }

    public void printNewPokemon() {
        System.out.printf("%n=== Ny Pokémon ===%n");
    }

    public void printEditPokemon(Pokemon pokemon) {
        System.out.printf("%n=== Redigera Pokémon ===%n");
        System.out.printf(
                "1. Namn (%s)%n" +
                        "2. Typ (%s)%n" +
                        "3. Max HP (%d)%n" +
                        "4. Lägg till attack%n" +
                        "5. Ta bort attack%n" +
                        "6. Klar%n%n",
                pokemon.getName(),
                pokemon.getElementType().toSwedish(),
                pokemon.getMaxHp());
    }

    public void printNewAttack() {
        System.out.printf("%n=== Ny attack ===%n");
    }

    public void printPokemon(Pokemon pokemon) {
        System.out.printf("%nNamn: %s%n", pokemon.getName());
        System.out.printf("Typ: %s%n", pokemon.getElementType().toSwedish());
        System.out.printf("Max HP: %d%n", pokemon.getMaxHp());
        System.out.printf("Nuvarande HP: %d%n", pokemon.getCurrentHp());

        for (Attack attack : pokemon.getAttacks()) {
            System.out.printf("   - %s%n", attack);
        }
    }

    public void printSearchPokemon() {
        System.out.printf("%n=== Sök Pokémon ===%n");
    }

    public void printNoPokemon() {
        System.out.println("Inga Pokémon finns.");
    }

    public void printPokemonAdded(String name) {
        System.out.printf("%s tillagd.%n", name);
    }

    public void printPokemonRemoved() {
        System.out.println("Borttagen.");
    }

    public void printBattleNotPossible() {
        System.out.println("Du behöver minst 2 Pokémon för att starta en strid.");
    }

    public void printCpuChoice(String name) {
        System.out.println("CPU valde " + name + "!");
    }

    public void printDifficultyMenu() {
        System.out.println("Svårighetsgrad:");
        System.out.println("1. Lätt (slumpmässig)");
        System.out.println("2. Svår (aggressiv)");
    }

    public void printBattleLog() {
        System.out.println("\n=== Stridslogg ===");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printExitMessage() {
        System.out.println("Avslutar...");
    }
}
