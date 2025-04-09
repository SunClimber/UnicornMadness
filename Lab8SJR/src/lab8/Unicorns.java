package lab8;
import net.datastructures.SortedTableMap;
import java.util.Random;

//import net.datastructures.ArrayList;

/**
 * Name: Sam Reynebeau
 * Date: 4/8/2025
 * Description: This class.... does things and stuff
 * Bugs: None known
 * Reflection: stuff and things and stuff
 */
public class Unicorns{

    //Nested Unicorn class----------------------------------
    public class Unicorn{
        //Every unicorn starts with 
        double uniDollars = 1000000.0;
        double xCoordinate = 0.0;

        public Unicorn(){

        }

    }
    //--------------END OF NESTED CLASS---------------------


    //sorted map to keep track of each unicorn, it's x-value and # of unidollars
    public SortedTableMap<Integer, Unicorn> map = new SortedTableMap<>();

    //these will be set from user in main method when object is created
    private int numUnicorns = 0;
    private int numIterations = 0;
    //create random seed so each time random is called it starts from the same place and will be reproducible
    private Random random = new Random(42);


    public Unicorns(int numUnicorns, int numIterations){
        //set instance variables
        this.numUnicorns = numUnicorns;
        this.numIterations = numIterations;

        //create Unicorns and store them
        initUnicornsInMap();
    }

    


    /**
     * This method creates each unicorn object dpending on numUnicorns variable passed in by user
     * and also stores them in the sorted map
     */
    public void initUnicornsInMap(){
        //iterate thru numUnicorns and initialize that many unicorn objects into map
        for(int i = 0; i < numUnicorns; i++){

            Unicorn unicorn = new Unicorn();
            map.put(i,unicorn);

        }

        //have to initialize map at same time I think, cuz you have to store each 
        //unicorn as it's created otherwise you'll iterate over it unless you want to
        //create a new variable every time.. which nah that sounds stupid. 
    }

    public void runUnicornSimulation(){
        compute();
        print();
        

    }



    /**
     * computes things
     */
    public void compute(){

        //iterate thru and set each unicorn's values numIterations times
        for(int i = 0; i < numIterations; i++)
            //set random x coordinate for each unicorn
            for(int j = 0; j < numUnicorns; j++){

                // Generate random double between -1e8 and 1e8
                double randomDouble = (random.nextDouble() * 2e8) - 1e8;
                // Round to nearest 0.1
                randomDouble = Math.round(randomDouble * 10.0) / 10.0;

                //get unicorn at key j and change it's x value to random
                Unicorn unicorn = map.get(j);
                unicorn.xCoordinate = randomDouble;

                //reset unicorn j to changed unicorn
                map.put(j,unicorn);
            }



    }

    /**
     * TODO:
     */
    public void print(){
        String unicorn = "Unicorn";
        String xi = "xi";
        String ui = "ui";
        
        //System.out.print(map.get(1).xCoordinate);
        int unicornWidth = unicorn.length();

        System.out.printf(unicorn + "%20s %15s", xi, ui);
        System.out.println();


        //loop through unicorns
        for(int i = 0; i < numUnicorns; i++){

            Unicorn unicornValues = map.get(i);
            double xValue = unicornValues.xCoordinate;
            double uniDollars = unicornValues.uniDollars;


            System.out.printf("%" + unicornWidth + "d" + "%" + 20 + "f" + "%" + 16 +"f", i, xValue, uniDollars);
            System.out.println();
        
        }


    }


    public void draw(){

    }

    //methods and such
        //everything()

        //initialize map

        //compute() 

        //print

        //draw()

}