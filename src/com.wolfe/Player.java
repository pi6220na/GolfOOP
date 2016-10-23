package com.wolfe;

import java.util.*;

/**
 * Created by Jeremy on 10/20/2016.
 *
 * A Player can be either Human or Computer depending on how it's extended
 *
 * This class will:
 *
 *
 *
 *
 */
public class Player {

    //Create two scanners, one for Strings, and one for numbers - int and float values.

    //Use this scanner to read text data that will be stored in String variables
    static Scanner stringScanner = new Scanner(System.in);
    //Use this scanner to read in numerical data that will be stored in int or double variables
    static Scanner numberScanner = new Scanner(System.in);

    //Create a Random object - this is a random number generator object
    Random random = new Random();

    static String COMPUTER = "C";
    static String HUMAN = "H";
    static String DOWN = "D";
    static String UP = "U";


    private int playerIndex;         // player index/element position in arraylist
    protected String name;        // name
    protected String playerType;  // a player can be human driven or computer driven
    protected Hand hand;        // the player's hand of cards
    protected int score;        // accumulated score for each round
    protected int scoreRound;   // score for hand during round

    // constructor
    public Player(int playerIndex, String name, String playerType) {

        this.playerIndex = playerIndex;
        this.name = name;
        this.playerType = playerType;

    }


    public void playRound(ArrayList<Player> players) {

        System.out.println();
        System.out.println("****** Play a Turn ****** Player " + name);

        //for (Player player : players) {

            printAllPlayerHands(players);

            Deck.printPlayerDrawsFrom();

            drawACard();

            printAllPlayerHands(players);

            Deck.printPlayerDrawsFrom();

        //}

        System.out.println();
        GolfManager.numberOfRoundsPlayed++;
        System.out.println("played test round #" + GolfManager.numberOfRoundsPlayed);
        return;

    }

    private void printAllPlayerHands(ArrayList<Player> players) {

        for (Player player : players) {

            player.hand.printHand(player.name, player.playerType);
            int test = player.hand.scoreUpCards();
            System.out.println("Score for up cards = " + test);

        }


/*      // TODO print up to four players in formatted rows and columns
        System.out.println("Player:     Player:     Player:     Player:");
        System.out.println(players.get(0).name + "      " + players.get(1).name + "     ");
*/


    }

    public void checkCardStatus() {

        GolfManager.allCardsUp = hand.checkCardStatus();

    }

    public void dealCards() {

        hand = new Hand();
        hand.buildNewHand();

        hand.printHand(name, playerType);

    }

    public void turnUpTwo() {

    }

    public void drawACard() {

    }
}
// end class Player
