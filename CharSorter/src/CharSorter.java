import java.util.*;

public class CharSorter {


    public static void main(String[] args) {

        Scanner userInputScan = new Scanner(System.in);

        System.out.println("Welcome to Character Sorter Program\n" +
              "Please input a string to be sorted ");
        String userInput = userInputScan.nextLine();
        int userOption;

        do {
            //Loops between the 3 methods and exits successfully based on user input
            System.out.println("\nPlease select the option you would like to see\n");


            System.out.println("1. Display character frequencies alphabetically\n" +
                    "2. Display sorted frequencies\n" +
                    "3. Show types of character frequencies\n" +
                    "4. Exit");

            try {
                userOption = userInputScan.nextInt();
            } catch (InputMismatchException mismatchError) {
                userInputScan.nextLine();
                userOption = 0; //If user input for the option isn't 1-4, the input becomes 0.
            }

            switch(userOption) {
                case 1: alphabeticalSort(userInput);
                        break;
                case 2: frequencySort(userInput);
                        break;
                case 3: charTypes(userInput);
                        break;
                case 4: System.out.println("\nCharacter Sorter Exited Successfully"); //exit message
                        break;
                default: System.out.println("Error, bad input, please enter a number 1-4");
                        break;
        }
        } while (userOption != 4);
    }

    public static void alphabeticalSort(String phrase) {

        char[] charUserInput = phrase.toCharArray();

        int incrementOuter;
        int incrementInner;
        int repetition;
        char tempValue;

        for (incrementOuter = 0; incrementOuter < charUserInput.length; ++incrementOuter) {
            for (incrementInner = incrementOuter + 1; incrementInner < charUserInput.length; ++incrementInner) {
                if (charUserInput[incrementOuter] > charUserInput[incrementInner]) {

                    tempValue = charUserInput[incrementInner]; //Bubble sort algorithm, swaps char values alphabetically
                    charUserInput[incrementInner] = charUserInput[incrementOuter];
                    charUserInput[incrementOuter] = tempValue;
                }
            }
        }

        for (incrementOuter = 0; incrementOuter < charUserInput.length; ) {
            repetition = 1;
            for (incrementInner = incrementOuter + 1; incrementInner < charUserInput.length; ++incrementInner) {
                if (charUserInput[incrementOuter] == charUserInput[incrementInner]) {

                    repetition++;
                }
                else {

                    break;
                }
            }

            String characterFrequency = (charUserInput[incrementOuter] + " freq: " + repetition);
            System.out.println(characterFrequency);

            incrementOuter+=repetition;
        }

        System.out.println();
    }

    public static void frequencySort(String phrase) {

        char[] charUserInput = phrase.toCharArray();
        List<Integer> listOfRepetitions = new ArrayList(); //Lists to move repetition and char values to respective arrays
        List<Character> listOfCharacters = new ArrayList();

        int incrementOuter;
        int incrementInner;
        int repetition;
        int temp;
        char tempValue;

        System.out.println("The sorted by frequency characters are:\n");
//Reusing alphabetic sort code to later reorganize by frequency and by alphabet after sorting by frequency
        for (incrementOuter = 0; incrementOuter < charUserInput.length; ++incrementOuter) {
            for (incrementInner = incrementOuter + 1; incrementInner < charUserInput.length; ++incrementInner) {
                if (charUserInput[incrementOuter] < charUserInput[incrementInner]) {

                    tempValue = charUserInput[incrementInner];   //Bubble sort algorithm, swaps char value
                    charUserInput[incrementInner] = charUserInput[incrementOuter];
                    charUserInput[incrementOuter] = tempValue;
                }
            }
        }

        for (incrementOuter = 0; incrementOuter < charUserInput.length; ) {
            repetition = 1;
            for (incrementInner = incrementOuter + 1; incrementInner < charUserInput.length; ++incrementInner) {
                if (charUserInput[incrementOuter] == charUserInput[incrementInner]) {

                    repetition++;
                }
                else {

                    break;
                }
            }

            char userInput = (charUserInput[incrementOuter]);

            incrementOuter += repetition;
            listOfRepetitions.add(repetition);
            listOfCharacters.add(userInput);
        }

        Integer[] repetitionsArray = listOfRepetitions.toArray(new Integer[0]); //Immediately changing to arrays to loop through
        Character[] charactersArray = listOfCharacters.toArray(new Character[0]);

        for ( incrementOuter = 0; incrementOuter < repetitionsArray.length; ++incrementOuter) {
            for (incrementInner = incrementOuter + 1; incrementInner < repetitionsArray.length; ++incrementInner) {
//This if statement keeps the index of repetition array values together with the index of char array values
                if (repetitionsArray[incrementInner] > repetitionsArray[incrementOuter]) {

                    temp = repetitionsArray[incrementInner];
                    repetitionsArray[incrementInner] = repetitionsArray[incrementOuter];
                    repetitionsArray[incrementOuter] = temp;

                    tempValue = charactersArray[incrementInner];
                    charactersArray[incrementInner] = charactersArray[incrementOuter];
                    charactersArray[incrementOuter] = tempValue;
                }
//Swaps character values back to sort alphabetically if repetition value is equivalent
                if(repetitionsArray[incrementInner] == repetitionsArray[incrementOuter]) {

                    tempValue = charactersArray[incrementInner];
                    charactersArray[incrementInner] = charactersArray[incrementOuter];
                    charactersArray[incrementOuter] = tempValue;
                }
            }
        }

        for (incrementOuter = 0; incrementOuter < charactersArray.length; ++incrementOuter) {

            System.out.println(charactersArray[incrementOuter] + " freq: " + repetitionsArray[incrementOuter]);
        }

        System.out.println();
    }

    public static void charTypes (String phrase){

        char[] charUserInput = phrase.toCharArray();
        int increment;
        int decimalChar;
        int numericCharacterIncrement = 0;
        int textualCharacterIncrement = 0;
        int whiteSpaceCharacterIncrement = 0;
        int symbolCharacterIncrement = 0;

        for (increment = 0; increment < charUserInput.length; ++increment){

            decimalChar = (int)charUserInput[increment]; //ranges of char based on decimal values

            if((decimalChar >= 65 && decimalChar <= 90) || decimalChar>=97 && decimalChar <=122 ) {
                textualCharacterIncrement++;

            }
            else if(decimalChar <= 57 && decimalChar >= 48 ) {
                numericCharacterIncrement++;
            }

            else if(decimalChar == 32) {
                whiteSpaceCharacterIncrement++;

            }

            else{
                symbolCharacterIncrement++;
            }

        }
        System.out.println("Textual Character count: " + textualCharacterIncrement);
        System.out.println("Numerical Character count: " + numericCharacterIncrement);
        System.out.println("WhiteSpace Character count: " + whiteSpaceCharacterIncrement);
        System.out.println("Symbol Character count: " + symbolCharacterIncrement);
        System.out.println();
    }
}