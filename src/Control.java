import java.io.File;
import java.io.IOException;

/**
 * Checks the inputfile and pass it into CommandParser class to parse each line
 * 
 * @author sohyun
 * @author sshumway
 * @version 02/01/2016
 */

public class Control {

    private String[] args;
    private File input;

    /**
     * constructor
     * 
     * @param args
     *            is command line
     */
    public Control(String[] args) {
        this.args = args;
    }

    /**
     * See if the file exists and parse if it does.
     * 
     * @return true when the file exists
     */
    public boolean setup() {
        if (checkFile()) {
            parse();
            return true;
        }
        return false;

    }

    /**
     * checks the file
     * 
     * @return true if the file exists
     */
    private boolean checkFile() {
        if (args[0] != null) {
            input = new File(args[0]);
            return input.exists();
        }
        return false;

    }

    /**
     * pass the input file into the CommandParser to parse!
     */
    private void parse() {
        CommandParser p = null;
        try {
            p = new CommandParser(input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        p.parse();
    }
}
