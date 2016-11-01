package com.wolfe;

import java.util.*;

/*
 * Created by Jeremy on 10/20/2016.
 *
 * A Player can be either Human or Computer depending on how it's extended
 *
 * This class will perform the basic player plays a hand logic by calling the
 * appropriate sub class (Human or Computer).
 *
 */
abstract class Player {

    //Create two scanners, one for Strings, and one for numbers - int and float values.

    //Use this scanner to read text data that will be stored in String variables
    static final Scanner stringScanner = new Scanner(System.in);

    static final String COMPUTER = "C";
    static final String HUMAN = "H";


    final String name;        // name
    Hand hand;        // the player's hand of cards
    int score;        // accumulated score for each round
    int scoreRound;   // score for hand during round

    // constructor
    Player(String name) {

        this.name = name;

    }


    // plays the player's current turn
    void playTurn(ArrayList<Player> players) {

        System.out.println();
        System.out.println("****** Play a Turn ****** Player " + name);


        printAllPlayerHands(players);
        Deck.printPlayerDrawsFrom(name);

        drawACard();

        checkCardUpStatus();

        if (GolfManager.allCardsUp) {
            for (Player player : players) {
            int turnScore = player.hand.scoreAllCards();
            player.scoreRound += turnScore;
            player.score += turnScore;
            }
        }

        printAllPlayerHands(players);
        Deck.printPlayerDrawsFrom(name);

    }

    // as a convenience to the current, show all players current hand (table) cards for each turn
    private void printAllPlayerHands(ArrayList<Player> players) {

        for (Player player : players) {

            player.hand.printHand(player.name);
            int test = player.hand.scoreUpCards();
            System.out.println("Score for up cards = " + test);

        }


/*      // TODO print up to four players in formatted rows and columns
        System.out.println("Player:     Player:     Player:     Player:");
        System.out.println(players.get(0).name + "      " + players.get(1).name + "     ");
*/


    }

    // check for end of round if all card are up
    private void checkCardUpStatus() {

        GolfManager.allCardsUp = hand.checkCardStatus();

    }

    public void dealCards() {

        hand = new Hand();
        hand.buildNewHand();

        hand.printHand(name);

    }

    public abstract void turnUpTwo();  // force subclass to implement


    protected abstract void drawACard();  // force subclass to implement


}// end class Player
