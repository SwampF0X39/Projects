import java.util.Scanner;
import java.util.Random;

/**
 * MASTERMIND GAME By: SwampF0X39
 */
public class Mastermind {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in); //Scanner obj input created and allocated
        String response; //String response declared
        String msgGoodBye = "Goodbye"; //String msgGoodBye declared and initialized to "Goodbye"
        assignment1MastermindNoArrays(); //assignment1Mastermind obj game1 created and allocated
        directions(); //obj game1 calls method directions
        System.out.print("Do you want to play Master Mind: "); //prompts user 
        response = input.next().toLowerCase(); //response set equal to next letter, set to lower case
        if (response.equals("y")) { //if response equals "y", then execute do while loop
            do { //do while loop plays game, prompts a winner; the solution; asks if the want to play again until response equals "n"
                int numCAndP; //declaring int numCAndP
                int numColor; //declaring int numColor
                makeSolution(); //game1 obj calls makeSolution method to generate the solution
                do { //do while loop allows user to guess the solution and prompts feedback until numCAndP is equal to the number of pegs
                   numCAndP = 0; //initializes numCAndP to 0
                   numColor = 0; //initializes numColor to 0
                   allowUserToGuess(); //obj game1 calls the allowUserToGuess method
                   numCAndP = numberCorrectColorAndPosition(); //numCAndP set equal to whatever CorrectColorAndPosition() returns
                   numColor = numberCorrectColor(); //numColor set equal to whatever numberCorrectColor() returns
                   if (numCAndP != getNumberOfPegs()) { //if numCAndP isn't equal to the number of pegs, then prompt user with feedback
                       System.out.printf("There are %d peg colors " + 
                       "correct and %d pegs in the correct " + 
                       "position\n", numColor, numCAndP); //prompts user with feedback
                    } 
                } while (numCAndP < getNumberOfPegs()); //once numCAndP is equal to number of pegs break loop
                System.out.println("You Won!!!!!"); //prompts user they won
                System.out.printf("The pegs that were chosen " +
                "were %s\n", getSolution()); //prompts user with solution
                System.out.print("Do you want to play " + 
                "another game of Master Mind? (Y/N): "); //prompts user asking if they want to play agian
                response = input.next().toLowerCase(); //response set equal to next letter, set to lower case
            } while (!response.equals("n")); //once response is equal to "n" break loop
        }
    }
    
    //Instance variables declared and NUM_OF_PEGS initialized to 5.
    public static final int NUM_OF_PEGS = 5;
    public static String userGuess;
    public static String solution;
    //Constructor initializes userGuess and solution to NULL
    public static void assignment1MastermindNoArrays() {
        userGuess = "";
        solution = "";
    }
    //Method returns String userGuess
    public static String getUserGuess() {
        return userGuess;
    }
    //Method returns String solution
    public static String getSolution() {
        return solution;
    }
    //Method returns int NUM_OF_PEGS
    public static int getNumberOfPegs() {
        return NUM_OF_PEGS;
    }
    //Method prints directions
    public static void directions() {
        System.out.println("Master Mind");
        System.out.println("The object of this game is to guess the colored pegs that the ");
        System.out.println("computer has randomly chosen. You will guess the sequence by ");
        System.out.println("typing in the first letter of the color that you choose.");
        System.out.println("The color choices are:\n");
        System.out.println("\tY-Yellow\n\tO-Orange\n\tR-Red\n\tB-Blue\n\tG-Green\n\tP-Purple");
        System.out.println("If the computer had a solution of BYRBP, and the user entered OBGRP");
        System.out.println("The computer would tell the user that they have 3 colors correct and");
        System.out.println("1 color in the correct position. Using this information, the user");
        System.out.println("would then guess again until they get all of the colors correct and");
        System.out.println("in the correct order.\n");
    }
    
    public static void makeSolution() { //Method initializes solution to a String 
        solution = (NUM_OF_PEGS == 3)? "EFT" : "EMILY"; //solution set equal to an initial value
        String dict = "YORBGP"; //(OPTIONAL) uses a string variable instead of a string array
        int rNum; //int rNum declared
        Random randomNum = new Random(); //object randomNum is instatiated and declared
        for (int i = 0; i <= (NUM_OF_PEGS - 1); i++) { //for loop begins at 0, increments by one until greater than NUM_OF_PEGS - 1
            String newLet = ""; //String newLet declared and initialized to NULL at beginning of loop
            rNum = randomNum.nextInt(6); //rNum set equal to a random number, from 0 - 5 (the same size of dict[])by invocing randomNum obj
            newLet = Character.toString(dict.charAt(rNum)); //(OPTIIONAL) newLet set equal to the letter at index rNum of dict
            solution = solution.substring(0, i) + newLet + 
            solution.substring(i + 1); //solution set equal to the substr from 0 to i plus newLet plus substr from i + 1 to end
            }
        System.out.println("I have chosen my color pegs"); //prompts user when pegs chosen
        //System.out.println("Solution: " + solution); //(FOR TESTING)prompts user with solution
        System.out.printf("The solution has %d pegs\n\n", NUM_OF_PEGS); //prompts user the NUM_OF_PEGS
    }
    
    public static void allowUserToGuess() { //Method allows user to enter their guess
        Scanner input = new Scanner(System.in); //Scanner obj input instatiated and allocated
        System.out.print("Enter your guess: "); //prompts user to enter guess
        userGuess = input.next().toUpperCase(); //userGuess set equal to next string and set to uppercase
        if (userGuess.length() != NUM_OF_PEGS) { //if userGuess is not the same length, then user is prompted to enter new guess
            System.out.println("There are " + NUM_OF_PEGS + 
            " pegs in the solution, your guess does not have" +
            " the correct number of pegs. Try again!!!"); //prompts user that guess is not the same length and to try again
            allowUserToGuess(); //invokes method allowUserToGuess so user can guess again
        }
        else { //if userGuess is equal to the length of NUM_OF_PEGS
            System.out.println("Your guess is: " + userGuess); //prompts user with their guess 
        }
    }
  
    public static int numberCorrectColorAndPosition() { //Method returns the int count of colored pegs in the correct position
        int count = 0; //int count declared and initialized to 0
        for (int i = 0; i <= (NUM_OF_PEGS -1); i++) { //for loop starts at 0, increments by 1 until greater than NUM_OF_PEGS
            String userPeg = Character.toString(userGuess.charAt(i)); //String userPeg declared and set equal to letter at index i
            String solPeg = Character.toString(solution.charAt(i)); //String solPeg declared and set equal to letter at indext i
            if (userPeg.equals(solPeg)) { //if userPeg is equal to solPeg, then increment count by 1. Else do nothing
                count++; //increments count by 1
            }
        }
        return count; //returns int count
    }
    
    public static int numberCorrectColor() { //Method returns int count of colored pegs in userGuess and solution
        int count = 0; //int count declared and initialized to 0
        String dict = "YORGBP";
        for (int i = 0; i <= 5; i++) { //count for each letter
            int numUserLet = 0; //the count for a user letter is initially 0
            int numSolLet = 0; //the count for a sol letter is initially 0
            for (int j = 0; j <= (NUM_OF_PEGS - 1); j++) { //count for a letter in each position
                String userPeg = Character.toString(userGuess.charAt(j)); //String userPeg declared and set equal to letter of userGuess at index i
                String solPeg = Character.toString(solution.charAt(j)); //String solPeg declared and set equal to letter of solution at index i
                if (userPeg.equals(Character.toString(dict.charAt(i)))) { //if the userPeg is equal to the letter then increment numUserLet
                    numUserLet++; //increment numUserLet
                }
                if (solPeg.equals(Character.toString(dict.charAt(i)))) { //if solPeg is equal to the letter then increment numSolLet
                    numSolLet++; //increment numSolLet
                }
            }
            if (numUserLet != 0 && numSolLet != 0) { //if numUserLet and numSolLet are not 0 then continue. Else do nothing
               if (numUserLet == numSolLet) { //if numUserLet is equal to numSolLet, then count is raised to numSolLet
                  count += numSolLet; //count is raised to numSolLet
               }
               else { //if numUserLet is not equal to numSolLet, then count is raised by numSolLet if it's less than numUserLet. if not raise count by numUserLet 
                  count += (numUserLet > numSolLet)? numSolLet : numUserLet; //count is raised by numSolLet if it's less than numUserLet. if not, raise count by numUserLet
               }
            }
        }
        return count; //returns int count of colored pegs in userGuess and solution
    }
}
