/* Name: [Kirill Afanasiev]
Date: [2021-04-12]
[Hangman game]
*/
package com.company;

import java.util.Scanner;

public class Hangman
{
    static String revealedWord;
    static String hiddenWord;
    static int attempt = 0;

    public static void main(String[] args)
    {
        Scanner consoleReader = new Scanner(System.in);
        char repeat = 'y';
        while(GameRepeater(repeat))
        {
            System.out.println("I Welcome to Sydney's Word Game!\n");
            System.out.println("I'm ready: I've chosen a word for you to guess!\n");
            //amount of attempts and hidden word
            while (attempt < 10 && hiddenWord.contains("*")) {



                // collect input from user
                System.out.println("Guess a letter in the word: " + hiddenWord + ": ");
                char c = consoleReader.next().charAt(0);
                charChecker(c);
            }
            //restart the game
            System.out.println("Play again? Y/N");
            repeat = consoleReader.next().toLowerCase().charAt(0);
        }
    }
    // generate a random word from the array
    static void WordGenerator(String[] dictionary)
    {
        revealedWord = dictionary[(int) (Math.random() * dictionary.length)];
        hiddenWord = new String(new char[revealedWord.length()]).replace("\0", "*");
    }
    //restart the game
    static boolean GameRepeater(char repeat)
    {
        boolean isRunning = true;
        if(repeat == 'n'){
            isRunning = false;
            System.out.println("Thanks for playing");
        }else{
            attempt = 0;
            WordGenerator(new String[]{"discovery", "cabbage", "computer", "programming", "crocodile", "kangaroo" ,
                    "garbage", "rubbish", "sidewalk", "washroom", "ukraine", "football", "baseball"});
        }
        return isRunning;
    }
    //check letter in the word
    static void charChecker(char c)
    {
        StringBuilder dirtyHiddenWord = new StringBuilder();
        for (int i = 0; i < revealedWord.length(); i++) {
            if (revealedWord.charAt(i) == c) {
                dirtyHiddenWord.append(c);
            } else if (hiddenWord.charAt(i) != '*') {
                dirtyHiddenWord.append(revealedWord.charAt(i));
            } else {
                dirtyHiddenWord.append("*");
            }
        }
        // attempts to guess
        if (hiddenWord.equals(dirtyHiddenWord.toString())) {
            attempt++;
            if (attempt == 10) {
                System.out.println("You ran out of guesses!" );
                System.out.println("Your word was " + revealedWord);
            }else{
                System.out.println(" You have " + (10 - attempt) + " incorrect guesses left.");
            }
        } else {
            hiddenWord = dirtyHiddenWord.toString();
        }
        // if user win the game
        if (hiddenWord.equals(revealedWord)) {
            System.out.println("You guessed the word " + revealedWord + "!");
        }
    }
}