package com.company;
import java.util.*;


public class CodeBreaker {
    private static List<String> allCombinations = new ArrayList<String>();
    private static final String[] letters = new String[6];
    private static final String G = "green";
    private static final String U = "blue";
    private static final String R = "red";
    private static final String Y = "yellow";
    private static final String O = "orange";
    private static final String W = "white";
    private static int numOfTurns = 10;
    private static String player;
    private static String secretC;
    private static String[] secretCode = new String[4];

    public CodeBreaker(String p) {
        player = p;
        Random obj = new Random();
        showGame();
        secretC = allCombinations.get(obj.nextInt(allCombinations.size()));
        for (int i = 0; i < secretC.length(); i++) {
            secretCode[i] = secretC.substring(i, i + 1);
        }
        getGuess();
    }

    public static void showGame() {
        System.out.println("---");
        System.out.println("Welcome to Mastermind, " + player + "!");
        System.out.println("---");
        letters[0] = "R";
        letters[1] = "G";
        letters[2] = "U";
        letters[3] = "O";
        letters[4] = "Y";
        letters[5] = "W";
        getAllCombinations();
        showAllRules();
    }

    public static void showAllRules() {
        System.out.println("Displaying Rules to the Game Mastermind:");
        showGameRules();
        showPlayerRules();
    }

    public static void showGameRules() {
        System.out.println("---");
        System.out.println("This is a Board game created by Mordecai Meirowitz.");
        System.out.println("---");
        System.out.println("There are 2 players, the Codemaker and the Codebreaker.");
        System.out.println("---");
        System.out.println("The goal of the Codemaker is create a secret code of 4 pegs with 6 colors to choose from.");
        System.out.println("---");
        System.out.println("The 6 colors are Red, Blue, Green, Orange, White, and Yellow.");
        System.out.println("---");
        System.out.println("For example, the Codemaker can have his/her secret be Red, Blue, Green, Green.");
        System.out.println("---");
    }

    public static void showPlayerRules() {
        System.out.println(player + ", you are playing as the Codebreaker.");
        System.out.println("---");
        System.out.println("And it is your goal to guess the opponents secret code as quickly as possible.");
        System.out.println("---");
        System.out.println("After you guess the secret code, the Codemaker will tell you how close your guess was to the answer.");
        System.out.println("---");
        System.out.println("For this game, you put 4 letters that correspond to the colors you put in!");
        System.out.println("---");
        System.out.println("G is for " + G + ", R is for " + R + ", U is for "+ U + ", Y is for " + Y + ", W is for " +
                W + ", and O is for " + O + ".");
        System.out.println("---");
        System.out.println("When you put in your answer, the computer will respond with either Black (for B), White (for W), or a Blank Space");
        System.out.println("---");
        System.out.println("A Black means the color you guessed is in the right spot.");
        System.out.println("---");
        System.out.println("A white means the color you guessed is in the code, but in the wrong place.");
        System.out.println("---");
        System.out.println("A blank means it's not in the code.");
        System.out.println("---");
        System.out.println("Have fun!");
    }

    public static void getGuess() {
        if (numOfTurns == 0) {
            finishGame();
        } else {
            System.out.println("---");
            System.out.println("What is your guess?");
            String guess;
            Scanner getGuess = new Scanner(System.in);
            guess = getGuess.nextLine();
            if (allCombinations.contains(guess)) {
                System.out.println("---");
                System.out.println(player + " guessed " + guess + ".");
                System.out.println("---");
                numOfTurns--;
                System.out.println("Your turns left: " + numOfTurns + ".");
                isCorrect(guess);
            } else {
                System.out.println("---");
                System.out.println("Try again please.");
                getGuess();
            }
        }
    }
    public static void getAllCombinations() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    for (int l = 0; l < 6; l++) {
                        String possible = letters[i]+letters[j]+letters[k]+letters[l];
                        allCombinations.add(possible);
                    }
                }
            }
        }
    }

    public static void isCorrect(String guess) {
        if (guess.equals(secretC)) {
            System.out.println("---");
            System.out.println("You got it right!");
            System.out.println("---");
            winGame();
        } else {
            System.out.println("Not quite right...");
            System.out.println("---");
            System.out.println(checkCorrect(guess));
            getGuess();
        }
    }

    public static String checkCorrect(String g) {
        String pegs = "";
        String[] guess = new String[4];
        for (int i = 0; i < g.length(); i++) {
            guess[i] = g.substring(i, i + 1);
        }
        List<String> seen = new ArrayList<>();
        for (int i = 0; i < guess.length; i++) {
            if (guess[i].equals(secretCode[i])) {
                pegs = pegs.concat("B");
                seen.add(guess[i]);
            }
        }
        for (int i = 0; i < guess.length; i++) {
            if (secretC.contains(guess[i]) && !(seen.contains(guess[i]))) {
                pegs = pegs.concat("W");
                seen.add(guess[i]);
            } else {
                pegs = pegs.concat(" ");
            }
        }
        String shuffledString = "";

        while (pegs.length() != 0)
        {
            int index = (int) Math.floor(Math.random() * pegs.length());
            String c = pegs.substring(index, index + 1);
            pegs = pegs.substring(0,index)+pegs.substring(index+1);
            shuffledString = shuffledString.concat(c);
        }

        shuffledString = shuffledString.replaceAll(" ", "");
        return shuffledString;

    }

    public static void finishGame() {
        System.out.println("You lost!");
        System.out.println("---");
        System.out.println("The secret code was: " + secretC);
    }

    public static void winGame() {
        System.out.println(player + " won with " + numOfTurns + " turns left!");
    }
}
