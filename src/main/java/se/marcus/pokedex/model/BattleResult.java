package se.marcus.pokedex.model;

import java.util.ArrayList;
import java.util.List;

public class BattleResult {

    private String winner;
    private String loser;
    private String date;
    private List<String> attacksUsed;

    // Constructor for Jackson
    public BattleResult() {
        this.attacksUsed = new ArrayList<>();
    }

    public BattleResult(String winner, String loser, String date,List<String> attacksUsed) {
        this.winner = winner;
        this.loser = loser;
        this.date = date;
        this.attacksUsed = new ArrayList<>(attacksUsed);
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getAttacksUsed() {
        return attacksUsed;
    }

    public void setAttacksUsed(List<String> attacksUsed) {
        this.attacksUsed = attacksUsed;
    }
}
