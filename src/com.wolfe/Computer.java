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

        boolean usedDiscard = doDiscardGroupings();

        if (usedDiscard) {
            System.out.println("Computer used discard logic");
            return;
        } else {
            System.out.println("Computer hit draw card from Deck logic.........");
        }


        boolean usedDeckCard = doDeckGroupings();

        if (usedDeckCard) {
            System.out.println("Computer used Deck Logic");
        }

    }




    //  *************** Discard Logic Driver *******************

    @SuppressWarnings("ConstantConditions")   // added by IntelliJ during Analyze>Inspect Code function
    private boolean doDiscardGroupings() {

        boolean pairFormed = false;
        boolean king2Found = false;
        boolean discardLower = false;
        boolean discardOpenSlot = false;

        for (int col=1; col < 4; col++) {

            // perform King/2 card logic
            if (col == 1) {
                king2Found = doCardKing2DiscardLogic(0, 3);
            } else if (col == 2) {
                king2Found = doCardKing2DiscardLogic(1, 4);
            } else if (col == 3) {
                king2Found = doCardKing2DiscardLogic(2, 5);
            }

            if (king2Found) {
                System.out.println("Computer used discard of 2 or King");
                //break;
                return king2Found;
            }


            // perform pairs of cards logic
            if (col == 1) {
                pairFormed = doCardPairDiscardLogic(0, 3);
            } else if (col == 2) {
                pairFormed = doCardPairDiscardLogic(1, 4);
            } else if (col == 3) {
                pairFormed = doCardPairDiscardLogic(2, 5);
            }

            if (pairFormed) {
                System.out.println("Computer made a pair from discard");
                //break;
                return pairFormed;
            }

            // check if discard value lower than any showing UP card, if yes, replace with discard
            if (col == 1) {
                discardLower = doDiscardLogic(0, 3);
            } else if (col == 2) {
                discardLower = doDiscardLogic(1, 4);
            } else if (col == 3) {
                discardLower = doDiscardLogic(2, 5);
            }

            if (discardLower) {
                System.out.println("Computer swapped out discard because it was lower value");
                System.out.println("   than card it replaced");
                // break;
                return discardLower;
            }

            // find an open slot and put discard there
            if (col == 1) {
                discardOpenSlot = doDiscardOpenSlotLogic(0, 3);
            } else if (col == 2) {
                discardOpenSlot = doDiscardOpenSlotLogic(1, 4);
            } else if (col == 3) {
                discardOpenSlot = doDiscardOpenSlotLogic(2, 5);
            }

            if (discardOpenSlot) {
                System.out.println("Computer swapped out discard because it was an available open slot");
                // break;
                return discardOpenSlot;
            }

        }

        return false;

    }


    //  *************** Deck Logic Driver *******************

    @SuppressWarnings("ConstantConditions")
    private boolean doDeckGroupings() {

        boolean deckPairFormed = false;
        boolean deckKing2Found = false;
        boolean deckLower = false;
        boolean deckOpenSlot = false;


        Card newCard = Deck.getACard();
        System.out.println("Computer drew a card from Deck: " + newCard);

        for (int col=1; col < 4; col++) {

            // perform King/2 card logic
            if (col == 1) {
                deckKing2Found = doCardKing2DeckLogic(0, 3, newCard);
            } else if (col == 2) {
                deckKing2Found = doCardKing2DeckLogic(1, 4, newCard);
            } else if (col == 3) {
                deckKing2Found = doCardKing2DeckLogic(2, 5, newCard);
            }

            if (deckKing2Found) {
                System.out.println("Computer used deck card of 2 or King");
                //break;
                return deckKing2Found;
            }


            // perform pairs of cards logic
            if (col == 1) {
                deckPairFormed = doCardPairDeckLogic(0, 3, newCard);
            } else if (col == 2) {
                deckPairFormed = doCardPairDeckLogic(1, 4, newCard);
            } else if (col == 3) {
                deckPairFormed = doCardPairDeckLogic(2, 5, newCard);
            }

            if (deckPairFormed) {
                System.out.println("Computer made a pair from deck card");
                //break;
                return deckPairFormed;
            }

            // check if discard value lower than any showing UP card, if yes, replace with discard
            if (col == 1) {
                deckLower = doDeckLogic(0, 3, newCard);
            } else if (col == 2) {
                deckLower = doDeckLogic(1, 4, newCard);
            } else if (col == 3) {
                deckLower = doDeckLogic(2, 5, newCard);
            }

            if (deckLower) {
                System.out.println("Computer swapped out deck card because it was lower value");
                System.out.println("   than card it replaced");
                //break;
                return deckLower;
            }

            // check if drawn card value lower than any showing UP card, if yes, replace with drawn card
            if (col == 1) {
                deckOpenSlot = doDeckOpenSlotLogic(0, 3, newCard);
            } else if (col == 2) {
                deckOpenSlot = doDeckOpenSlotLogic(1, 4, newCard);
            } else if (col == 3) {
                deckOpenSlot = doDeckOpenSlotLogic(2, 5, newCard);
            }

            if (deckOpenSlot) {
                System.out.println("Computer swapped out deck card because it was an available slot");
                //break;
                return deckOpenSlot;
            }

        }

        if (!deckKing2Found && !deckPairFormed && !deckLower && !deckOpenSlot) {
            System.out.println("Computer pushing newCard to disCardPile: " + newCard);
            Deck.discardPile.push(newCard);
            System.out.println("Computer moved Deck Card to discard pile because all four booleans false");
            System.out.println("peeking at discard pile: " + Deck.discardPile.peek());

        }

        return false;
    }



    //  *************** Discard Logic Section *******************

   private boolean doCardKing2DiscardLogic(int row1Card, int row2Card) {

        // if discard is a King or a 2... keep and find a home for it
        if (Deck.discardPile.peek().getSequence() != 1 && Deck.discardPile.peek().getSequence() != 13) {

            return false;

        }

        if (hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN))) {

            // TODO check for pair before doing this
            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, Deck.discardPile.pop());
            Deck.discardPile.push(returnedCard);
            return true;

        }

        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.UP))) {

            // TODO check for pair before doing this
            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, Deck.discardPile.pop());
            Deck.discardPile.push(returnedCard);
            return true;

        }

        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN))) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, Deck.discardPile.pop());
            Deck.discardPile.push(returnedCard);
            return true;

        }


        // if both cards UP, skip over this column


        return false;
    }


   private boolean doCardPairDiscardLogic(int row1Card, int row2Card) {

        // matching pair showing, ignore
        if ((hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.UP)))
                &&
                (hand.handArray.get(row1Card).getSequence() == hand.handArray.get(row2Card).getSequence())) {

            return false;
        }

        // make a pair with row1 and discard?
        if (hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN))) {

            if (Deck.discardPile.peek().getSequence() ==
                    hand.handArray.get(row1Card).getSequence()) {

                // method over-loaded
                Card returnedCard = hand.swapCard(row2Card, Deck.discardPile.pop());
                Deck.discardPile.push(returnedCard);
                return true;
            }

        }

        // make a pair with row2 and discard?
        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN) &&
                (hand.handArray.get(row2Card).getFacing().equals(Card.UP))) {

            if (Deck.discardPile.peek().getSequence() ==
                    hand.handArray.get(row2Card).getSequence()) {

                // method over-loaded
                Card returnedCard = hand.swapCard(row1Card, Deck.discardPile.pop());
                Deck.discardPile.push(returnedCard);
                return true;
            }

        }

        // if both cards in hand are facing down, we can't use to make a pair, no need to check anything
        return false;

    }


   private boolean doDiscardLogic(int row1Card, int row2Card) {

        // don't care about DOWN cards for this method (can't check against discard, unless we cheat :) )

       int highCard = getHighCardValue();

        // if hand card sequence < discard sequence, replace hand card
        if (hand.handArray.get(row1Card).getSequence() == highCard &&
                hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                Deck.discardPile.peek().getSequence() < highCard) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, Deck.discardPile.pop());
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added swap card to discard pile");

            return true;
        }

        // if hand card sequence < discard sequence, replace hand card
       if (hand.handArray.get(row2Card).getSequence() == highCard &&
               hand.handArray.get(row2Card).getFacing().equals(Card.UP) &&
               Deck.discardPile.peek().getSequence() < highCard) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, Deck.discardPile.pop());
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added swap card to discard pile");

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


    private boolean doDiscardOpenSlotLogic(int row1Card, int row2Card) {

        // don't care what the discard is at this point, replace any available open slot with it
        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN)) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, Deck.discardPile.pop());
            returnedCard.setFacing(Card.UP);
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added available open r1 slot card to discard pile");

            return true;

        }
        // don't care what the discard is at this point, replace any available open slot with it
        if (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN)) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, Deck.discardPile.pop());
            returnedCard.setFacing(Card.UP);
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added available open r2 slot card to discard pile");

            return true;

        }

        return false;
    }



    //  *************** Deck Card Logic Section *******************


    private boolean doCardKing2DeckLogic(int row1Card, int row2Card, Card newCard) {

        // if deck card is a King or a 2... keep and find a home for it
        if (newCard.getSequence() != 1 && newCard.getSequence() != 13) {

            return false;

        }

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


    private boolean doCardPairDeckLogic(int row1Card, int row2Card, Card newCard) {

        // matching pair showing, ignore
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


    private boolean doDeckLogic(int row1Card, int row2Card, Card newCard) {

        // don't care about DOWN cards for this method (can't check against discard, unless we cheat :) )


        int highCard = getHighCardValue();

        // if hand card sequence < discard sequence, replace hand card
        if (hand.handArray.get(row1Card).getSequence() == highCard &&
                hand.handArray.get(row1Card).getFacing().equals(Card.UP) &&
                Deck.discardPile.peek().getSequence() < highCard) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, newCard);
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added swap card to discard pile");

            return true;
        }

        // if hand card sequence < discard sequence, replace hand card
        if (hand.handArray.get(row2Card).getSequence() == highCard &&
                hand.handArray.get(row2Card).getFacing().equals(Card.UP) &&
                Deck.discardPile.peek().getSequence() < highCard) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, newCard);
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added swap card to discard pile");

            return true;
        }


        return false;
    }

    private boolean doDeckOpenSlotLogic(int row1Card, int row2Card, Card newCard) {

        // don't care what the drawn card is at this point, replace any available open slot with it
        if (hand.handArray.get(row1Card).getFacing().equals(Card.DOWN)) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row1Card, newCard);
            returnedCard.setFacing(Card.UP);
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added Deck card to available open r1 slot card to discard pile");

            return true;

        }

        // don't care what the drawn card is at this point, replace any available open slot with it
        if (hand.handArray.get(row2Card).getFacing().equals(Card.DOWN)) {

            // method over-loaded
            Card returnedCard = hand.swapCard(row2Card, newCard);
            returnedCard.setFacing(Card.UP);
            Deck.discardPile.push(returnedCard);
            System.out.println("Computer added Deck card to available open r2 slot card to discard pile");

            return true;

        }

        return false;
    }


} // end Class Computer
