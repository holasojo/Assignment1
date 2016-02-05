import java.lang.reflect.Array;
import java.util.Random;

/** Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)" 
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer
 */

/** Skiplist implementation for Dictionary ADT */
/**
 * Checks the inputfile and pass it into CommandParser class to parse each line
 * 
 * @author SOJO
 * @author sshumway
 * @version 01/25/2016
 * @param <K>
 * @param <E>
 */
class SkipList<K extends Comparable<K>, E> {

    private SkipNode head; // pointer to head node of skiplist
    private int level; // the greatest level(depth = level + 1) of any skipnode
                       // in the skiplist
    private int size; // the number of skip nodes in the list not counting the
                      // head node
    private Random value; // used for geometric distribution to determine random
                          // level of a new skip nodes

    /**
     * Skiplist Constructor. Creates head node containing null data with a
     * forward array with depth of 1(level 0). Instantiates value object to be
     * used later when nodes are insterted into the list.
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        level = 0;
        size = 0;
        value = new Random();
    }

    /**
     * Adjusts the level of the head node when a node is inserted that has a
     * greater level than the current head node.
     * 
     * @param newLevel
     *            The new level of the head node.
     */
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(null, newLevel);

        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }

        level = newLevel;
    }

    /**
     * Prints the data stored in each skipnode in the list, starting at the
     * first node.
     */
    public void dump() {
        // temp variable used to walk down the skiplist
        SkipNode temp = head;
        System.out.println("SkipList dump: ");

        // loop used to walk down list and print what is stored in each skipnode
        while (temp.forward[0] != null) {
            System.out.println("Node has depth " + temp.forward.length 
                    + ", Value (" + temp.getData() + ")");
            temp = temp.forward[0]; // increments temp's postion in the list.
        }
        // last node isn't reached in loop, so must print its value
        System.out.println("Node has depth " + temp.forward.length
                + ", Value (" + temp.getData() + ")");
        // prints the number of nodes in the skiplist
        System.out.println("SkipList size is: " + size);
    }

    /**
     * size
     * 
     * @return size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Pick a level using a geometric distribution
     * 
     * @return a randomized number for new level
     */
    private int randomLevel() {
        int lev;
        for (lev = 0; value.nextInt(2) == 0; lev++) {
        }
        return lev;
    }

    /**
     * Methods to insert a KVPair into the skiplist.
     * 
     * @param it
     *            kvpair to be inserted
     * 
     * @return True if it gets inserted, false otherwise.
     */
    @SuppressWarnings({ "unchecked" })
    public boolean insert(KVPair<K, E> it) {
        // random level for the new node
        int newLevel = randomLevel();
        // The key to determine insert position.
        Comparable<K> k = it.key();
        // adjusts head node level when appropriate
        if (level < newLevel)
            adjustHead(newLevel);

        /*
         * allocates array of skipnodes to hold all the nodes that will point to
         * the new node
         */
        SkipNode[] update = (SkipNode[]) 
                Array.newInstance(SkipList.SkipNode.class, level + 1);

        SkipNode x = head; // Start at header node

        for (int i = level; i >= 0; i--) { // Finds insert position by using
                                           // key.
            while ((x.forward[i] != null) && (k.compareTo(((KVPair<K, E>) 
                    (x.forward[i]).getData()).key()) > 0))
                x = x.forward[i];
            update[i] = x; // Tracks the furthest node reached at level i
        }
        // new node to be inserted
        x = new SkipNode(it, newLevel);
        // sets the new nodes forward array to point to what the previous nodes
        // pointed to.(inserts the new node into the list.
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            // updates nodes that should point to the new node.
            update[i].forward[i] = x;
        }
        size++; // Increment dictionary size
        return true;
    }

    /**
     * Removes a KVPair from the list based on key, if it exists.
     * 
     * @param it
     *            The KVPair to remove.
     * 
     * @return KVPair The removed pair, null if its not in the list.
     */
    public KVPair<K, E> remove(KVPair<K, E> it) {

        Comparable<K> k = it.key();

        @SuppressWarnings("unchecked")
        SkipNode[] update = (SkipNode[])
            Array.newInstance(SkipList.SkipNode.class, level + 1);
        SkipNode x = head; // Start at header node

        for (int i = level; i >= 0; i--) { // finds remove position.
            while ((x.forward[i] != null) && (k.compareTo(((KVPair<K, E>) 
                    (x.forward[i]).getData()).key()) > 0))
                x = x.forward[i];
            update[i] = x; // Track farthest node reached at level i.
        }
        // Checks to make sure node was found.
        if (x.forward[0] != null && (k.compareTo(((KVPair<K, E>) 
                (x.forward[0]).getData()).key()) == 0)) {

            // go forward once more to access node that should be removed.
            SkipNode deleted = x.forward[0];

            for (int i = 0; i < deleted.forward.length; i++) {
                // updates farthest nodes reached that point to removed node
                // to point to
                // node in front of removed node.
                update[i].forward[i] = deleted.forward[i];
            }
            size--;
            return deleted.getData();
        }

        return null;
    }

    /**
     * Removes node from SkipList based on the value stored in KVPair.
     * 
     * 
     * @param val
     *            The value being searched for.
     * 
     * @return KVPair in the removed node if the value was found.
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, E> remove(E val) {

        SkipNode x = head; // Start at header node

        // holds nodes that will need to be updated when value is found
        // and removed.
        SkipNode[] update = (SkipNode[]) 
                Array.newInstance(SkipList.SkipNode.class, level + 1);

        // initializes the update array to header node for every index.
        // Accounts for the condition of the first node containing the value,
        // because loop won't be entered if it is found immediately.
        for (int i = 0; i <= level; i++) {
            update[i] = head;
        }

        // walks down the list looking for the value.
        while ((x.forward[0] != null) && 
                !(val.equals((x.forward[0].data.value())))) {

            // update array pointers to nodes that current node
            // points to
            for (int i = x.forward.length - 1; i >= 0; i--) {

                update[i] = x.forward[i];
            }
            // move forward to next position
            x = x.forward[0];
        }

        x = x.forward[0]; // go forward once more

        // checks to make sure node with the value was found.
        if (x != null) {

            SkipNode deletedNode = x;

            // updates forward array of previous node to point to what the
            // forward array
            // of the removed node pointed to.
            for (int i = 0; i < deletedNode.forward.length; i++) {
                update[i].forward[i] = deletedNode.forward[i];
            }

            size--;
            return deletedNode.data;
        }
        return null;
    }

    /**
     * Searches for KVPairs with the matching key in the list.
     * 
     * @param key
     *            The key being searched for.
     * 
     */
    public void search(Comparable<K> key) {
        SkipNode x = head; // header node

        for (int i = level; i >= 0; i--) { // For each level...

            while ((x.forward[i] != null) && // go forward
                    (key.compareTo(x.forward[i].getData().key()) > 0))
                x = x.forward[i]; // Go one last step
        }

        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (key.compareTo(x.getData().key()) == 0)) {

            System.out.println("Rectangles found:");
            System.out.println(x.getData());

            // walks down the list form first instance of found key, to print
            // remaining KVPair's
            // with the same key, if they exist.
            while (x.forward[0] != null && 
                    key.compareTo(x.forward[0].getData().key()) == 0) {
                x = x.forward[0];
                System.out.println(x.getData());
            }
        }
        else
            System.out.println("Rectangle not found: " + key);
    }

    /**
     * Searches list for rectangles that intersect the query region.
     * 
     * @param rec
     *            The rectangle region that is being used to test for
     *            intersections
     * 
     * @return The number of intersections
     */
    public int regionsearch(RectangleValue rec) {

        int intersections = 0;

        System.out.println("Rectangles intersecting region " 
                + "(" + rec.toString() + ")" + ":");

        SkipNode x = head;
        RectangleValue val;

        //traversing through the list
        for (int i = 0; i < size; i++) {
            //node that we are comparing with
            x = x.forward[0];

            //getting the value of x for comparison
            val = (RectangleValue) x.data.value();
            
            //if intersect, print out.
            if (val.intersect(rec)) {

                intersections++;
                System.out.println("(" + x.data.toString() + ")");

            }
        }
        return intersections;
    }

    /**
     * Determines if any rectangles within the skiplist intersect with each
     * other. Intersections are double counted with this implementation.
     * 
     * @return number of intersections
     */
    public int intersections() {

        System.out.println("Intersection pairs: ");

        int numIntersections = 0;

        //the one we want to see if any other rectangles are intersecting with
        //this outerNode
        SkipNode outerNode = head;
        //node that we traverse through the list. 
        SkipNode innerNode = head;

        //value of outerNode and innerNode
        RectangleValue outerVal;
        RectangleValue innerVal;

        //starting from the first node
        for (int i = 0; i < size; i++) {
            // move forward to start a comparison
            outerNode = outerNode.forward[0];
            outerVal = (RectangleValue) outerNode.data.value();

            //traversing the list
            for (int j = 0; j < size; j++) {
                innerNode = innerNode.forward[0];
                innerVal = (RectangleValue) innerNode.data.value();
                // check if the two rectangles are identical
                // at the same location.
                if (i != j) {
                    //print out if they intersect
                    if (outerVal.intersect(innerVal)) {
                        System.out.println("(" + outerNode.data.toString() 
                            + " | " + innerNode.data.toString() + ")");
                        numIntersections++;
                    }
                }
            }
            //resetting the innerNode to start traversing from the head
            innerNode = head;
        }
        return numIntersections;
    }

    /**
     * Implementation of a skip node class. SkipNode object holds KVPairs and an
     * array of references to proceeding SkipNodes at each level in the
     * Skiplist.
     */

    public class SkipNode {

        private KVPair<K, E> data;

        private SkipNode[] forward;

        /**
         * creating a new node
         * 
         * @param value
         *            is RectangleValue
         * @param level
         *            is the level
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, E> value, int level) {
            data = value;

            forward = (SkipNode[]) 
                    Array.newInstance(SkipList.SkipNode.class, level + 1);

            for (int i = 0; i < level; i++) {
                forward[i] = null;
            }
        }

        /**
         * gets data. Aka RectangleValue
         * 
         * @return the value
         */
        public KVPair<K, E> getData() {
            return data;
        }

    }

}
