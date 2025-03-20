package cardgameex;

import cardgameex.Card.*;

public class Card {
    private int value; // 0 for Skip-Bo (wild), 1-12 for numbered cards

    // Constructor
    public Card(int value) {
        if (value < 0 || value > 12) {
            throw new IllegalArgumentException("Card value must be between 0 (Skip-Bo) and 12.");
        }
        this.value = value;
    }

    // Get the value of the card
    public int getValue() {
        return value;
    }

    // Set the value (with validation)
    public void setValue(int value) {
        if (value < 0 || value > 12) {
            throw new IllegalArgumentException("Card value must be between 0 (Skip-Bo) and 12.");
        }
        this.value = value;
    }

    // String representation of the card
    @Override
    public String toString() {
        if (value == 0) {
            return "Skip-Bo";
        }
        return String.valueOf(value);
    }

    // Check if the card is a Skip-Bo wild card
    public boolean isWild() {
        return value == 0;
    }
}