package players;

import cards.Card;
import cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    protected List<Card> hand;

    public Hand(){
        hand = new ArrayList<Card>();
    }

    public void takeCardFromDeck(Deck deck){
        hand.add(deck.takeCard());
    }

    public int calculatedValue() {
        //variable to count number of aces, and current total value
        int value = 0;
        int aceCount = 0;

        //For each card in this hand
        for(Card card : hand){
            //Add the card value to the hand
            value += card.getValue();
            //Count how many aces have been added
            if (card.getValue() == 11){
                aceCount++;
            }
        }

        // probably shouldn't be here and instead work as an impl of a method and Hand should be an interface

        //if we have a scenario where we have multiple aces, as may be the case of drawing 10, followed by two or more aces, (10+11+1 > 21)
        //go back and set each ace to 1 until get back under 21, if possible
        if (value > 21 && aceCount > 0){
            while(aceCount > 0 && value > 21){
                aceCount --;
                value -= 10;
            }
        }
        return value;
    }

    public Card getCard(int ind){
        return hand.get(ind);
    }

    public String toString(){
        if (hand.isEmpty()) {
            return "[Hand is empty]";
        }

        StringBuilder output = new StringBuilder();
        for(Card card : hand){
            output.append(card);
            output.append(" - ");
        }
        return output.toString();
    }

    public void discardHandToDeck(Deck discardDeck){

        //copy cards from hand to discardDeck
        discardDeck.addCards(hand);

        //clear the hand
        hand.clear();

    }
}
