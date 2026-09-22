package se.marcus.pokedex.model;

public enum ElementType {
    FIRE, WATER, GRASS, ELECTRIC, NORMAL;

    public String toSwedish() {
        return switch (this) {
            case FIRE -> "Eld";
            case WATER -> "Vatten";
            case GRASS -> "Gräs";
            case ELECTRIC -> "El";
            case NORMAL -> "Normal";
        };
    }
}
