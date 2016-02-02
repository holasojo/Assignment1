import student.TestCase;

/**
 * @author ayaan
 * @version 2
 */
public class Rectangle1Test extends TestCase {

    /**
     * This method sets up the tests that follow.
     */
    public void setUp() {
        // Nothing here yet
    }

    // ----------------------------------------------------------
    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testRInit() {
        Rectangle1 dum = new Rectangle1();
        assertNotNull(dum);
        // args.length == 1
        String[] arr = new String[1];
        // args[0] == null
        arr[0] = null;
        Rectangle1.main(arr);
        assertFuzzyEquals("Check your arguments\n", systemOut()
                .getHistory());
        // args[0] != null
        arr[0] = "hi.txt";
        Rectangle1.main(arr);
        assertFuzzyEquals("Check your arguments\n", systemOut()
                .getHistory());

        // args.length != 1
        String[] arr2 = new String[2];
        // args[0] == null
        arr2[0] = null;
        arr2[1] = null;
        Rectangle1.main(arr2);
        assertFuzzyEquals("Check your arguments\nCheck your arguments\n",
                systemOut().getHistory());
        // args[0] != null
        arr2[0] = "hi.txt";
        arr2[1] = null;
        Rectangle1.main(arr2);
        assertFuzzyEquals(
                "Check your arguments\nCheck your arguments\nCheck "
                + "your arguments\n",
                systemOut().getHistory());

        

    }

}
