package com.wolfe;

import java.util.Random;

/**
 * Created by Jeremy on 10/20/2016.
 *
 *
 *
 */

public class Computer extends Player {

    //Create a Random object - this is a random number generator object
    Random random = new Random();


    public Computer(int playerIndex, String name, String playerType) {
        super(playerIndex, name, playerType);

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

        hand.handArray.get(rand1).setFacing(UP);
        hand.handArray.get(rand2).setFacing(UP);

        hand.printHand(name, playerType);

    }

    /**
     *  make a copy of handArray and mark off limit cards?
     *  then if swapping out a card from hand, use handArray (not the copy)
     *
     *      mark any existing pairs as off-limits
     *          boolean - col1No, col2No, col3No ???
     *
     *      mark any 2 or K as off-limits? or check for them when doing below items
     *
     *
     *  examine discard
     *
     *      if discard is 2 or K
     *          move to unpaired column or column with highest faceup card and get out
     *
     *      check for pairs, if can make a new pair, do it and get out
     *
     *      if discard is lower than any faceup card, replace highest faceup card and get out
     *
     *   draw from deck
     *
     *      check for pairs, if can make a new pair(don't replace an existing pair), do it and get out
     *
     *      replace highest faceup card
     *
     *
      */

    public void drawACard() {

    }
}
