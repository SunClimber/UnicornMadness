package lab8;
import net.datastructures.SortedTableMap;
import java.util.Random;
import net.datastructures.ArrayList;
import net.datastructures.Entry;


/**
 * Name: Sam Reynebeau
 * Date: 4/8/2025
 * Description: This class initializes a user defined number of unicorns and iterations to run a simulation
 * that, when done, sorts the unicorns by number of dollars and draws where each one is at on the x axis.
 * (A Unicorn is an object defined in nested class)
 * Bugs: None known
 * Reflection: I initially started with one map, and realized after a while that it would probably be better
 * to use two, since we are organizing both by unidollars and x coordinates. That made it much easier to keep 
 * track of things. I caught on to needing a nested Unicorn class very quickly and I think it makes things 
 * substantially simpler, instead of implementing the values in ArraList or something that you would need to 
 * iterate through and have no proper variable names. I did use an an ArrayList to keep track of the Unicorn
 * ID's since I really wasn't sure how else to do it and it seemed to make sense to me. I am sure I could have 
 * done it in other ways. I kept the x values high for the console output and scaled them back down in order 
 * to draw since I wasn't sure, as you didn't change the output sample after your announcment of using [-1,1]
 * so I figured that you still wanted those higher values for the console output. 
 */
public class Unicorns {

    //Nested Unicorn class----------------------------------
    public class Unicorn{
        double uniDollars = 1000000.0;
        double xCoordinate = 0.0;
        int unicornID = 0;

        public Unicorn(){
            //intentionally empty
        }
    }
    //--------------END OF NESTED CLASS---------------------

    //sorted map where the x-coordinate is the key
    public SortedTableMap<Double, Unicorn> xSortedMap = new SortedTableMap<>();

    //sorted map where the unidollars is the key
    public SortedTableMap<Double, Unicorn> uSortedMap = new SortedTableMap<>();

    //list to store all unicorns by ID, this is needed in order to find each individual unicorn, as their keys
    //will not be set until after the initialization
    public ArrayList<Unicorn> unicornList = new ArrayList<>();

    private int numUnicorns = 0;
    private int numIterations = 0;
    private Random random = new Random(42);

    /**
     * constructor
     * @param numUnicorns number of Unicorns wanted in simulaion
     * @param numIterations number of iterations to run in simulation
     */
    public Unicorns(int numUnicorns, int numIterations){
        this.numUnicorns = numUnicorns;
        this.numIterations = numIterations;
        //creates unicorns straight away
        initUnicorns();
    }

    /**
     * Initializes unicorns
     */
    public void initUnicorns(){
        for(int i = 0; i < numUnicorns; i++){
            Unicorn unicorn = new Unicorn();
            unicorn.unicornID = i + 1; //changing to start at 1 instead of 0
            xSortedMap.put(unicorn.xCoordinate, unicorn);
            unicornList.add(i, unicorn);
        }
    }

    /**
     * Runs whole simulation start to finish for convenience sake
     */
    public void runUnicornSimulation(){
        compute();
        print();
        draw();
    }

    /**
     * Computes and rearranges x coordinates based on it's left and right neighbors (if they have any)
     */
    public void compute(){
        for(int iter = 0; iter < numIterations; iter++){
            // update positions
            for(Unicorn u : unicornList){
                double r = random.nextDouble() * 2 - 1;
                u.xCoordinate += r * u.uniDollars;
                u.xCoordinate = Math.round(u.xCoordinate * 10) / 10.0;
            }

            // rebuilding position map
            xSortedMap = new SortedTableMap<>();
            for(Unicorn u : unicornList){
                xSortedMap.put(u.xCoordinate, u);
            }

            // unidollar transfers
            for(Unicorn u : unicornList){
                //immedeate neighbor on x axis must be lower entry because they all start at the same amount
                Entry<Double, Unicorn> left = xSortedMap.lowerEntry(u.xCoordinate);
                //RIGHT
                Entry<Double, Unicorn> right = xSortedMap.higherEntry(u.xCoordinate);
                //if left is null then there's no neighbor on that side
                if(left != null){
                    Unicorn leftUni = left.getValue();
                    double stolen = leftUni.uniDollars / 2;
                    u.uniDollars += stolen;
                    leftUni.uniDollars -= stolen;
                }

                if(right != null){
                    Unicorn rightUni = right.getValue();
                    double stolen = rightUni.uniDollars / 2;
                    u.uniDollars += stolen;
                    rightUni.uniDollars -= stolen;
                }
            }
        }
    }

    /**
     * Prints results to console in specific formatting defined in lab
     */
    public void print(){
        // Rebuild unidollars map
        uSortedMap = new SortedTableMap<>();
        for(Unicorn u : unicornList){
            uSortedMap.put(u.uniDollars, u);
        }

        // matches sample output format
        System.out.printf("%7s %15s %15s%n", "Unicorn", "xi", "ui");
        for(Entry<Double, Unicorn> entry : uSortedMap.entrySet()){
            Unicorn u = entry.getValue();
            System.out.printf("%7d %15.1f %15.0f%n", 
                u.unicornID, 
                u.xCoordinate, 
                u.uniDollars);
        }
    }

    /**
     * Draws unicorn positions on x axis with transparent circles from xSortedMap
     * @return void but produces StdDraw window with x axis drawing
     */
    public void draw() {
        // find x-coordinate range using the pre-sorted map
        double minX = -1.0;
        double maxX = 1.0;
        //getting first and last entry keys to get min and max x coordinate
        Entry<Double, Unicorn> firstEntry = xSortedMap.firstEntry();
        Entry<Double, Unicorn> lastEntry = xSortedMap.lastEntry();
        //getting keys
        if (firstEntry != null && lastEntry != null) {
            minX = firstEntry.getKey();
            maxX = lastEntry.getKey();
        }
        
        // set up canvas with proper aspect ratio
        StdDraw.setCanvasSize(1000, 200);
        StdDraw.setXscale(-1.02, 1.02);
        StdDraw.setYscale(-0.05, 0.05);
        
        // first draw the horizontal line between the ticks
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(-1.0, 0, 1.0, 0);  // Line stops at ticks
        
        // draw vertical end ticks
        StdDraw.setPenRadius(0.006);
        double tickLength = 0.01;
        StdDraw.line(-1.0, -tickLength, -1.0, tickLength);
        StdDraw.line(1.0, -tickLength, 1.0, tickLength);
        
        // draw each unicorn position using normalized coordinates
        double circleRadius = 0.006;
        for (Entry<Double, Unicorn> entry : xSortedMap.entrySet()) {
            // normalize x to [-1,1] range to properly visualize
            double normX = 2 * ((entry.getKey() - minX)/(maxX - minX)) - 1;
            
            // just draw the outline of circle (transparent center)
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.0015);
            StdDraw.circle(normX, 0, circleRadius);
        }
    }
}