package cards;

import cards.enums.Rank;
import cards.enums.Suit;

import java.util.*;

public class Deck {
    private List<Card> deck;

    public List<Card> getDeck() {
        return deck;
    }

    public Deck(int size){
        deck = new ArrayList<Card>();

        limitReached:
        while(deck.size() < size) {

            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    if (deck.size() >= size) {
                        break limitReached;
                    }
                    deck.add(new Card(suit, rank));

                }
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



    public Card takeCard() {
        Card card = deck.get(0); // maybe new Card(deck.get(0)) but not sure yet
        deck.remove(0);
        return card;
    }

    public boolean hasCards(){
        if (deck.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public void emptyDeck(){
        deck.clear();
    }

    public void addCards(List<Card> cards){
        deck.addAll(cards);
    }

    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getDeck());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }

    public String toString(){
        if (deck.isEmpty()) {
            return "[Deck is empty]";
        }

        StringBuilder output = new StringBuilder();
        for(Card card : deck) {
            output.append(card);
            output.append("\n");
        }
        return output.toString();
    }

    public int cardsLeft() {
        return deck.size();
    }
}
