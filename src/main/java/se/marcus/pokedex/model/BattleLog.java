package se.marcus.pokedex.model;
import java.util.ArrayList;
import java.util.List;

public class BattleLog {

    private final List<String> entries = new ArrayList<>();

    public void add (String message) {
        entries.add(message);
    }

    public List<String> getEntries() {
        return List.copyOf(entries);
    }

    public void print() {
        for (String entry : entries) {
            System.out.println(entry);
        }
    }
}
