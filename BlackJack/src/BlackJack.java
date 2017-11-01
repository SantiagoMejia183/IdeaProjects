import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class BlackJack {

    public static void main(String[] args) {

        Scanner optionScan = new Scanner(System.in);
        Random randomCard = new Random();
        int gamePlayed = 1;      //declaring variables, gamePlayed initialized to 1 to start game
        int playerWins = 0;
        int dealerWins = 0;
        int tieGames = 0;
        int userOption = 0;
        int cardValue = 0;
        int newCard = 0;
        int totalCardValue;
        int dealerCard;


        while (userOption != 4) {  //game ends when user inputs 4
            System.out.println("START GAME #" + gamePlayed);

            cardValue = randomCard.nextInt(13) + 1;
            if (cardValue == 13) {
                cardValue = 10;
                System.out.println("Your card is a KING!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else if (cardValue == 12) {
                cardValue = 10;
                System.out.println("Your card is a QUEEN!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else if (cardValue == 11) {
                cardValue = 10;
                System.out.println("Your card is a JACK!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else if (cardValue == 1) {
                cardValue = 1;
                System.out.println("Your card is a ACE!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else {
                System.out.println("Your card is a " + cardValue + "!");
                System.out.println("Your hand is: " + cardValue + "\n");
            }
            String options = "1. Get another card \n2. Hold hand \n3. Print statistics \n4. Exit \n";
            totalCardValue = 0;
            totalCardValue = totalCardValue + newCard + cardValue; //initial totalCardValue

            while (totalCardValue < 21) { //totalCardValue ends
                System.out.print(options + "\nChoose an option: ");
                try {
                    userOption = optionScan.nextInt();
                } catch (InputMismatchException mismatchError) {
                    optionScan.nextLine();
                    userOption = 0;
                }

                if (userOption == 1) {
                    newCard = randomCard.nextInt(13) + 1; //randomized card value between 13 and 1

                    if (newCard == 13) {
                        newCard = 10;
                        totalCardValue = totalCardValue + newCard;  //different totalCardValue, accounting for new card and not previous
                        System.out.println("Your card is a KING!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else if (newCard == 12) {
                        newCard = 10;
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a QUEEN!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else if (newCard == 11) {
                        newCard = 10;
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a JACK!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else if (newCard == 1) {
                        newCard = 1;
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a ACE!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else {
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a " + newCard + "!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    }
                    {
                        if (totalCardValue == 21) {
                            System.out.println("BLACKJACK! You win!\n");
                            playerWins++;
                            gamePlayed++;
                            newCard = 0;
                            totalCardValue = 0;
                            break;
                        } else if (totalCardValue > 21) {
                            System.out.println("You exceeded 21! You lose :(\n");
                            dealerWins++;
                            gamePlayed++;
                            newCard = 0;
                            totalCardValue = 0;
                            break;
                        }
                    }
                } else if (userOption == 2) {

                    dealerCard = randomCard.nextInt(11) + 16;
                    System.out.println("Dealer's hand: " + dealerCard);
                    System.out.println("Your hand is: " + totalCardValue + "\n");

                    if (dealerCard == totalCardValue) {
                        System.out.println("It's a tie! No one wins!\n");
                        tieGames++;
                        gamePlayed++;
                        totalCardValue = 0;
                        break;
                    } else if ((dealerCard > 21) || (dealerCard < totalCardValue)) {
                        System.out.println("You win!\n");
                        playerWins++;
                        gamePlayed++;
                        totalCardValue = 0;
                        break;
                    } else if (dealerCard > totalCardValue) {
                        System.out.println("Dealer wins!\n");
                        dealerWins++;
                        gamePlayed++;
                        break;
                    }
                } else if (userOption == 3) {

                    System.out.println("\nNumber of Player wins: " + playerWins);
                    System.out.println("Number of Dealer wins: " + dealerWins);
                    System.out.println("Number of tie games: " + tieGames);
                    System.out.println("Total # of games played is: " + gamePlayed);
                    System.out.println("Percentage of Player wins: " + ((double) playerWins / (double) gamePlayed) + "%\n");
                } else if (userOption == 4) {
                    break;
                } else {
                    System.out.println("\nInvalid input!\nPlease enter an integer value between 1 and 4.\n");
                }

            }
        }
    }
}