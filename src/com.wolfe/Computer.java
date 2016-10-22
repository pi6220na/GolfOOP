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

    public void drawACard() {

    }
}
