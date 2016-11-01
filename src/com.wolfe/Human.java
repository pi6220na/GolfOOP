package com.wolfe;

/*
 * Created by Jeremy on 10/20/2016.
 *
 * Gets input data from Human player and plays the cards based on the input.
 *
 */
public class Human extends Player {

    // constructor calls super class Player
    public Human(String name) {
        super(name);

    }

    // new deal setup at the start of each round, turns up two of six card facing down
    public void turnUpTwo() {

        int turnFirst;
        int turnSecond;

        System.out.println("turnUpTwo method in human sub class");

        System.out.println("Player: Enter first card to turn up: (0 - 5): ");
        turnFirst = GolfManager.numberScanner.nextInt();
        while (turnFirst < 0 || turnFirst > 5) {
            System.out.println("Error: must be position 0 - 5");
            System.out.println("Player: Enter first card to turn up: (0 - 5): ");
            turnFirst = GolfManager.numberScanner.nextInt();
        }


        System.out.println("Player: Enter second card to turn up: (0 - 5): ");
        turnSecond = GolfManager.numberScanner.nextInt();
        while (turnSecond < 0 || turnSecond > 5) {
            System.out.println("Error: must be position 0 - 5");
            System.out.println("Player: Enter first card to turn up: (0 - 5): ");
            turnSecond = GolfManager.numberScanner.nextInt();
        }

        hand.handArray.get(turnFirst).setFacing(Card.UP);
        hand.handArray.get(turnSecond).setFacing(Card.UP);

        hand.printHand(name);
    }

    // asks user what they want to do and swaps out card(s) as needed
    public void drawACard() {

        System.out.println("Entering drawACard in human class");

        Card drawnCard = null;

        System.out.println("Draw a card from Deck (x) or Discard Pile (d)");
        String choice = stringScanner.nextLine();
        while (!choice.equalsIgnoreCase("x") && !choice.equalsIgnoreCase("d")) {
            System.out.println("Draw a card from Deck (x) or Discard Pile (d)");
            choice = stringScanner.nextLine();
        }

        if (choice.equalsIgnoreCase("x")) {
            drawnCard = Deck.getACard();
            System.out.println("Card drawn from Deck: " + drawnCard);
        }


        if (choice.equalsIgnoreCase("d")) {
            drawnCard = Deck.discardPile.pop();
            System.out.println("Card drawn from Discard: " + drawnCard);
        }

        System.out.println("Place choice? table(0-5) or Discard(d)");
        choice = stringScanner.nextLine();
        // add edit for choice input

        System.out.println("Choice taken: " + choice);
        swapOutCard(choice, drawnCard);

    }

    // swaps out user's choice from discard or deck with a card on the table (player hand)
    private void swapOutCard(String choice, Card drawnCard) {

        if (choice.equals("0") || choice.equals("1") || choice.equals("2") || choice.equals("3")
            || choice.equals("4") || choice.equals("5")) {

            Card returnedCard = hand.swapCard(choice, drawnCard);

            Deck.discardPile.push(returnedCard);
            System.out.println("Human added swap card to discard pile");
        }

        if (choice.equalsIgnoreCase("d")) {
            Deck.discardPile.push(drawnCard);
            System.out.println("Human added discard or deck card to discard pile");
        }

    }

}
