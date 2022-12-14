package cards;

import cards.enums.Rank;
import cards.enums.Suit;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Card card){
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

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
