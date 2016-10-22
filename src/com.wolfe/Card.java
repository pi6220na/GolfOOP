package com.wolfe;


/**
 * Created by myrlin on 10/19/2016.
 */
public class Card {

    static String DOWN = "D";
    static String UP = "U";

    private int sequence;
    private String rank;
    private String suit;
    private String facing;


    public Card(int sequence, String rank, String suit){

        this.sequence = sequence;       // 1=Ace... 13=King
        this.rank = rank;               // A, 2, 3,... J, Q, K
        this.suit = suit;               // Hearts=H, Clubs=C, Diamonds=D, Spades=S
        this.facing = DOWN;             // card is facing down to start
    }

    public Card(int sequence, String rank, String suit, String facing){

        this.sequence = sequence;       // 1=Ace... 13=King
        this.rank = rank;               // A, 2, 3,... J, Q, K
        this.suit = suit;               // Hearts=H, Clubs=C, Diamonds=D, Spades=S
        this.facing = facing;             // card is facing down to start
    }








    static String HEARTS = "H";
    static String CLUBS = "C";
    static String DIAMONDS = "D";
    static String SPADES = "S";

    static char spadeCh = 9824;  // Unicode characters for playing card symbols
    static char clubCh = 9827;
    static char heartCh = 9829;
    static char diamondCh = 9830;

    static String spade = Character.toString(spadeCh);
    static String club = Character.toString(clubCh);
    static String heart = Character.toString(heartCh);
    static String diamond = Character.toString(diamondCh);

    @Override
    public String toString() {

        if (suit.equals(HEARTS)) {
            return ColorPrinter.print(rank + heart, TextCol.RED);
        } else if (suit.equals(DIAMONDS)) {
            return ColorPrinter.print(rank + diamond, TextCol.RED);
        } else if (suit.equals(CLUBS)) {
            return ColorPrinter.print(rank + club, TextCol.BLACK);
        }

        return ColorPrinter.print(rank + spade, TextCol.BLACK);
    }


//    @Override
//    public String toString() {
//        return sequence + ":" + rank +  " of " + suit;
//    }


    static String convertSeqToRank(int i) {

        switch (i) {

            case 1: {
                return "A";
            }
            case 2: {
                return "2";
            }
            case 3: {
                return "3";
            }
            case 4: {
                return "4";
            }
            case 5: {
                return "5";
            }
            case 6: {
                return "6";
            }
            case 7: {
                return "7";
            }
            case 8: {
                return "8";
            }
            case 9: {
                return "9";
            }
            case 10: {
                return "10";
            }
            case 11: {
                return "J";
            }
            case 12: {
                return "Q";
            }
            case 13: {
                return "K";
            }
        }
        return null;  // should never be reached
    }


    protected static int convertRankToSeq(String i) {

        switch (i) {

            case "A": {
                return 1;
            }
            case "2": {
                return 2;
            }
            case "3": {
                return 3;
            }
            case "4": {
                return 4;
            }
            case "5": {
                return 5;
            }
            case "6": {
                return 6;
            }
            case "7": {
                return 7;
            }
            case "8": {
                return 8;
            }
            case "9": {
                return 9;
            }
            case "10": {
                return 10;
            }
            case "J": {
                return 11;
            }
            case "Q": {
                return 12;
            }
            case "K": {
                return 13;
            }
        }
        return 0;  // should never be reached
    }


// getters and one needed setter

    public int getSequence() {
        return sequence;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getFacing() { return facing; }

    public void setFacing(String facing) { this.facing = facing; }

    public void setSequence(int sequence)  { this.sequence = sequence; }

}
