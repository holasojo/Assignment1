/**
 * Tests KVPair class
 * @author SOJO
 * @author sshumway
 * @version 01/29/2016
 *
 */
public class KVPairTest extends student.TestCase {

    /**
     * test class
     */
    public void test() {

        KVPair<String, RectangleValue> kv1 = new KVPair<String, RectangleValue>(
                "a", new RectangleValue(1, 1, 1, 1));
        KVPair<String, RectangleValue> kv2 = new KVPair<String, RectangleValue>(
                "a", new RectangleValue(1, 1, 1, 1));
        KVPair<String, RectangleValue> kv3 = new KVPair<String, RectangleValue>(
                "b", new RectangleValue(1, 1, 1, 1));
        KVPair<String, RectangleValue> kv4 = new KVPair<String, RectangleValue>(
                "c", new RectangleValue(1, 1, 1, 1));
        assertEquals(kv1.compareTo(kv2), 0);
        assertEquals(kv1.compareTo(kv3), -1);
        assertEquals(kv4.compareTo(kv3), 1);
        assertEquals(kv1.compareTo(kv2.key()), 0);
        assertEquals(kv1.compareTo(kv3.key()), -1);
        assertEquals(kv4.compareTo(kv3.key()), 1);

    }

}
