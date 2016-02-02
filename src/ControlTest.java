/**
 * Tests Control class
 * 
 * @author SOJO
 * @author sshumway
 * @version 02/01/2016
 *
 */
public class ControlTest extends student.TestCase {

    /**
     * test class
     */
    public void test() {
        String[] str = new String[1];

        Control con = new Control(str);

        assertFalse(con.setup());

        str[0] = "SyntaxTest.txt";
        assertTrue(con.setup());

    }

}
