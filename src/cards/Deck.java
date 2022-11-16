package cards;

import enums.DeckContents;
import enums.Rank;
import enums.Suit;

import java.util.*;

public class Deck {
    private List<Card> deck;
    public Deck(int size){
        deck = new ArrayList<Card>();

        limitReached:
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                if (deck.size() >= size) {
                    break limitReached;
                }
                deck.add(new Card(suit, rank));

            }
        }
    }


    public void addCard(Card card){
        deck.add(card);
    }

    public void shuffle() {
        Collections.shuffle(deck,new Random());
    }

    // yet to implement
    public void sort() {
        deck.sort(new Comparator<Card>() {

            @Override
            public int compare(Card o1, Card o2) {
                return 0;
            }
        });
    }

    public String toString(){
        StringBuilder output = new StringBuilder();

        for(Card card : deck) {
            output.append(card);
            output.append("\n");
        }
        return output.toString();
    }
}
