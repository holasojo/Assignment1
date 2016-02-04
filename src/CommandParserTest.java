import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
/**
 * Test for CommandParser class
 * @author sohyun
 * @author sshumway
 * @version 02/1/2016
 *
 */
public class CommandParserTest extends student.TestCase {

    /**
     * Checking the file.
     * @throws IOException if file is not found
     */
    @Test(expected = FileNotFoundException.class)
    public void test() throws IOException {
        File f = new File("a.txt");
        new CommandParser(f);
        assertFuzzyEquals("Check your file\n", systemOut().getHistory());

    }

}
