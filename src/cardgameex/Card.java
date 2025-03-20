package cardgameex;

import cardgameex.Card.*;

public class Card {

    // Example using Array
    private int value;
    private String suit;

    public static final String[] SUITS={
       "HEARTS", "CLUBS","SPADES", "DIAMONDS" };   
    
    public Card(int value, String suit)
    {
        this.value = value;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

	
    //Example using Enum
   /* public enum Suit {
        HEARTS, CLUBS, SPADES, DIAMONDS
    }

    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING,
        JACL
    }
    private Value value;
    private Suit suit;

    public Card(Value v, Suit s) {
        value = v;
        suit = s;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }*/

}