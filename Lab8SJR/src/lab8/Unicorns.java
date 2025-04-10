package lab8;
import net.datastructures.SortedTableMap;
import java.util.Random;
import net.datastructures.ArrayList;
import net.datastructures.Entry;

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
        int unicornID = 0;

        public Unicorn(){

        }

    }
    //--------------END OF NESTED CLASS---------------------


    //sorted map where the x-coordinate is the key
    public SortedTableMap<Double, Unicorn> xSortedMap = new SortedTableMap<>();

    //sorted map where the unidollars is the key
    public SortedTableMap<Double, Unicorn> uSortedMap = new SortedTableMap<>();

    //this list is needed to iterate through unicorns by ID because xi and ui are not known until sim runs
    //all unicorns will be stored in here.
    public ArrayList<Unicorn> unicornList = new ArrayList<>();




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
        initUnicorns();
    }

    


    /**
     * This method creates each unicorn object dpending on numUnicorns variable passed in by user
     * and also stores them in the sorted map
     */
    public void initUnicorns(){
        //iterate thru numUnicorns, initalize both maps, and store id for each unicorn
        for(int i = 0; i < numUnicorns; i++){

            Unicorn unicorn = new Unicorn();
            unicorn.unicornID = i;
            xSortedMap.put(unicorn.xCoordinate,unicorn);

            uSortedMap.put(unicorn.uniDollars,unicorn);

            //add to list
            unicornList.add(i,unicorn);

        }
    }
    
    /**
     * run this biotch
     */
    public void runUnicornSimulation(){
        compute();
        print();
        

    }



    /**
 * Computes things.
 */
public void compute() {
    for (int i = 0; i < numIterations; i++) {

        // rebuild xSortedMap from current unicorn positions (no need to clear the map)
        for (int j = 0; j < numUnicorns; j++) {
            Unicorn realUnicorn = unicornList.get(j);

            // get the current x-coordinate and remove the unicorn from that position
            xSortedMap.remove(realUnicorn.xCoordinate);

            // get closest left and right unicorns
            Entry<Double, Unicorn> rightEntry = xSortedMap.higherEntry(realUnicorn.xCoordinate);
            Entry<Double, Unicorn> leftEntry = xSortedMap.lowerEntry(realUnicorn.xCoordinate);

            if (rightEntry != null) {
                Unicorn rightUnicorn = rightEntry.getValue();
                realUnicorn.uniDollars += rightUnicorn.uniDollars / 2;
                rightUnicorn.uniDollars /= 2;
            }

            if (leftEntry != null) {
                Unicorn leftUnicorn = leftEntry.getValue();
                realUnicorn.uniDollars += leftUnicorn.uniDollars / 2;
                leftUnicorn.uniDollars /= 2;
            }

            // move unicorn based on its uniDollars and random value
            double randomDouble = random.nextDouble(-1, 1);
            double newX = realUnicorn.xCoordinate + (randomDouble * realUnicorn.uniDollars);
            newX = Math.round(newX * 10.0) / 10.0; // round to nearest 0.1
            realUnicorn.xCoordinate = newX;

            // put the unicorn back into the map at the new position
            xSortedMap.put(realUnicorn.xCoordinate, realUnicorn);
        }
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

            Unicorn realUnicorn = unicornList.get(i);



            //Unicorn unicornValues = xSortedMap.get(i);
            double xValue = realUnicorn.xCoordinate;
            double uniDollars = realUnicorn.uniDollars;


            System.out.printf("%" + unicornWidth + "d" + "%" + 20 + ".1f" + "%" + 16 +".1f", i, xValue, uniDollars);
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