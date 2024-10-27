// task07

package MasterMind;

import java.io.Console;
import java.util.*;

public class mastermind{
    public static void main(String[] args) {
        Console cons = System.console();
        int triesleft = 12;
        String secretNumber = generateSecretNumber();
        // System.out.println(secretNumber);

        while (triesleft > 0){
            System.out.println("Tries left: " + triesleft);
            String userGuess = cons.readLine(">>> Enter your guess (4 digits between 1 and 6): ");
            System.out.println("Your guess: " + userGuess);

            // validate user guess
            if (!validateUserGuess(userGuess)){
                continue;
            }

            int correctposition = 0;
            int wrongposition = 0;

            // track matched digits
            boolean[] matchedInSecret = new boolean[secretNumber.length()];
            boolean[] matchedInGuess = new boolean[userGuess.length()];

            // check for correct positions first
            for (int i = 0; i < secretNumber.length(); i++){
                if (userGuess.charAt(i) == secretNumber.charAt(i)){
                    correctposition += 1;
                    matchedInSecret[i] = true;
                    matchedInGuess[i] = true;
                } 
            }

            // now check for wrong positions - correct digit, wrong place
            for (int i = 0; i<userGuess.length(); i++){
                // only check for unmatched digits
                if (!matchedInGuess[i]){
                    for (int j = 0; j < secretNumber.length(); j++){
                        if (!matchedInSecret[j] && userGuess.charAt(i) == secretNumber.charAt(j)){
                            wrongposition += 1;
                            matchedInSecret[j] = true;
                            break; // stop after finding the first unmatched position
                        }
                    }
                }
            }

            System.out.println("Correct position count: " + correctposition);
            System.out.println("Wrong position but correct number count: " + wrongposition);

            if (correctposition == 4){
                System.out.println("Congratulations! You win!");
                break;
            } else{
                triesleft -= 1;
            }
        
            if (triesleft == 0)
                System.out.println("You lost! You have run out of tries. The secret number was: " + secretNumber);
        }

        playAgain();
    }

    private static boolean validateUserGuess(String userGuess){
        
        // check for invalid input
        // input is not a 4-digit
        if (userGuess.length() != 4) {
            System.out.println("Please enter a 4-digit number.");
            return false;
        } else {

            // check if each digit is between 1 and 6
            for (int i = 0; i < userGuess.length(); i++){
                if (Character.getNumericValue(userGuess.charAt(i)) > 6 || Character.getNumericValue(userGuess.charAt(i)) < 1){
                    System.out.println("Please enter a number between 1 and 6");
                    return false;
                }
            }
        }
        return true;
    }

    // use random function to generate 4 digits between 1 and 6
    private static String generateSecretNumber(){
        Random random = new Random();
        String num = "";
        for (int i = 0; i<4; i++){
            String digit = Integer.toString(random.nextInt(6) + 1);
            num += digit;
        }
        return num;
    }

    // allow user to repeat the game
    private static void playAgain(){
        Console cons = System.console();
        String play = cons.readLine(">>> Do you want to play again? (yes/no)");

        if (play.toLowerCase().equals("yes"))
             main(null);
        
        else
            System.out.println("Thanks for playing!");

    }
}