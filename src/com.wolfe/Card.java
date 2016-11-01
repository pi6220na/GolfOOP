package com.wolfe;


/*
 * Created by Jeremy on 10/19/2016.
 *
 * Holds standard playing card information. Deals out one card at a time.
 *
 */
public class Card {

    static final String DOWN = "D";
    static final String UP = "U";

    private int sequence;
    private final String rank;
    private final String suit;
    private String facing;


    // setup constructor
    public Card(int sequence, String rank, String suit){

        this.sequence = sequence;       // 1=Ace... 13=King
        this.rank = rank;               // A, 2, 3,... J, Q, K
        this.suit = suit;               // Hearts=H, Clubs=C, Diamonds=D, Spades=S
        this.facing = DOWN;             // card is facing down to start
    }

    // constructor used by scoring method
    public Card(int sequence, String rank, String suit, String facing){

        this.sequence = sequence;       // 1=Ace... 13=King
        this.rank = rank;               // A, 2, 3,... J, Q, K
        this.suit = suit;               // Hearts=H, Clubs=C, Diamonds=D, Spades=S
        this.facing = facing;           // UP or DOWN
    }

    private static final String HEARTS = "H";
    private static final String CLUBS = "C";
    private static final String DIAMONDS = "D";
    private static final String SPADES = "S";

    // copied text coloring code from Java MCTC Class - works in conjunction with ColorPrinter
    private static final char spadeCh = 9824;  // Unicode characters for playing card symbols
    private static final char clubCh = 9827;
    private static final char heartCh = 9829;
    private static final char diamondCh = 9830;

    private static final String spade = Character.toString(spadeCh);
    private static final String club = Character.toString(clubCh);
    private static final String heart = Character.toString(heartCh);
    private static final String diamond = Character.toString(diamondCh);

    @Override
    public String toString() {

        switch (suit) {
            case HEARTS:
                return ColorPrinter.print(rank + heart, TextCol.RED);
            case DIAMONDS:
                return ColorPrinter.print(rank + diamond, TextCol.RED);
            case CLUBS:
                return ColorPrinter.print(rank + club, TextCol.BLACK);
            case SPADES:
                return ColorPrinter.print(rank + spade, TextCol.BLACK);
        }

        return null;
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


// getters and setters

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
