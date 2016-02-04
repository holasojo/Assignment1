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
 * @param <E>
 */
class SkipList<K extends Comparable<K>, E> {

    private SkipNode<KVPair<K, E>> head;
    private int level;
    private int size;
    private Random value;

    /**
     * constructor for skiplist
     */
    public SkipList() {
        head = new SkipNode<KVPair<K, E>>(null, 0);
        level = 0;
        size = 0;
        value = new Random();
    }

    /**
     * adjust head when the newLevel is bigger
     * 
     * @param newLevel
     *            the new head
     */
    private void adjustHead(int newLevel) {
        SkipNode<KVPair<K, E>> temp = head;
        head = new SkipNode<KVPair<K, E>>(null, newLevel);

        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }

        level = newLevel;
    }

    /**
     * shows all the existing rectangles in the list
     */
    public void dump() {
        SkipNode<KVPair<K, E>> temp = head;
        System.out.println("SkipList dump: ");

        while (temp.forward[0] != null) {
            System.out.println("Node has depth " + temp.forward.length
                    + ", Value (" + temp.getData() + ")");
            temp = temp.forward[0];
        }
        System.out.println("Node has depth " + temp.forward.length
                + ", Value (" + temp.getData() + ")");
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
    int randomLevel() {
        int lev;
        for (lev = 0; value.nextInt(2) == 0; lev++) {
        }
        return lev;
    }

    /**
     * Insert a record into the skiplist
     * 
     * @param it
     *            is a new record
     * @return true if gets inserted
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean insert(KVPair<K, E> it) {
        int newLevel = randomLevel();
        Comparable<K> k = it.key();
        if (level < newLevel)
            adjustHead(newLevel);

        // Generic array allocation
        SkipNode[] update = (SkipNode[]) Array.newInstance(
                SkipList.SkipNode.class, level + 1);
        SkipNode x = head; // Start at header node

        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null)
                    && (k.compareTo(((KVPair<K, E>) (x.forward[i])
                            .getData()).key()) > 0))
                x = x.forward[i];
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who y points to
        }
        size++; // Increment dictionary size
        return true;
    }

    /**
     * removes KVPair
     * 
     * @param it
     *            gets removed
     * @return KVPair when it actually gets removed
     */
    public KVPair<K, E> remove(KVPair<K, E> it) {

        Comparable<K> k = it.key();

        @SuppressWarnings("unchecked")
        // Generic array allocation
        SkipNode<KVPair<K, E>>[] update = (SkipNode[]) Array.newInstance(
                SkipList.SkipNode.class, level + 1);
        SkipList<K, E>.SkipNode<KVPair<K, E>> x = head; // Start at header node

        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null)
                    && (k.compareTo(((KVPair<K, E>) (x.forward[i])
                            .getData()).key()) > 0))
                x = x.forward[i];
            update[i] = x; // Track end at level i
        }

        if (x.forward[0] != null
                && (k.compareTo(((KVPair<K, E>) (x.forward[0]).getData())
                        .key()) == 0)) {

            SkipList<K, E>.SkipNode<KVPair<K, E>> deleted = x.forward[0];

            for (int i = 0; i < deleted.forward.length; i++) {
                update[i].forward[i] = deleted.forward[i]; // what deleted node
                                                           // points to
            }
            size--;
            return deleted.getData();
        }

        return null;
    }

    /**
     * remove by regions
     * 
     * @param v
     *            is the requested one to get removed.
     * @return kvpair if got removed.
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, E> remove(E val) {
        SkipList<K, E>.SkipNode<KVPair<K, E>> x = head; // Start at header node
        SkipNode<KVPair<K, E>>[] update = (SkipNode[]) Array
                .newInstance(SkipList.SkipNode.class, level + 1);
        
        while ((x.forward[0] != null)
                && !(val.equals((x.data.value())))) {
            for(int i = x.forward.length - 1; i >=0; i--)
            {
                if(val.equals(x.forward[i].data.value()))
                    update[i] = x.forward[i];
            }
            x = x.forward[0];
        }

        

         if (x != null && x.data.value().equals(val)) {
//
             SkipList<K, E>.SkipNode<KVPair<K, E>> deletedNode = x;
//            Comparable<K> k = x.data.key();
//            x = head;
//
//            SkipNode<KVPair<K, E>>[] update = (SkipNode[]) Array
//                    .newInstance(SkipList.SkipNode.class, level + 1);
//
//            for (int i = level; i >= 0; i--) { // Find insert position
//                while ((x.forward[i] != null)
//                        && (k.compareTo(((KVPair<K, E>) (x.forward[i])
//                                .getData()).key()) > 0))
//                    x = x.forward[i];
//                update[i] = x; // Track end at level i
//            }
//
//            for (int i = update.length - 1; i >= 0; i--) {
//                if (update[i].forward[i] == null
//                        || update[i].forward[i].data.key() != k) {
//                    continue;
//                }
//                while (!update[i].forward[i].data.value().equals(v)) {
//                    update[i] = update[i].forward[i];
//                }
//            }

            for (int i = 0; i < deletedNode.forward.length; i++) {
                update[i].forward[i] = deletedNode.forward[i];
            }

            size--;
            return deletedNode.data;
        }
        return null;
    }

    /**
     * searches KVPair through the list
     * 
     * @param key
     *            is the name of rectangle
     * @return KVPair when found
     */
    public KVPair<K, E> search(Comparable<K> key) {
        SkipNode<KVPair<K, E>> x = head; // Dummy header node

        for (int i = level; i >= 0; i--) { // For each level...

            while ((x.forward[i] != null) && // go forward
                    (key.compareTo(x.forward[i].getData().key()) > 0))
                x = x.forward[i]; // Go one last step
        }

        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (key.compareTo(x.getData().key()) == 0)) {

            System.out.println("Rectangles found:");
            System.out.println(x.getData());

            while (x.forward[0] != null
                    && key.compareTo(x.forward[0].getData().key()) == 0) {
                x = x.forward[0];
                System.out.println(x.getData());
            }
            return x.getData();
        }
        else
            System.out.println("Rectangle not found: " + key);
        return null;
    }

    /**
     * all rectangles currently in the database that intersect the query
     * rectangle
     * 
     * @param rec
     *            is the range
     * @return the number of intersections
     */
    public int regionsearch(RectangleValue rec) {

        int intersections = 0;

        System.out.println("Rectangles intersecting region " + "("
                + rec.toString() + ")" + ":");
        SkipList<K, E>.SkipNode<KVPair<K, E>> x = head;

        RectangleValue val;

        for (int i = 0; i < size; i++) {
            x = x.forward[0];
            val = (RectangleValue) x.data.value();
            if (val.intersect(rec)) {
                intersections++;
                System.out.println("(" + x.data.toString() + ")");
            }
        }
        return intersections;
    }

    /**
     * Checks for rectangle intersections within the skiplist.
     * @return number of intersections
     */
    public int intersections() {

        System.out.println("Intersection pairs: ");

        int num = 0;

        SkipList<K, E>.SkipNode<KVPair<K, E>> outerNode = head;
        SkipList<K, E>.SkipNode<KVPair<K, E>> innerNode = head;

        RectangleValue outerVal;
        RectangleValue innerVal;

        for (int i = 0; i < size; i++) {
            outerNode = outerNode.forward[0];
            outerVal = (RectangleValue) outerNode.data.value();

            for (int j = 0; j < size; j++) {
                innerNode = innerNode.forward[0];
                innerVal = (RectangleValue) innerNode.data.value();

                if (i != j) {
                    if (outerVal.intersect(innerVal)) {
                        System.out.println("(" + outerNode.data.toString()
                                + " | " + innerNode.data.toString() + ")");
                        num++;
                    }
                }
            }
            innerNode = head;
        }
        return num;
    }

    /**
     * Source code example for "A Practical Introduction to Data Structures and
     * Algorithm Analysis, 3rd Edition (Java)" by Clifford A. Shaffer Copyright
     * 2008-2011 by Clifford A. Shaffer
     */

    public class SkipNode<T> {

        private T data;

        private SkipNode<T>[] forward;

        /**
         * creating a new node
         * 
         * @param value
         *            is RectangleValue
         * @param level
         *            is the level
         */
        @SuppressWarnings("unchecked")
        public SkipNode(T value, int level) {
            data = value;

            forward = (SkipNode<T>[]) new SkipNode[level + 1];

            for (int i = 0; i < level; i++) {
                forward[i] = null;
            }
        }

        /**
         * gets data. Aka RectangleValue
         * 
         * @return the value
         */
        public T getData() {
            return data;
        }

    }

}
