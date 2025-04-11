import java.util.Random;
import java.util.Scanner;
import lab8.Unicorns;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Enter # of unicorns and iterations
        System.out.print("Enter # of unicorns and iterations: ");

        //user input
        Scanner scnr = new Scanner(System.in);
        int numUnicrons = scnr.nextInt();
        int numIterations = scnr.nextInt();

        //create and intialize object with # of unicorns and iterations from user input
        Unicorns unicorns = new Unicorns(numUnicrons, numIterations);

        //RUN SIMULATION-----------------------------
        unicorns.runUnicornSimulation();
    }
}