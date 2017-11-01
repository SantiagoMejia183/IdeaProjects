import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        int MPG = 0;
        double tankCapacity = 0;
        double percentage = 0;

        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter your car’s MPG rating (miles/gallon) as a positive integer: ");

        MPG = userInput.nextInt();

        if (MPG <= 0 ){
            System.out.println("ERROR: ONLY POSITIVE INTEGERS ARE ACCEPTED FOR MPG!!!");
            System.exit(0);
        }

        System.out.print("Enter your car’s tank capacity (gallons) as a positive decimal number: ");
        tankCapacity = userInput.nextDouble();

        if (tankCapacity <= 0){
            System.out.println("ERROR: ONLY POSITIVE DECIMAL NUMBERS ACCEPTED FOR TANK CAPACITY!!!");
            System.exit(0);
        }

        System.out.print("Enter the percentage of the gas tank that is currently filled (from 0-100%): ");
        percentage = userInput.nextDouble();

        if ((percentage > 100) || (percentage < 0)){
            System.out.println("ERROR: PERCENTAGE MUST BE A DECIMAL NUMBER IN THE RANGE OF 0-100(INCLUSIVE)!!!");
            System.exit(0);
        }

        double rawRange = MPG * tankCapacity * (percentage * 0.01);
        {
            if (rawRange <= 25) {
                System.out.println("Attention! Your current estimated range is running low: " + (int) rawRange + " miles left!!!");
            } else {
                System.out.println("Keep driving! Your current estimated range is: " + (int) rawRange + " miles!");
            }
        }

    }
}


