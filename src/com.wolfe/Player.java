package com.wolfe;

import java.util.*;

import static com.wolfe.GolfManager.players;


/**
 * Created by Jeremy on 10/20/2016.
 *
 * A Player can be either Human or Computer depending on how it's extended
 *
 * This class will:
 *      - get player name and type of player
 *      - ask another player for a card
 *      - check for requested matching card
 *      - if no matching card, draw a card from pool
 *      - accept matching card(s) and put in hand
 *      - check for books at end of each round
 *      - take another turn if appropriate
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

    // constructor
    public Player(int playerIndex, String name, String playerType) {

        this.playerIndex = playerIndex;
        this.name = name;
        this.playerType = playerType;

    }


    public void playRound(ArrayList<Player> players) {

        System.out.println();
        System.out.println("****** Play a Round ******");

        for (Player player : players) {

            printAllPlayerHands(players);
        }

        System.out.println();
        GolfManager.numberOfRoundsPlayed++;
        System.out.println("played test round #" + GolfManager.numberOfRoundsPlayed);
        return;

    }

    private void printAllPlayerHands(ArrayList<Player> players) {

        for (Player player : players) {

            player.hand.printHand(player.name, player.playerType);

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

        //hand.handArray.get(0).setFacing(UP);
        //System.out.println(hand.handArray.get(0).getFacing());
        //hand.handArray.get(1).setFacing(UP);
        //System.out.println(hand.handArray.get(1).getFacing());
        //hand.handArray.get(2).setFacing(UP);
        //System.out.println(hand.handArray.get(2).getFacing());
        //hand.handArray.get(3).setFacing(UP);
        //System.out.println(hand.handArray.get(3).getFacing());
        //hand.handArray.get(4).setFacing(UP);
        //System.out.println(hand.handArray.get(4).getFacing());
        //hand.handArray.get(5).setFacing(UP);
        //System.out.println(hand.handArray.get(5).getFacing());

        hand.printHand(name, playerType);

    }

    public void turnUpTwo() {


    }


}
// end class Player
