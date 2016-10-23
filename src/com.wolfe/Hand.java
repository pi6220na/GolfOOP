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


    protected Card swapCard(String choice, Card drawnCard) {

        int location = Integer.parseInt(choice);
        System.out.println("swapCard: location = " + location);
        System.out.println("swapCard: drawnCard = " + drawnCard);


        Card retCard = handArray.get(location);

        drawnCard.setFacing(UP);
        handArray.set(location, drawnCard);



        System.out.println("swapCard: retCard = " + retCard);
        return retCard;
    }





    // during game play, show partial score of up cards. this method determines the up cards score.
    int scoreUpCards() {

        ArrayList<Card> workArray = new ArrayList<Card>();

        for (Card card : handArray) {

            Card newcard = new Card(card.getSequence(), card.getRank(),
                    card.getSuit(), card.getFacing());

            workArray.add(newcard);

        }

/*
        System.out.println("handArray");
        for (Card card : handArray) {

            System.out.println(card.getSequence());
            System.out.println(card.getRank());
            System.out.println(card.getSuit());
            System.out.println(card.getFacing());
            System.out.println();

        }

        System.out.println("workArray");
        for (Card card : workArray) {

            System.out.println(card.getSequence());
            System.out.println(card.getRank());
            System.out.println(card.getSuit());
            System.out.println(card.getFacing());
            System.out.println();

        }
*/

        // if card is facing down, set it to 0 ignore it
        for (Card card : workArray) {

            //System.out.println("test b4: card seq = " + card.getSequence());
            //System.out.println(" card facing: " + card.getFacing());

            if (card.getFacing().equals(DOWN)) {
                card.setSequence(0);
            }

            //System.out.println("test after: card seq = " + card.getSequence());
            //System.out.println(" card facing: " + card.getFacing());

        }

        // set pairs to zero value
        if (workArray.get(row1Left).getSequence() == workArray.get(row2Left).getSequence()) {
            workArray.get(row1Left).setSequence(0);
            workArray.get(row2Left).setSequence(0);
        }
        if (workArray.get(row1Mid).getSequence() == workArray.get(row2Mid).getSequence()) {
            workArray.get(row1Mid).setSequence(0);
            workArray.get(row2Mid).setSequence(0);
        }
        if (workArray.get(row1Right).getSequence() == workArray.get(row2Right).getSequence()) {
            workArray.get(row1Right).setSequence(0);
            workArray.get(row2Right).setSequence(0);
        }

        // now scan each card and sum up values
        int score = 0;
        for (Card card : workArray) {
            int workCard = card.getSequence();
            score += calcScore(workCard);
            //System.out.println("test: workCard = " + workCard);
            //System.out.println("test: score = " + score);
        }

        return score;
    }

    private int calcScore(int workCard) {

        int score = 0;

        switch (workCard) {
            case 0:
                score = 0;
                break;
            case 1:
                score = 1;
                break;
            case 2:
                score = -2;
                break;
            case 3:
                score = 3;
                break;
            case 4:
                score = 4;
                break;
            case 5:
                score = 5;
                break;
            case 6:
                score = 6;
                break;
            case 7:
                score = 7;
                break;
            case 8:
                score = 8;
                break;
            case 9:
                score = 9;
                break;
            case 10:
                score = 10;
                break;
            case 11:
                score = 10;
                break;
            case 12:
                score = 10;
                break;
            case 13:
                score = 0;
                break;
            default:
                System.out.println("error in scoring routine");
        }

        return score;
    }


    protected void printHand(String name, String playerType) {

        System.out.println();
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
