package cardgameex;

import cardgameex.Card.*;

public class Card {
    private int value;
    
    // Single Responsibility: Represents a card and its properties only
    public Card(int value) {
        if (value < 0 || value > 12) {
            throw new IllegalArgumentException("Card value must be between 0 (Skip-Bo) and 12.");
        }
        this.value = value;
    }

    public int getValue() { return value; }
    public void setValue(int value) { /* Same validation */ this.value = value; }
    public boolean isWild() { return value == 0; }
    @Override public String toString() { return value == 0 ? "Skip-Bo" : String.valueOf(value); }
}