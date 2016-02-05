import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * It parses each line into the processor.
 * 
 * @author sohyun
 * @author sshumway
 * @version 01/31/2016
 *
 */

public class CommandParser {

    // the inputfile
    private Scanner inputfile;
    // CommandProcessor class
    // initialized when the file is available
    private CommandProcessor commandProcessor;

    /**
     * creates Scanner for parsing the file.
     * 
     * @param input
     *            is the file
     * @throws IOException
     */
    public CommandParser(File input) throws IOException {
        try {
            inputfile = new Scanner(input);
            commandProcessor = new CommandProcessor();

        }
        catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Check your file");
            throw e;
        }

    }

    /**
     * Starts parsing. Get rid of spaces and tabs. Stores in the String array.
     * Passing the array to CommandProcesor class every time it loops.
     */
    public void parse() {
        // loops until at the end of the txt file
        while (inputfile.hasNextLine()) {
            // split the line
            String[] line = inputfile.nextLine().trim().split("\\s+");
            commandProcessor.setUp(line);
            // process the command
            commandProcessor.process();
        }
        // making sure to close the input file
        inputfile.close();

    }
}
