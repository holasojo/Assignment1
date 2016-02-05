/**
 * Communicate with SkipList and another data structure that will be added for
 * project2.
 * 
 * @author sohyun
 * @author sschumway
 * @version 01/29/2016
 *
 */
public class Container {

    // skiplist
    private SkipList<String, RectangleValue> list;

    /**
     * constructor. Initializing the SkipList.
     */
    public Container() {
        list = new SkipList<String, RectangleValue>();

    }

    /**
     * Insert method. Creates RectangleValue and KVPair. Calls insert() method
     * in SkipList class.
     * 
     * @param name
     *            is the key
     * @param x
     *            is x-pos
     * @param y
     *            is y-pos
     * @param w
     *            is width
     * @param h
     *            is height
     */
    public void insert(String name, int x, int y, int w, int h) {
        // check if the rectangle fits under the requirement
        if (fits(x, y, w, h)) {
            // create a rectangle
            RectangleValue rec = new RectangleValue(x, y, w, h);
            // create KVPair with the rectangle object
            KVPair<String, RectangleValue> kv 
                = new KVPair<String, RectangleValue>(name, rec);
            // insert kv into the list
            list.insert(kv);
            System.out.println("Rectangle inserted: (" + 
                    name + ", " + x + ", " + y + ", " + w + ", " + h + ")");
        }
        else {
            System.out.println("Rectangle rejected: (" + 
                    name + ", " + x + ", " + y + ", " + w + ", " + h + ")");
        }
    }

    /**
     * checking bounds of the rectangle
     * 
     * @param x
     *            is x-Position. Has to be greater than 0
     * @param y
     *            is y-Position. Has to be greater than 0
     * @param w
     *            is width of the rectangle
     * @param h
     *            is height of the rectangle
     * @return true if the size of rectangle fits within the unit
     */
    public boolean fits(int x, int y, int w, int h) {

        return (w > 0 && h > 0) && (x >= 0 && y >= 0) 
                && ((x + w <= 1024) && (y + h <= 1024));

    }

    /**
     * remove by name
     * 
     * @param name
     *            is the key value
     */
    public void remove(String name) {

        // creating a KVPair to pass into the method
        KVPair<String, RectangleValue> toRemove 
            = new KVPair<String, RectangleValue>(name,
                new RectangleValue(1, 1, 1, 1));
        // the one actually got removed
        KVPair<String, RectangleValue> removed = list.remove(toRemove);

        // if there was a rectangle and got removed,
        // print out that it was removed
        // if not, print out Rectangle not removed
        if (removed != null) {

            System.out.println("Rectangle removed: " + "(" + 
                    removed.toString() + ")");
        }
        else {

            System.out.println("Rectangle not removed: " + name);
        }
    }

    /**
     * calls remove method in skipList class.
     * 
     * @param x
     *            is x-pos
     * @param y
     *            is y-pos
     * @param w
     *            is width
     * @param h
     *            is height
     * 
     */
    public void remove(int x, int y, int w, int h) {
        // check the values meet conditions
        if (fits(x, y, w, h)) {
            // create a new rectangle value with passed in values
            RectangleValue rec = new RectangleValue(x, y, w, h);
            // the one got removed
            KVPair<String, RectangleValue> removed = list.remove(rec);
            if (removed != null) {
                // rectangle was in the skip list and got removed
                System.out.println("Rectangle removed: " + "(" 
                        + removed.toString() + ")");
            }
            else {
                // rectangle was not in the skiplist
                System.out.println("Rectangle not removed: " + rec.toString());
            }
        }
        // if the rectangle size and position not meet requirements, print out
        // rectangle rejected
        else {

            System.out.println("Rectangle rejected: (" + x + ", " 
                    + y + ", " + w + ", " + h + ")");
        }

    }

    /**
     * calls the regionSearch method in skipList class.
     * 
     * 
     * @param x
     *            is x-pos
     * @param y
     *            is y-pos
     * @param w
     *            is width
     * @param h
     *            is height
     * @return false when the rectangle is not within the region.
     */
    public boolean regionSearch(int x, int y, int w, int h) {
        // checking width and height
        if (w > 0 && h > 0) {
            // start region search
            list.regionsearch(new RectangleValue(x, y, w, h));
            return true;

        }
        else {
            // print out rejection when w <= 0 and h <= 0
            System.out.println("Rectangle rejected: (" + x + ", " + 
                    y + ", " + w + ", " + h + ")");
            return false;
        }

    }

    /**
     * calls the search method in skipList class.
     * 
     * @param name
     *            is rectangle name
     * @return true if the rectangle is found.
     */
    public boolean search(String name) {

        return list.search(name) != null;

    }

    /**
     * calls the intersect method in skipList class.
     */
    public void intersections() {
        list.intersections();
    }

    /**
     * calls the dump method in skipList class.
     */
    public void dump() {
        list.dump();
    }

    /**
     * for testing
     * 
     * @return the list.
     */
    public SkipList<String, RectangleValue> getList() {
        return list;
    }

}
