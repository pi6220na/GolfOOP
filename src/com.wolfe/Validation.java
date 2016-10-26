package com.wolfe;

/*
 * Created by Jeremy on 10/21/2016.
 *
 *  copied from Java2545examples:Validation.java
 *
 */

import java.util.InputMismatchException;

class Validation {

    // A variant of the method below - notice it calls doubleInput with null as the argument
    public static double doubleInput() {
        return doubleInput(null);
    }

    //Takes a question, asks user the question, checks to make sure user enters a double, and
    //then returns that double to the calling method.
    private static double doubleInput(String question) {

        while (true) {
            // If user has provided a question, then print it for the user
            if (question != null) {
                System.out.println(question);
            }

            //Try to read what the user typed, expect it to be a double.
            try {
                // If the input can be read as a double, that double value will be returned
                // This ends the loop, and this method, and control returns to the calling method.
                return GolfManager.numberScanner.nextDouble();

            } // if the input can't be read as a double, then an error will be raised.
            // That error can be 'caught' by this code, and we can print an error message.
            // Since we are inside a while loop, then the loop can repeat and ask the user for input again.
            catch (InputMismatchException ime) {
                System.out.println("Error - please enter a number");
                GolfManager.numberScanner.next();   //Clear any other characters from the Scanner
            }
        }
    }

    // A variant of the method below - notice it calls intInput with null as the argument
    public static int intInput() {
        return intInput(null);
    }

    //Takes a question, asks user the question, checks to make sure user enters an int, and
    //then returns that int to the calling method.
    public static int intInput(String question) {

        while (true) {
            // If user has provided a question, then print it for the user
            if (question != null) {
                System.out.println(question);
            }

            //Try to read what the user typed as an int.
            try {
                // If the input can be read as a int, that int will be returned
                // This ends the loop, and this method, and control returns to the calling method.
                return GolfManager.numberScanner.nextInt();

            } // if the input can't be read as an int, then an error will be raised.
            // For example, if the user enters 'ten' or 1.4 or 123456543454343434, these are not ints, so will cause an error.
            // That error can be 'caught' by this code, and we can print an error message.
            // Since we are inside a while loop, then the loop can repeat and ask the user for input again.
            catch (InputMismatchException ime) {
                System.out.println("Error - please enter an integer number");
                GolfManager.numberScanner.next();   //Clear any other characters from the Scanner
            }
        }

    }
}




