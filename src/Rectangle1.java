/**
 * 
 * Project 1 for CS 3114 Spring 2016 The class containing the main method, the
 * entry point of the application.
 *
 * On my honor: // // - I have not used source code obtained from another
 * student, // or any other unauthorized source, either modified or //
 * unmodified. // // - All source code and documentation used in my program is
 * // either my original work, or was derived by me from the // source code
 * published in the textbook for this course. // // - I have not discussed
 * coding details about this project with // anyone other than my partner (in
 * the case of a joint // submission), instructor, ACM/UPE tutors or the TAs
 * assigned // to this course. I understand that I may discuss the concepts //
 * of this program with other students, and that another student // may help me
 * debug my program so long as neither of us writes // anything during the
 * discussion or modifies any computer file // during the discussion. I have
 * violated neither the spirit nor // letter of this restriction.
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

        // checks if the length of argument is 1 and args[0] is available.
        if (args.length == 1 && args[0] != null) {
            // continue to run. Passing arguments into the control class.
            Control cont = new Control(args);
            cont.setup();
        }
        else {
            // A warning message if argument is not right.
            System.out.println("Check your arguments");

        }
    }
}
