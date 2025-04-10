package cardgameex;

import java.util.Random;

public class CardHandGenerator {
    private static final Random random = new Random();
    private static final int MAX_NUMBER = 12; // Highest numbered card
    private static final double WILD_CARD_PROBABILITY = 0.1; // Roughly 18/162 ≈ 11% chance of Skip-Bo card

    // Factory Method Pattern: Encapsulates card creation, open for extension (OCP)
    public static Card[] generateStockpile(int size) {
        Card[] stockpile = new Card[size];
        for (int i = 0; i < size; i++) {
            stockpile[i] = createCard();
        }
        return stockpile;
    }

    // Generate a hand of cards (e.g., 5 cards in Skip-Bo)
    public static Card[] generateHand(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Hand size must be positive.");
        }
        Card[] hand = new Card[size];
        for (int i = 0; i < size; i++) {
            hand[i] = generateRandomCard();
        }
        return hand;
    }
    
    // Factory Method: Can be overridden in subclasses for different card types
    private static Card createCard() {
        return random.nextDouble() < WILD_CARD_PROBABILITY ? new Card(0) : new Card(random.nextInt(MAX_NUMBER) + 1);
    }

    // Helper method to generate a single random card
    static Card generateRandomCard() {
        // Decide if it's a wild card (Skip-Bo) or numbered card
        if (random.nextDouble() < WILD_CARD_PROBABILITY) {
            return new Card(0); // Skip-Bo wild card
        } else {
            // Random number between 1 and 12
            int value = random.nextInt(MAX_NUMBER) + 1;
            return new Card(value);
        }
    }
}