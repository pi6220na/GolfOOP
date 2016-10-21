package com.wolfe;

/**
 * Created by myrlin on 10/20/2016.
 */
public class Human extends Player {

    static String COMPUTER = "C";
    static String HUMAN = "H";


    public Human(int playerIndex, String name, String playerType) {
        super(playerIndex, name, playerType);

    }


    public void turnUpTwo() {

        int turnFirst;
        int turnSecond;

        System.out.println("turnUpTwo method in human sub class");

        System.out.println("Player: Enter first card to turn up: (0 - 5): ");
        turnFirst = GolfManager.stringScanner.nextInt();
        while (turnFirst < 0 || turnFirst > 5) {
            System.out.println("Error: must be position 0 - 5");
            System.out.println("Player: Enter first card to turn up: (0 - 5): ");
            turnFirst = GolfManager.stringScanner.nextInt();
        }


        System.out.println("Player: Enter second card to turn up: (0 - 5): ");
        turnSecond = GolfManager.stringScanner.nextInt();
        while (turnSecond < 0 || turnSecond > 5) {
            System.out.println("Error: must be position 0 - 5");
            System.out.println("Player: Enter first card to turn up: (0 - 5): ");
            turnSecond = GolfManager.stringScanner.nextInt();
        }

        hand.handArray.get(turnFirst).setFacing(UP);
        hand.handArray.get(turnSecond).setFacing(UP);

        hand.printHand(name, playerType);
    }


}
