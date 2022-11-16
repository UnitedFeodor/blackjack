import cards.Deck;
import players.Dealer;
import players.Player;
import players.enums.RoundResult;

import java.util.Scanner;

public class Game {

    private Scanner input;
    private int wins;
    private int losses;
    private int pushes;

    private Deck deck;
    private Deck discarded;

    private Dealer dealer;
    private Player player;
    private int bet;
    private RoundResult roundResult;

    /**
     * Constructor for Game, creates our variables and starts the Game
     */
    public Game(){
        input = new Scanner(System.in);


        //Create a new deck with 52 cards
        deck = new Deck(52);
        //Create a new empty deck
        discarded = new Deck(0);

        //Create the People
        dealer = new Dealer();
        player = new Player(input,100);

        //Shuffle the deck and start the first round
        deck.shuffle();
        startRound();
    }

    //This  method will handle the logic for each round
    private void startRound(){
        if (wins>0 || losses>0 || pushes > 0) {
            System.out.println();
            System.out.println("Starting Next Round... Wins: " + wins + " Losses: "+ losses+ " Pushes: "+pushes);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        //Check to make sure the deck has at least 4 cards left
        if(deck.cardsLeft() < 4){
            deck.reloadDeckFromDiscard(discarded);
        }

        System.out.println("You have " + player.getMoney() +"$.");
        bet = player.bet();

        //Give the dealer two cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        //Give the player two cards
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        //Print their hands
        dealer.printFirstHand();
        player.printHand();


        playRound();
        switch (roundResult) {
            case WIN -> {
                player.setMoney(player.getMoney()+bet);
            }
            case PUSH -> {
                player.setMoney(player.getMoney());
            }
            case LOSE -> {
                player.setMoney(player.getMoney()-bet);
            }
        }
        System.out.println("You now have " + player.getMoney() +"$.");
        if (player.getMoney() <= 0) {
            System.out.println("You're bankrupt! GAME OVER :(");
            return;
        }


        //Start a new round
        System.out.println("Press <enter> to start the next game.");
        input.nextLine();

        startRound();

    }

    private void playRound() {
        //Check if dealer has BlackJack to start

        if(dealer.hasBlackjack()){
            //Show the dealer has BlackJack
            dealer.printHand();

            //Check if the player also has BlackJack
            if(player.hasBlackjack()){
                //End the round with a push
                System.out.println("You both have 21 - Push.");
                pushes++;
                roundResult = RoundResult.PUSH;
                return;//startRound();
            }
            else{
                System.out.println("Dealer has BlackJack. You lose.");
                dealer.printHand();
                losses++;
                roundResult = RoundResult.LOSE;
                return;//startRound();
            }
        }

        //Check if player has blackjack to start
        //If we got to this point, we already know the dealer didn't have blackjack
        if(player.hasBlackjack()){
            System.out.println("You have Blackjack! You win!");
            wins++;
            roundResult = RoundResult.WIN;
            return;//startRound();
        }

        player.makeDecision(deck,discarded);

        if(player.getHand().calculatedValue() > 21){
            System.out.println("You have gone over 21.");
            //count the losses
            losses++;
            roundResult = RoundResult.LOSE;
            //start the round over
            return; //startRound();
        }

        //Now it's the dealer's turn
        dealer.printHand();
        while(dealer.getHand().calculatedValue()<17){
            dealer.hit(deck, discarded);
        }

        //Check who wins
        if(dealer.getHand().calculatedValue()>21){
            System.out.println("Dealer busts.");
            wins++;
            roundResult = RoundResult.WIN;
        } else if(dealer.getHand().calculatedValue() > player.getHand().calculatedValue()){
            System.out.println("You lose.");
            losses++;
            roundResult = RoundResult.LOSE;
        } else if(player.getHand().calculatedValue() > dealer.getHand().calculatedValue()){
            System.out.println("You win.");
            wins++;
            roundResult = RoundResult.WIN;
        } else {
            System.out.println("Push.");
            roundResult = RoundResult.PUSH;
            pushes++;
        }


    }
}
