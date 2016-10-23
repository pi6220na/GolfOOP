/**
 * Created by Jeremy on 10/19/2016
 *
 * Copied and modified from GoFishOOP
 *
 *
 *
 *
 * x Classes in this program:
 *
 *
 *
 *
 */
package com.wolfe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class GolfManager {

    //Create two scanners, one for Strings, and one for numbers - int and float values.
    //Use this scanner to read text data that will be stored in String variables
    static Scanner stringScanner = new Scanner(System.in);
    //Use this scanner to read in numerical data that will be stored in int or double variables
    static Scanner numberScanner = new Scanner(System.in);

    public static int dealCount = 6;  // number of cards to deal to a player's table
    public static int numberOfPlayers;    // number of players in this game
    public static boolean allCardsUp = false;   // end of round flag
    public static int numberOfRoundsPlayed = 0;     // game over when 9 rounds played

    static String COMPUTER = "C";
    static String HUMAN = "H";

    static LinkedList<Integer> playerPlaysQueue = new LinkedList<>();   // circular index list of players
    static ArrayList<Player> players = new ArrayList<>();               // list of player (superclass) objects
    static int currentPlayerID;                                         // player currently active

    public static void main(String[] args) {

        setupPlayers();

        // outer loop deals six down cards to each player
        do {

            Deck.buildDeck();
            Deck.shuffleDeck();
            dealCardsToPlayers();

            // inner play loop. Pulls a player from front of queue and puts into play.
            // Returns player to back of queue at end of turn.
            // round continues until a player has turned up all six table cards.
            System.out.println("****** Start of Round ******");
            do {

                int playerIndex = playerPlaysQueue.pop(); // get next player from queue
                currentPlayerID = playerIndex;
                Player currentPlayer = players.get(playerIndex);

                currentPlayer.playRound(players);

                currentPlayer.checkCardStatus();

                playerPlaysQueue.add(playerIndex); // put player back to end of queue


//                allCardsUp = true;  // development only code:

            } while (!allCardsUp);


            System.out.println();
            GolfManager.numberOfRoundsPlayed++;
            System.out.println("played test round #" + GolfManager.numberOfRoundsPlayed);


        } while (numberOfRoundsPlayed < 2);


        System.out.println();
        System.out.println("************* G A M E   O V E R ***************");
        for (Player player : players) {
            System.out.println("something here...");
        }

        stringScanner.close();
        numberScanner.close();

    } // end class main


    private static void dealCardsToPlayers() {

        System.out.println();
        System.out.println("******  Set Up Start of Round  ******");
        for (Player player : players) {

            player.dealCards();

            player.turnUpTwo();

        }

        Card discard = Deck.getACard();
        Deck.discardPile.push(discard);

    }


    // sets up game and players TODO needs input validations
    protected static void setupPlayers() {

        String morePlayers = "y";
        String name;
        String playerType;
        int pCount;
        int playerIndex = 0;

        numberOfPlayers = Validation.intInput("Enter the number of players (2 thru 4): ");
        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.println("Input error, wrong number of players.");
            pCount = Validation.intInput("Enter the number of players (2 thru 4): ");
        }

        do {

            System.out.println("Enter Player Name: ");
            name = stringScanner.nextLine();

            System.out.println("Enter Player Type (C for computer, H for human): ");
            playerType = stringScanner.nextLine();
            while (!playerType.equals("C") && !playerType.equals("H")) {
                System.out.println("Error: player type must be C or H");
                System.out.println("Enter Player Type (C for computer, H for human): ");
                playerType = stringScanner.nextLine();
            }

            if (playerType.equals(HUMAN)) {
                Human human = new Human(playerIndex, name, HUMAN);
                players.add(playerIndex, human);       // add player object to arraylist
            }
            if (playerType.equals(COMPUTER)) {
                Computer computer = new Computer(playerIndex, name, COMPUTER);
                players.add(playerIndex, computer);       // add player object to arraylist
            }

            playerPlaysQueue.add(playerIndex);      // index(ID) of player added to round robin queue
            playerIndex++;

            System.out.println("Enter more players? (y or n):");
            morePlayers = stringScanner.nextLine();

        } while (morePlayers.equalsIgnoreCase("y") && playerIndex < 4);

    }

} //end class GoFishMgr
