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
    private static final Scanner stringScanner = new Scanner(System.in);
    //Use this scanner to read in numerical data that will be stored in int or double variables
    static final Scanner numberScanner = new Scanner(System.in);

    static final int dealCount = 6;  // number of cards to deal to a player's table
    static boolean allCardsUp = false;   // end of round flag
    private static int numberOfRoundsPlayed = 0;     // game over when 9 rounds played

    private static final LinkedList<Integer> playerPlaysQueue = new LinkedList<>();   // circular index list of players
    private static final ArrayList<Player> players = new ArrayList<>();               // list of player (superclass) objects

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
                Player currentPlayer = players.get(playerIndex);

                currentPlayer.playRound(players);

                playerPlaysQueue.add(playerIndex); // put player back to end of queue


            } while (!allCardsUp);


            System.out.println();
            GolfManager.numberOfRoundsPlayed++;
            System.out.println("played test round #" + GolfManager.numberOfRoundsPlayed);
            for (Player player : players) {
                System.out.println("Player: " + player.name + " round score: " + player.scoreRound);
                System.out.println("Player: " + player.name + " total score: " + player.score);
            }


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

            player.scoreRound = 0;

        }

        Deck.discardPile.clear();
        Card discard = Deck.getACard();
        Deck.discardPile.push(discard);
        System.out.println("GolfManager added card to discard pile");

    }


    // sets up game and players
    private static void setupPlayers() {

        String name;
        String playerType;
        int pCount;
        int loopCount = 0;
        int playerIndex = 0;

        int numberOfPlayers = Validation.intInput("Enter the number of players (2 thru 4): ");
        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.println("Input error, wrong number of players.");
            numberOfPlayers = Validation.intInput("Enter the number of players (2 thru 4): ");
        }

        pCount = numberOfPlayers;

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

            if (playerType.equals(Player.HUMAN)) {
                Human human = new Human(name);
                players.add(playerIndex, human);       // add player object to arraylist
            }
            if (playerType.equals(Player.COMPUTER)) {
                Computer computer = new Computer(name);
                players.add(playerIndex, computer);       // add player object to arraylist
            }

            playerPlaysQueue.add(playerIndex);      // index(ID) of player added to round robin queue
            playerIndex++;

            loopCount++;

        } while (playerIndex < 4 && pCount > loopCount);

    }

} //end class GoFishMgr
