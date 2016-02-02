/**
 * Tests the RectangleValueClass
 * 
 * @author sshumway
 * @author sohyun
 * @version 02/1/2016
 *
 */
public class RectangleValueTest extends student.TestCase {

    private RectangleValue rec;
    private RectangleValue rec2;
    private RectangleValue rec3;
    private RectangleValue rec4;

    private RectangleValue rec5;
    private RectangleValue rec6;
    private RectangleValue rec7;
    private RectangleValue rec8;
    private RectangleValue rec9;
    private RectangleValue rec10;
    private RectangleValue rec11;
    private RectangleValue rec12;
    private RectangleValue rec13;
    private RectangleValue rec14;
    private RectangleValue rec15;
    private RectangleValue rec16;
    private RectangleValue rec17;
    private RectangleValue rec18;
    private RectangleValue rec19;
    private RectangleValue rec20;
    private RectangleValue rec21;
    private RectangleValue rec22;
    private RectangleValue rec23;
    private RectangleValue rec24;
    private String str;

    /**
     * set up for testing
     */
    public void setUp() {
        rec = new RectangleValue(10, 20, 30, 40);
        rec2 = new RectangleValue(10, 20, 30, 40);
        rec3 = new RectangleValue(30, 70, 80, 10);
        rec5 = new RectangleValue(50, 20, 20, 15);
        rec6 = new RectangleValue(5, 40, 20, 15);
        rec7 = new RectangleValue(5, 10, 70, 15);
        rec8 = new RectangleValue(5, 10, 10, 150);
        rec9 = new RectangleValue(-10, -20, 60, 100);
        rec10 = new RectangleValue(5, 90, 60, 200);
        rec11 = new RectangleValue(10, 90, 60, 200);
        rec12 = new RectangleValue(5, 90, 30, 200);
        rec13 = new RectangleValue(5, 90, 60, 40);
        rec14 = new RectangleValue(5, 90, 30, 40);
        rec15 = new RectangleValue(10, 20, 10, 10);
        rec16 = new RectangleValue(10, 10, 30, 10);
        rec17 = new RectangleValue(1, 20, 10, 40);
        rec18 = new RectangleValue(10, 10, 10, 40);
        rec19 = new RectangleValue(1, 20, 30, 10);
        rec20 = new RectangleValue(1, 20, 30, 40);
        rec21 = new RectangleValue(10, 10, 30, 40);
        rec22 = new RectangleValue(10, 20, 10, 40);
        rec23 = new RectangleValue(10, 20, 30, 10);
        rec24 = new RectangleValue(0, 0, 0, 0);
        rec4 = null;
        str = "e";
    }

    /**
     * test equals method
     */
    public void testEquals() {
        // same
        assertTrue(rec.equals(rec2));
        // all different
        assertFalse(rec2.equals(rec3));
        // empty
        assertFalse(rec.equals(rec4));
        // different object
        assertFalse(rec.equals(str));
        // all different
        assertFalse(rec.equals(rec6));
        // all different
        assertFalse(rec.equals(rec7));
        // all different
        assertFalse(rec.equals(rec8));
        // same y
        assertFalse(rec.equals(rec5));
        //same x
        assertFalse(rec.equals(rec11));
        //same width
        assertFalse(rec.equals(rec12));
        //same height
        assertFalse(rec.equals(rec13));
        //same width and height
        assertFalse(rec.equals(rec14));
        //same x and y
        assertFalse(rec.equals(rec15));
        //same width and x
        assertFalse(rec.equals(rec16));
        //same height and y
        assertFalse(rec.equals(rec17));
        //same height and x
        assertFalse(rec.equals(rec18));
        //same width and y
        assertFalse(rec.equals(rec19));
        // all but x
        assertFalse(rec.equals(rec20));
        //all but y
        assertFalse(rec.equals(rec21));
        //all but w
        assertFalse(rec.equals(rec22));
        //all but h
        assertFalse(rec.equals(rec23));
        assertFalse(rec.equals(rec24));
        
    }

    /**
     * test intersect method
     */
    public void testIntersect() {
        assertTrue(rec.intersect(rec2));
        assertFalse(rec.intersect(rec3));
        assertFalse(rec.intersect(rec5));
        assertTrue(rec.intersect(rec9));
        assertTrue(rec.intersect(rec));
        assertTrue(rec2.intersect(rec9));
        assertTrue(rec3.intersect(rec9));
        assertTrue(rec6.intersect(rec9));
        assertTrue(rec7.intersect(rec9));
        assertTrue(rec8.intersect(rec9));
        assertFalse(rec5.intersect(rec9));
        assertTrue(rec9.intersect(rec9));
        assertFalse(rec10.intersect(rec9));
    }

}
