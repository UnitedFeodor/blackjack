package players;

import cards.Deck;

import java.util.Scanner;

public class Player extends Person {

    private Scanner input;

    private int money;

    //Create a new Player
    public Player(Scanner scanner,int money) {
        super.setName("Player");
        input = scanner;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public int bet() {
        int bet = 0;
        boolean isUserInput = true;

        //while were getting a number...
        while(isUserInput) {
            try {
                System.out.println("Enter your bet: ");
                bet = input.nextInt();
                if (bet > 0 && bet <= this.money) {
                    isUserInput = false;
                    //this.money -= bet;
                    
                } else {
                    System.out.println("Invalid bet amount!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.next();
            }
        }
        return bet;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //Allow the player to make decisions
    public void makeDecision(Deck deck, Deck discard) {
        int  decision = 0;
        boolean isUserInput = true;

        //while were getting a number...
        while(isUserInput){

            try{
                System.out.println("Would you like to: 1) Hit or 2) Stand");
                decision = input.nextInt();
                isUserInput = false;

            }
            catch(Exception e){
                System.out.println("Invalid input");
                input.next();
            }
            //we don't close the scanner, because we will need it later.

            if (decision == 1) {
                //hit the deck using the deck and discard deck
                this.hit(deck, discard);
                //return if they have blackjack or busted
                if(this.getHand().calculatedValue()>=21) {
                    return;
                } else {
                    //if they didn't bust or get 21, allow them to decide to hit or stand again by going back to this same method
                    this.makeDecision(deck, discard);
                }

                //if they type any number other than 1, we'll assume they're standing
            } else {
                System.out.println("You stand.");
            }
        }

    }
}
