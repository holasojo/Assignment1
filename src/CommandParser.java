import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * It parses the input.txt
 * 
 * @author SOJO
 * @author sshumway
 * @version 01/31/2016
 *
 */

public class CommandParser {

    private Scanner inputfile;
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
          //e.printStackTrace();
            System.out.println("Check your file");
            throw e;
        }

    }

    /**
     * starts parsing. gets one line every time it loops.
     */
    public void parse() {

        while (inputfile.hasNextLine()) {
            String[] line = inputfile.nextLine().trim().split("\\s+");
            commandProcessor.setUp(line);
            commandProcessor.process();
        }
        inputfile.close();

    }
}
