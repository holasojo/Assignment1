/**
 * 
 * Project 1 for CS 3114 Spring 2016 The class containing the main method, the
 * entry point of the application.
 *
 * @author So Hyun Jo (sohyun)
 * @author Scott Shumway (sshumway)
 * @version 1/23/16.
 */
public class Rectangle1 {

    /**
     * The entry point of the application.
     *
     * @param args
     *            The name of the command file passed in as a command line
     *            argument.
     */
    public static void main(String[] args) {

        if (args.length == 1 && args[0] != null) {
            Control cont = new Control(args);
            cont.setup();
        }
        else {

            System.out.println("Check your arguments");

        }
    }
}
