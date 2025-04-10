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
        


        //compute() 

        //print

        //draw()
    }
}