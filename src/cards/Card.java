package cards;

import enums.Rank;
import enums.Suit;

public class Card {
    private Suit suit;
    private Rank rank;

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.rankValue;
    }

    @Override
    public String toString() {
        return "["+rank+" of "+ suit + "] (" + getValue() + ")";
    }

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

}
