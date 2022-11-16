import cards.Card;
import cards.Deck;
import enums.*;

public class Blackjack {
    public static void main(String[] args) {
        Deck testDeck = new Deck(0);
        Card aCard = new Card(Suit.CLUB, Rank.QUEEN);
        Card bCard = new Card(Suit.DIAMOND,Rank.ACE);
        Card cCard = new Card(Suit.SPADE, Rank.SIX);
//Add the cards to the deck
        testDeck.addCard(aCard);
        testDeck.addCard(bCard);
        testDeck.addCard(cCard);
//Print out the deck
        System.out.println(testDeck);

        Game blackjack = new Game();
    }
}
