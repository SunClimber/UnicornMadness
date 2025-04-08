package lab8;
import net.datastructures.SortedTableMap;
import net.datastructures.ArrayList;

/**
 * Name: Sam Reynebeau
 * Date: 4/8/2025
 * Description: This class.... does things and stuff
 * Bugs: None known
 * Reflection: stuff and things and stuff
 */
public class Unicorns {

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
    SortedTableMap<Integer, Unicorn> map = new SortedTableMap<>();

    //these will be set from user in main method when object is created
    private int numUnicorns = 0;
    private int numIterations = 0;


    public Unicorns(int numUnicorns, int numIterations){
        this.numUnicorns = numUnicorns;
        this.numIterations = numIterations;

        //initUnicorns()?
    }

    



    public void initUnicorns(){
        //iterate thru numUnicorns and initialize that many unicorn objects
        for(int i = 0; i < numUnicorns; i++){


        }

        //have to initialize map at same time I think, cuz you have to store each 
        //unicorn as it's created otherwise you'll iterate over it unless you want to
        //create a new variable every time.. which nah that sounds stupid. 
    }

    public void initializeMap(){

    }
    //methods and such

        //initialize map

        //compute() 

        //print

        //draw()




}
