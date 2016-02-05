/**
 * Test for SkipList
 * 
 * @author SOJO
 * @author sshumway
 * @version 02/01/2016
 */
public class SkipListTest extends student.TestCase {

    private SkipList<String, RectangleValue> list;
    private Container con;
    private Container con2;
    private Container con3;

    /**
     * constructor. empty.
     */
    public SkipListTest() {

    }

    /**
     * set up for testing
     */
    public void setUp() {
        con = new Container();
        con2 = new Container();

        con3 = new Container();

        con3.insert("A", 0, 0, 1, 1);
        con3.insert("B", 0, 0, 1024, 1024);
        con3.insert("C", 1, 1, 1, 1);
        con3.insert("E", 1023, 1023, 1, 1);
        con3.insert("E", 0, 1023, 1, 1);
        con3.insert("E", 1023, 0, 1, 1);
        con3.insert("C", 1, 1, 1, 1);
        con3.insert("C", 1, 1, 1, 1);
        con3.insert("C", 1, 1, 1, 1);
        systemOut().clearHistory();
    }

    /**
     * testing search
     */
    public void testSearch() {
        list = con2.getList();
        assertNotNull(list);
        con2.search(null);
        assertFuzzyEquals("Rectangle not found: null\n", 
                systemOut().getHistory());
        assertEquals(0, list.size());
        systemOut().clearHistory();
        con2.search("A");
        assertFuzzyEquals("Rectangle not found: A\n", systemOut().getHistory());

        con2.insert("A", 0, 0, 1, 1);
        systemOut().clearHistory();
        con2.search("A");
        assertFuzzyEquals("Rectangles found:\nA 0, 0, 1, 1\n", 
                systemOut().getHistory());
        assertEquals(1, list.size());
        con2.insert("B", 0, 0, 1024, 1024);
        assertEquals(2, list.size());
        con2.insert("C", 1, 1, 1, 1);
        assertEquals(3, list.size());
        systemOut().clearHistory();

        con2.search("Z");
        assertFuzzyEquals("Rectangle not found: Z\n", systemOut().getHistory());
        con2.insert("E", 1023, 1023, 1, 1);
        assertEquals(4, list.size());
        con2.insert("E", 0, 1023, 1, 1);
        assertEquals(5, list.size());
        con2.insert("E", 1023, 0, 1, 1);
        systemOut().clearHistory();

        con2.search("E");
        assertFuzzyEquals("Rectangles found:\nE 1023, 0, 1, 1\nE 0, 1023, 1, "
                + "1\nE 1023, 1023, 1, 1\n", systemOut().getHistory());
        assertEquals(6, list.size());
        con2.insert("C", 1, 1, 1, 1);
        con2.insert("C", 1, 1, 1, 1);
        con2.insert("C", 1, 1, 1, 1);
        systemOut().clearHistory();
        con2.search("C");
        assertFuzzyEquals("Rectangles found:\nC 1, 1, 1, 1\nC 1, 1, 1, 1\nC " 
                + "1, 1, 1, 1\nC 1, 1, 1, 1\n",
                    systemOut().getHistory());
        systemOut().clearHistory();

        con2.search("D");
        assertFuzzyEquals("Rectangle not found: D\n", systemOut().getHistory());

    }

    /**
     * testing removing with x,y,w,h
     */
    public void testRemoveByRegion() {
        // remove from the empty list
        con.remove(1, 1, 1, 1);
        assertEquals(0, con.getList().size());
        con.remove(-1, -1, -1, -1);
        assertEquals(0, con.getList().size());
        // list now has rectangles inserted
        list = con3.getList();
        con3.remove(0, 0, 1, 1);
        assertEquals(8, list.size());
        con3.remove(0, 0, 1024, 1024);
        assertEquals(7, list.size());
        // not in the list
        con3.remove(3, 3, 100, 100);
        assertEquals(7, list.size());
        // rectangle not valid
        con3.remove(0, 0, -1, -1);
        assertEquals(7, list.size());
        con3.insert("Z", 3, 3, 3, 3);
        con3.remove(3, 3, 3, 3);
        assertEquals(7, list.size());

    }

    /**
     * test regionSearch
     */
    public void testRegionSearch() {
        // w > 0 && h > 0
        assertTrue(con3.regionSearch(1, 1, 2, 2));
        // w <= 0 && h <= 0
        assertFalse(con3.regionSearch(1, 1, -1, -20));
        assertFalse(con3.regionSearch(1, 1, 0, 0));
        // just w<=0
        assertFalse(con3.regionSearch(1, 1, 0, 1));
        assertFalse(con3.regionSearch(1, 1, -1, 1));
        // just h<=0
        assertFalse(con3.regionSearch(1, 1, 1, 0));
        assertFalse(con3.regionSearch(1, 1, 1, -1));
        // w > 0 && h > 0
        assertTrue(con3.regionSearch(-1, -1, 2, 2));
        // w <= 0 && h <= 0
        assertFalse(con3.regionSearch(-1, -1, -1, -1));
        assertFalse(con3.regionSearch(-1, -1, 0, 0));
        // just w<=0
        assertFalse(con3.regionSearch(-1, -1, 0, 20));
        assertFalse(con3.regionSearch(-1, -1, -1, 20));
        // just h<=0
        assertFalse(con3.regionSearch(-1, -1, 1, 0));
        assertFalse(con3.regionSearch(-1, -1, 1, -1));

    }

    /**
     * Insert rejections.
     */
    public void testInsert1() {
        list = con.getList();
        assertNotNull(list); // not null by default
        assertEquals(list.size(), 0);
        con.insert("hello", -1, -20, 3, 4);
        assertEquals(0, list.size());
        con.insert("it's", 7, -8, 1, 3);
        assertEquals(0, list.size());
        con.insert("me", 0, 0, 11, 0);
        assertEquals(0, list.size());
        con.insert("from", 0, 0, 1025, 1025);
        assertEquals(0, list.size());
        con.insert("thousand", 1, 1, 0, 0);
        assertEquals(0, list.size());
        con.insert("miles", 1, 1, -1, -2);
        assertEquals(0, list.size());
        con.insert("big", 1, 1, 1025, 1025);
        assertEquals(0, list.size());
        con.insert("bang", 1, 1, 1022, 1025);
        assertEquals(0, list.size());
        con.getList().dump();

    }

    /**
     * Insert actual rectangles.
     */
    public void testInsert2() {
        list = con2.getList();
        assertNotNull(list);

    }

    /**
     * testing remove
     */
    public void testRemove() {
        list = con3.getList();
        assertNotNull(list);
        con3.remove("C");
        assertEquals(8, list.size());
        con3.remove("A");
        assertEquals(7, list.size());
        con3.remove("C");
        con3.remove("C");
        con3.remove("C");
        assertEquals(4, list.size());
        con3.remove("C"); // trying to remove when it's not there.
        assertEquals(4, list.size());
        list.dump();
        con3.remove("B");
        con3.remove("D");
        list.dump();
        con3.remove("E");
        assertEquals(2, list.size());
        con3.remove("E");
        con3.remove("E"); // rmeoved all E's
        assertEquals(0, list.size());
        assertNull(list.remove(new RectangleValue(1, 2, 3, 4)));
        assertNull(list.remove(new RectangleValue(-1, -1, -2, -2)));
        con3.insert("B", 0, 0, 1024, 1024);
        con3.insert("B", 0, 0, 1024, 1024);
        con3.insert("B", 0, 0, 1024, 1024);

        RectangleValue rec = new RectangleValue(0, 0, 1024, 1024);
        con3.dump();
        assertNotNull(list.remove(rec));
        assertNull(list.remove(new RectangleValue(1, 2, 3, 4)));
        assertEquals(list.remove(rec).value().getWidth(), 1024);

        assertEquals(1, list.size());
        list.dump();
        assertEquals(0, list.remove(rec).value().getPosY());

        list.dump();

    }

    /**
     * test regionsearch
     */
    public void testRegionSearch2() {
        list = con.getList();
        RectangleValue region = new RectangleValue(15, 15, 100, 100);
        RectangleValue region2 = new RectangleValue(-10, -10, 200, 200);
        RectangleValue region3 = new RectangleValue(200, 200, 10, 10);
        RectangleValue region4 = new RectangleValue(2, 2, 4, 3);
        RectangleValue region5 = new RectangleValue(110, 110, 90, 90);

        con.insert("a", 10, 10, 30, 40);
        con.insert("b", 115, 115, 20, 20);
        con.insert("c", 180, 180, 10, 10);

        con.insert("r1", 0, 0, 3, 4);
        con.insert("r2", 5, 0, 5, 3);

        assertEquals(1, list.regionsearch(region));
        assertEquals(5, list.regionsearch(region2));
        assertEquals(0, list.regionsearch(region3));
        assertEquals(2, list.regionsearch(region5));
        assertEquals(2, list.regionsearch(region4));

    }

    /**
     * testing intersection
     */
    public void testIntersection() {
        list = con.getList();

        con.insert("a", 10, 10, 30, 40);
        assertEquals(0, list.intersections());
        con.insert("b", 115, 115, 20, 20);
        assertEquals(0, list.intersections());
        con.insert("c", 180, 180, 10, 10);
        assertEquals(0, list.intersections());
        con.insert("d", 10, 13, 3, 4);
        assertEquals(2, list.intersections());
        con.insert("e", 9, 7, 5, 10);
        assertEquals(6, list.intersections());

    }

}