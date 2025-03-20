/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cardgameex;

//import cardgameex.Card.Suit;
//import cardgameex.Card.Value;
//import static cardgameex.CardHandGenerator.generateHand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author fagun
 */
public class SkipBoGame {
     private static final int STOCKPILE_SIZE = 10; // Small for testing; typically 30 in short games
    private static final int HAND_SIZE = 5;
    private static final int MAX_BUILDING_PILES = 4;
    private static final int MAX_DISCARD_PILES = 4;

    private Card[] stockpile; // Player's stockpile to deplete
    private ArrayList<Card> hand; // Player's hand (5 cards)
    private ArrayList<Integer> buildingPiles; // Up to 4 piles, top card value (starts at 0)
    private ArrayList<ArrayList<Card>> discardPiles; // Up to 4 discard piles
    private Scanner input;

    public SkipBoGame() {
        input = new Scanner(System.in);
        initializeGame();
    }

    // Initialize the game state
    private void initializeGame() {
        stockpile = CardHandGenerator.generateStockpile(STOCKPILE_SIZE);
        hand = new ArrayList<>(Arrays.asList(CardHandGenerator.generateHand(HAND_SIZE)));
        buildingPiles = new ArrayList<>();
        for (int i = 0; i < MAX_BUILDING_PILES; i++) {
            buildingPiles.add(0); // Empty piles start at 0
        }
        discardPiles = new ArrayList<>();
        for (int i = 0; i < MAX_DISCARD_PILES; i++) {
            discardPiles.add(new ArrayList<>()); // Empty discard piles
        }
    }

    // Main game loop
    public void play() {
        System.out.println("Welcome to Skip-Bo! Try to empty your stockpile.");
        while (stockpile.length > 0) {
            displayGameState();
            if (!makeMove()) {
                System.out.println("No valid moves left or invalid input. Turn ends.");
                refillHand();
            }
            checkBuildingPiles(); // Clear completed piles (12)
        }
        System.out.println("Congratulations! You emptied your stockpile and won!");
        input.close();
    }

    // Display current game state
    private void displayGameState() {
        System.out.println("\nStockpile (" + stockpile.length + " left): " + stockpile[0]);
        System.out.print("Hand: ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(i + ":" + hand.get(i) + " ");
        }
        System.out.println("\nBuilding Piles:");
        for (int i = 0; i < buildingPiles.size(); i++) {
            System.out.println("Pile " + i + ": " + buildingPiles.get(i));
        }
        System.out.println("Discard Piles:");
        for (int i = 0; i < discardPiles.size(); i++) {
            System.out.print("Pile " + i + ": ");
            ArrayList<Card> pile = discardPiles.get(i);
            System.out.println(pile.isEmpty() ? "Empty" : pile.get(pile.size() - 1));
        }
    }

    // Handle player move
    private boolean makeMove() {
        System.out.println("\nChoose an action:");
        System.out.println("1: Play from hand (enter '1 <card index> <building pile>')");
        System.out.println("2: Play top stockpile card (enter '2 <building pile>')");
        System.out.println("3: Discard a card (enter '3 <card index> <discard pile>')");
        System.out.println("4: End turn");

        String[] choice = input.nextLine().split(" ");
        if (choice.length == 1 && choice[0].equals("4")) {
            return false; // End turn
        }

        try {
            int action = Integer.parseInt(choice[0]);
            if (action == 1 && choice.length == 3) {
                int cardIdx = Integer.parseInt(choice[1]);
                int pileIdx = Integer.parseInt(choice[2]);
                return playFromHand(cardIdx, pileIdx);
            } else if (action == 2 && choice.length == 2) {
                int pileIdx = Integer.parseInt(choice[1]);
                return playFromStockpile(pileIdx);
            } else if (action == 3 && choice.length == 3) {
                int cardIdx = Integer.parseInt(choice[1]);
                int pileIdx = Integer.parseInt(choice[2]);
                return discardCard(cardIdx, pileIdx);
            }
        } catch (Exception e) {
            return false; // Invalid input
        }
        return false;
    }

    // Play a card from hand to a building pile
    private boolean playFromHand(int cardIdx, int pileIdx) {
        if (cardIdx < 0 || cardIdx >= hand.size() || pileIdx < 0 || pileIdx >= MAX_BUILDING_PILES) {
            return false;
        }
        Card card = hand.get(cardIdx);
        int pileValue = buildingPiles.get(pileIdx);
        if (isValidPlay(card, pileValue)) {
            hand.remove(cardIdx);
            buildingPiles.set(pileIdx, card.isWild() ? pileValue + 1 : card.getValue());
            return true;
        }
        return false;
    }

    // Play the top stockpile card to a building pile
    private boolean playFromStockpile(int pileIdx) {
        if (stockpile.length == 0 || pileIdx < 0 || pileIdx >= MAX_BUILDING_PILES) {
            return false;
        }
        Card card = stockpile[0];
        int pileValue = buildingPiles.get(pileIdx);
        if (isValidPlay(card, pileValue)) {
            stockpile = Arrays.copyOfRange(stockpile, 1, stockpile.length);
            buildingPiles.set(pileIdx, card.isWild() ? pileValue + 1 : card.getValue());
            return true;
        }
        return false;
    }

    // Discard a card from hand to a discard pile
    private boolean discardCard(int cardIdx, int pileIdx) {
        if (cardIdx < 0 || cardIdx >= hand.size() || pileIdx < 0 || pileIdx >= MAX_DISCARD_PILES) {
            return false;
        }
        Card card = hand.remove(cardIdx);
        discardPiles.get(pileIdx).add(card);
        return true;
    }

    // Check if a card can be played on a building pile
    private boolean isValidPlay(Card card, int pileValue) {
        return card.isWild() || (pileValue == 0 && card.getValue() == 1) || (card.getValue() == pileValue + 1);
    }

    // Refill hand to 5 cards if stockpile isn’t empty
    private void refillHand() {
        while (hand.size() < HAND_SIZE && stockpile.length > 0) {
            hand.add(CardHandGenerator.generateRandomCard());
        }
    }

    // Clear building piles that reach 12
    private void checkBuildingPiles() {
        for (int i = 0; i < buildingPiles.size(); i++) {
            if (buildingPiles.get(i) == 12) {
                buildingPiles.set(i, 0); // Reset to empty
            }
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        SkipBoGame game = new SkipBoGame();
        game.play();
    }
}
