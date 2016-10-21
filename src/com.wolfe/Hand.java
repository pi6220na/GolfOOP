package com.wolfe;

import java.util.*;

/**
 * Created by Jeremy on 10/20/2016.
 *
 * - builds a player's table of six cards four facing down and two cards face up.
 *
 * - prints out a hand
 *
 */
public class Hand {

    public ArrayList<Card> handArray;

    static int row1Left = 0;
    static int row1Mid = 1;
    static int row1Right = 2;

    static int row2Left = 3;
    static int row2Mid = 4;
    static int row2Right = 5;

    static String DOWN = "D";
    static String UP = "U";


    // constructor
    public Hand() {

        this.handArray = new ArrayList<>(6);

    }


    // at start of game, builds a new hand with dealCount number of cards
    protected void buildNewHand() {

        System.out.println("entering buildNewHand");
        System.out.println("   GolfManager.dealCount = " + GolfManager.dealCount);

        for (int i = 0; i < GolfManager.dealCount; i++) {

            Card card = Deck.getACard();
            // System.out.println(card);
            addCardToArray(card);

        }
    }

    // adds card from Deck to hand
    protected void addCardToArray(Card card) {

        card.setFacing(DOWN);
        handArray.add(card);

    }


    protected boolean checkCardStatus() {

        for (Card card : handArray) {

            if (card.getFacing().equals(DOWN)) {
                System.out.println("checkCardStatus returning false");
                return false;
            }
        }
        System.out.println("checkCardStatus returning true");
        return true;
    }


    protected void printHand(String name, String playerType) {

        System.out.println("entering printHand - ");
        System.out.println("Player: " + name + " Player Type: " + playerType);

        System.out.println("0  1  2");
        if (handArray.get(row1Left).getFacing().equals(UP)) System.out.print(handArray.get(row1Left) + " ");
        else System.out.print("xx ");
        if (handArray.get(row1Mid).getFacing().equals(UP)) System.out.print(handArray.get(row1Mid) + " ");
        else System.out.print("xx ");
        if (handArray.get(row1Right).getFacing().equals(UP)) System.out.println(handArray.get(row1Right) + " ");
        else System.out.println("xx");

        System.out.println("3  4  5");
        if (handArray.get(row2Left).getFacing().equals(UP)) System.out.print(handArray.get(row2Left) + " ");
        else System.out.print("xx ");
        if (handArray.get(row2Mid).getFacing().equals(UP)) System.out.print(handArray.get(row2Mid) + " ");
        else System.out.print("xx ");
        if (handArray.get(row2Right).getFacing().equals(UP)) System.out.println(handArray.get(row2Right) + " ");
        else System.out.println("xx");

    }

    @Override
    public String toString() {
        return "player's hand goes here";
    }



} // end class Hand
