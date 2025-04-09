import java.util.Random;
import java.util.Scanner;
import lab8.Unicorns;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Enter # of unicorns and iterations: 20 1000
        System.out.print("Enter # of unicorns and iterations: ");

        //USER INPUT
        Scanner scnr = new Scanner(System.in);
        int numUnicrons = scnr.nextInt();
        int numIterations = scnr.nextInt();

        //create and intialize object with # unicorns and iterations from user input
        Unicorns unicorns = new Unicorns(numUnicrons, numIterations);

        unicorns.compute();

        unicorns.print();

        // Random random = new Random(42);
        // // Generate random double between -1e8 and 1e8
        // double randomDouble = (random.nextDouble() * 2e8) - 1e8;

        // // Round to nearest 0.1
        // randomDouble = Math.round(randomDouble * 10.0) / 10.0;

        // Print the result, ensuring it's displayed in decimal format
        //System.out.printf("%.1f%n", randomDouble);  // Format to 1 decimal place


        //System.out.println(randomDouble);
        //System.out.println(anotherOne);

        //System.out.println(string);

        //object.numUnicorns object.numIterations
        


        //compute() 

        //print

        //draw()
    }
}


