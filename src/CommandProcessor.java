/**
 * This processes each line. Recognizes each command.
 * 
 * @author sohyun
 * @author sshumway
 * @version 01/29/2016
 *
 */
public class CommandProcessor {

    private String[] line;
    private Container c;
    private String com;
    private int[] regions;
    private int x;
    private int y;
    private int w;
    private int h;

    /**
     * constructor. Initialize the container.
     */
    public CommandProcessor() {
        c = new Container();

    }

    /**
     * Set up before it actually gets processed. cline is the command that gets
     * passed in from the CommandParser class.
     * 
     * @param cline
     *            is the splited command/line from text file.
     */
    public void setUp(String[] cline) {
        this.line = cline;
        
    }

    /**
     * Compare the first word of line with possible commands. Calls private
     * methods in order to proceed.
     */
    public void process() {
        com = line[0]; // command

        if (com.equals("insert")) {
            regions = 
            insertRectangle(name, regions);
        }
        else if (com.equals("regionsearch")) {
            regions = 
            regionSearch(regions);
        }
        else if (com.equals("remove")) {
            // 2 Cases for remove command.
            // if there the length of array is 2, there are only command and the
            // name of rectangle
            if (line.length == 2) {
                removeByName();
            }
            else {
                //length of array is not 2. Most likely going to be just 4.
                //Meaning that it will be regions/coordinates.
                regions = 
                removeByCoor(regions);
            }
        }
        else if (com.equals("intersections")) {
            intersectSearch();
        }
        else if (com.equals("dump")) {
            dump();
        }
        else if (com.equals("search")) {
            searchByName();
        }
    }

    /**
     * converts String to Integer.
     */
    private int[] convertValues() {
        int[] arr = new int[4];
        
        int i = 0;
        if (com.equals("insert")) {
            i++;
        }
        
        arr[0] = Integer.parseInt(line[1 + i]);
        arr[1] = Integer.parseInt(line[2 + i]);
        arr[2] = Integer.parseInt(line[3 + i]);
        arr[3] = Integer.parseInt(line[4 + i]);
        
        return arr;

    }

    /**
     * gets called when the command line is insert name x y w h
     * 
     * calls the insert method in Container class.
     */

    private void insertRectangle() {
        // create a rectagle object if the rectangle fits under 1024

        convertValues();
        c.insert(line[1], x, y, w, h);

    }

    /**
     * gets called when the command line is remove name
     * 
     * calls the remove method in Container class.
     */
    private void removeByName() {

        c.remove(line[1]);

    }

    /**
     * gets called when the command line is remove x y w h
     * 
     * calls the remove method in Container class.
     */
    private void removeByCoor() {
        convertValues();
        c.remove(x, y, w, h);
    }

    /**
     * gets called when the command line is regionseaerch
     * 
     * calls the regionSearch method in Container class.
     */
    private void regionSearch() {
        convertValues();
        c.regionSearch(x, y, w, h);

    }

    /**
     * gets called when the command line is intersection
     * 
     * calls the intersect method in Container class.
     */
    private void intersectSearch() {
        c.intersections();

    }

    /**
     * gets called when the command line is dump
     * 
     * calls the dump method in Container class.
     */
    private void dump() {
        c.dump();
    }

    /**
     * gets called when the command line is search name
     * 
     * calls the search(String) method in Container class.
     */
    private void searchByName() {
        c.search(line[1]);
    }
}
