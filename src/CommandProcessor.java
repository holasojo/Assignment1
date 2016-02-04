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
     * Set up before it actually gets processed.
     * cline is the command that gets passed in from the CommandParser class.
     * 
     * @param cline is the line from text file.
     */
    public void setUp(String[] cline) {
        this.line = cline;
    }

    /**
     * Compare the first word of line with possible commands. and calls private
     * methods in order to proceed.
     */
    public void process() {
        com = line[0];

        if (com.equals("insert")) {
            insertRectangle();
        }
        else if (com.equals("regionsearch")) {
            regionSearch();
        }
        else if (com.equals("remove")) {
            if (line.length == 2) {
                removeByName();
            }
            else {
                removeByCoor();
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
    private void convertValues() {
        int i = 0;
        if (com.equals("insert")) {
            i++;
        }
        x = Integer.parseInt(line[1 + i]);
        y = Integer.parseInt(line[2 + i]);
        w = Integer.parseInt(line[3 + i]);
        h = Integer.parseInt(line[4 + i]);

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
