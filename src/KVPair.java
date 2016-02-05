/**
 * KVPair class. 
 * Will store String and RectangleValue for this project.
 * 
 * @author SOJO
 * @author sshumway
 * @version 01/29/2016
 *
 * @param <K>
 *            is Key
 * @param <E>
 *            is Value
 * 
 */
public class KVPair<K extends Comparable<K>, E> implements
        Comparable<KVPair<K, E>> {
    
    //key, String
    private K theKey;
    //value, RectangleValue
    private E theVal;

    /**
     * Constructor
     * 
     * @param k
     *            key
     * @param v
     *            value
     */
    KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }

    /**
     * Compares against the pair. But compare keys
     * 
     * @param it
     *            is KVPair
     * @return 0 if equal. 1 if greater. -1 if less
     */
    public int compareTo(KVPair<K, E> it) {
        return theKey.compareTo(it.key());
    }

    /**
     * Compares against key
     * 
     * @param it
     *            is key
     * @return 0 if equal. 1 if greater. -1 if less
     */
    public int compareTo(K it) {
        return theKey.compareTo(it);
    }

    /**
     * getter for key
     * 
     * @return key!
     */
    public K key() {
        return theKey;
    }

    /**
     * getter for value
     * 
     * @return value!
     */
    public E value() {
        return theVal;
    }

    /**
     * converts to string.
     * @return key and value
     */
    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }

}