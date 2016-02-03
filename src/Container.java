/**
 * It's a container class. Communicate with SkipList and another data structure
 * that will be added for project2.
 * 
 * @author SOJO
 * @author sschumway
 * @version 01/29/2016
 *
 */
public class Container {

    private SkipList<String, RectangleValue> list;

    /**
     * constructor. sets the list.
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
        if (fits(x, y, w, h)) {
            RectangleValue rec = new RectangleValue(x, y, w, h);
            KVPair<String, RectangleValue> kv = new KVPair<String, RectangleValue>(name, rec);
            // insert kv into the list
            list.insert(kv);
            System.out.println("Rectangle inserted: (" + name + ", " + x +
                    ", " + y + ", " + w + ", " + h + ")");
        }
        else {
            System.out.println("Rectangle rejected: (" + name + ", " + x + 
                    ", " + y + ", " + w + ", " + h + ")");
        }
    }

    /**
     * checking bounds of the rectangle
     * 
     * @param x
     *            is x-Position
     * @param y
     *            is y-Position
     * @param w
     *            is width of the rectangle
     * @param h
     *            is height of the rectangle
     * @return true if the size of rectangle fits within the unit
     */
    public boolean fits(int x, int y, int w, int h) {

        return (w > 0 && h > 0) && (x >= 0 && y >= 0) && 
                ((x + w <= 1024) && (y + h <= 1024));

    }

    /**
     * remove by name
     * 
     * @param name
     *            is the key value
     */
    public void remove(String name) {

        KVPair<String, RectangleValue> toRemove 
            = new KVPair<String, RectangleValue>(name, 
                new RectangleValue(1, 1, 1, 1));

        KVPair<String, RectangleValue> removed = list.remove(toRemove);

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
        if (fits(x, y, w, h)) {
            RectangleValue rec = new RectangleValue(x, y, w, h);
            KVPair<String, RectangleValue> removed = list.remove(rec);
            if (removed != null) {
                System.out.println("Rectangle removed: " + "(" 
                        + removed.toString() + ")");
            }
            else {

                System.out.println("Rectangle not removed: " + rec.toString());
            }
        }
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
        if (w > 0) {
            if (h > 0) {
                list.regionsearch(new RectangleValue(x, y, w, h));
                return true;
            }
            return false;
        }
        else {

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
        // KVPair<String, RectangleValue> pair = new KVPair<String,
        // RectangleValue>(name, null);
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
