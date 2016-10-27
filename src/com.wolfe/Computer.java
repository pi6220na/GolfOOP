package com.wolfe;

import java.util.Random;

/*
 * Created by Jeremy on 10/20/2016.
 *
 *
 *  This class contains the logic for the computer to play a turn.
 *
 */

class Computer extends Player {

    //Create a Random object - this is a random number generator object
    private final Random random = new Random();

    // constructor calls super class Player
    Computer(String name) {
        super(name);

    }


    public void turnUpTwo() {

        System.out.println("turnUpTwo method in computer sub class");

        int rand1 = random.nextInt(6);
        System.out.println("Random generator number 0 - 6 = " + rand1);
        int rand2 = random.nextInt(6);
        System.out.println("Random generator number 0 - 6 = " + rand2);

        while (rand1 == rand2) {
            rand2 = random.nextInt(6);
        }

        hand.handArray.get(rand1).setFacing(Card.UP);
        hand.handArray.get(rand2).setFacing(Card.UP);

        hand.printHand(name);

    }

    /*
     *      mark any existing pairs as off-limits
     *
     *      check for 2's and King's when doing below logic groups
     *
     *
     *  Two groups of computer logic: discard processing and draw a card from deck processing
     *  They both do the same groups of logic checks/replacements.
     *
     *  examine discard group
     *
     *      if discard is 2 or K
     *          move to unpaired column or column with highest faceup card and get out
     *
     *      check for pairs, if can make a new pair, do it and get out
     *
     *      if discard is lower than any faceup card, replace highest faceup card and get out
     *
     *   draw from deck group
     *
     *      check for pairs, if can make a new pair(don't replace an existing pair), do it and get out
     *
     *      replace highest faceup card
     *
     *   card column combinations
     *      card up
     *      card dn
     *
     *      card dn
     *      card up
     *
     *      card up
     *      card up
     *
     *      card dn
     *      card dn
     *
     * array elements in 3 columns of 2 rows
     *      0   1   2
     *      3   4   5
     *
     *
      */

    public void drawACard() {


        for (int loop = 1; loop < 3; loop++) {

            Card newCard;

            if (loop == 1) {
                newCard = Deck.discardPile.pop();
                System.out.println("Computer popped a newCard from discardPile size= " + Deck.discardPile.size());
            } else {
                newCard = Deck.getACard();
                System.out.println("Computer got a card from Deck size= " + Deck.deck.size());
            }

            boolean usedCard = doDeckGroupings(newCard, loop); // loop=1:discard, loop=2:deck card

            if (usedCard && loop == 1) {
                System.out.println("Computer used Discard Logic");
                break;
            } else if (usedCard && loop == 2){
                System.out.println("Computer used Deck Logic");
                break;
            }

        }

    }


    //  *************** Deck Logic Driver *******************

    @SuppressWarnings("ConstantConditions")
    private boolean doDeckGroupings(Card newCard, int indicator) { // indicator=1:discard, indicator=2:deck card

        boolean PairFormed = false;
        boolean King2Found = false;
        boolean Lower = false;
        boolean OpenSlot = false;


        //Card newCard = Deck.getACard();
        //System.out.println("Computer drew a card from Deck: " + newCard);

        for (int col=1; col < 4; col++) {

            // perform King/2 card logic
            if (col == 1) {
                King2Found = doCardKing2DeckLogic(0, 3, newCard, indicator);
            } else if (col == 2) {
                King2Found = doCardKing2DeckLogic(1, 4, newCard, indicator);
            } else if (col == 3) {
                King2Found = doCardKing2DeckLogic(2, 5, newCard, indicator);
            }

            if (King2Found) {
                if (indicator == 1) {
                    System.out.println("Computer used discard card of Ace, 2 or King");
                } else {
                    System.out.println("Computer used deck card of Ace, 2 or King");
                }
                //break;
                return King2Found;
            }


            // perform pairs of cards logic
            if (col == 1) {
                PairFormed = doCardPairDeckLogic(0, 3, newCard, indicator);
            } else if (col == 2) {
                PairFormed = doCardPairDeckLogic(1, 4, newCard, indicator);
            } else if (col == 3) {
                PairFormed = doCardPairDeckLogic(2, 5, newCard, indicator);
            }

            if (PairFormed) {
                if (indicator == 1) {
                    System.out.println("Computer made a pair from discard");
                } else {
                    System.out.println("Computer made a pair from deck card");
                }
                //break;
                return PairFormed;
            }

            // check if discard value lower than any showing UP card, if yes, replace with discard
            if (col == 1) {
                Lower = doDeckLogic(0, 3, newCard, indicator);
            } else if (col == 2) {
                Lower = doDeckLogic(1, 4, newCard, indicator);
            } else if (col == 3) {
                Lower = doDeckLogic(2, 5, newCard, indicator);
            }

            if (Lower) {
                if (indicator == 1) {
                    System.out.println("Computer swapped out discard because it was lower value");
                    System.out.println("   than card it replaced");
                } else {
                    System.out.println("Computer swapped out deck card because it was lower value");
                    System.out.println("   than card it replaced");
                }
                //break;
                return Lower;
            }

            /*
            // check if drawn card value lower than any showing UP card, if yes, replace with drawn card
            if (col == 1) {
                OpenSlot = doDeckOpenSlotLogic(0, 3, newCard, indicator);
            } else if (col == 2) {
                OpenSlot = doDeckOpenSlotLogic(1, 4, newCard, indicator);
            } else if (col == 3) {
                OpenSlot = doDeckOpenSlotLogic(2, 5, newCard, indicator);
            }

            if (OpenSlot) {
                if (indicator == 1) {
                    System.out.println("Computer swapped out discard because there was an available slot");
                } else {
                    System.out.println("Computer swapped out deck card because there was an available slot");
                }
                //break;
                return OpenSlot;
            }
           */

        }

        //if (!King2Found && !PairFormed && !Lower && !OpenSlot && indicator != 1) { // this line caused a subtle bug
        //                                                                           // of not populating discardPile
            System.out.println("Computer pushing newCard to disCardPile: " + newCard);
            Deck.discardPile.push(newCard);
            System.out.println("Computer moved newCard to discard pile because all four booleans false");
            System.out.println("   peeking at discard pile: " + Deck.discardPile.peek());

        //}

        return false;
    }


    //  *************** Deck Card Logic Section *******************
    //
    //  first time through, indicator = 1, processing discard
    //  second time through, indicator = 2, processing deck card


    private boolean doCardKing2DeckLogic(int row1Card, int row2Card, Card newCard, int indicator) {

        // if deck card is a King or a 2 or an Ace... keep and find a home for it
        if (newCard.getSequence() != 1 && newCard.getSequence() != 13
                && newCard.getSequence() != 2) {

            return false;

        }

        //
        if (hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN))) {

            // TODO check for pair before doing this
            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, newCard);
            Deck.discardPile.push(returnedCard);
            return true;

         }


        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.UP))) {

            // TODO check for pair before doing this
            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, newCard);
            Deck.discardPile.push(returnedCard);
            return true;

        }

        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN))) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, newCard);
            Deck.discardPile.push(returnedCard);
            return true;

        }

        // if both cards UP, skip over this column

        return false;
    }


    private boolean doCardPairDeckLogic(int row1Card, int row2Card, Card newCard, int indicator) {

        // matching pair showing, don't process this pair, leave it alone
        if ((hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.UP)))
                &&
                (hand.handArray.get(row1Card).getSequence() == hand.handArray.get(row2Card).getSequence())) {

            return false;
        }

        // make a pair with row1 and discard?
        if (hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN))) {

            if (newCard.getSequence() ==
                    hand.handArray.get(row1Card).getSequence()) {

                // method over-loaded
                Card returnedCard = hand.swapCard(row2Card, newCard);
                Deck.discardPile.push(returnedCard);
                return true;
            }

        }

        // make a pair with row2 and discard?
        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.UP))) {

            if (newCard.getSequence() ==
                    hand.handArray.get(row2Card).getSequence()) {

                // method over-loaded
                Card returnedCard = hand.swapCard(row1Card, newCard);
                Deck.discardPile.push(returnedCard);
                return true;
            }

        }

        // if both cards in hand are facing down, we can't use to make a pair, no need to check anything
        return false;
    }


    private boolean doDeckLogic(int row1Card, int row2Card, Card newCard, int indicator) {

        // don't care about DOWN cards for this method (can't check against discard, unless we cheat :) )

        int highCard = getHighCardValue();

        // if hand card sequence < discard sequence, replace hand card
        if (hand.handArray.get(row1Card).getSequence() == highCard &&
                hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                newCard.getSequence() < highCard) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, newCard);
            Deck.discardPile.push(returnedCard);

            return true;
        }

        // if hand card sequence < discard sequence, replace hand card
        if (hand.handArray.get(row2Card).getSequence() == highCard &&
                hand.handArray.get(row2Card).getFacing().equals(Card.UP) &&
                newCard.getSequence() < highCard) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, newCard);
            Deck.discardPile.push(returnedCard);

            return true;
        }


        return false;
    }

    private boolean doDeckOpenSlotLogic(int row1Card, int row2Card, Card newCard, int indicator) {

        // don't care what the drawn card is at this point, replace any available open slot with it
        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN)) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, newCard);
            returnedCard.setFacing(Card.UP);
            Deck.discardPile.push(returnedCard);

            return true;

        }

        // don't care what the drawn card is at this point, replace any available open slot with it
        if (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN)) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, newCard);
            returnedCard.setFacing(Card.UP);
            Deck.discardPile.push(returnedCard);

            return true;

        }

        return false;
    }

    // find the highest valued card that is not a King (13)
    private int getHighCardValue() {

        int maxCard = 0;

        for (Card card : hand.handArray) {

            // don't replace a King
            if (card.getFacing().equals(Card.UP) && card.getSequence() > maxCard
                    && card.getSequence() != 13) {
                maxCard = card.getSequence();
            }

        }

        return maxCard;
    }




} // end Class Computer
