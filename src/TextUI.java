import java.util.Scanner;

public class TextUI {
    /**
     * Gets input from the terminal and returns it
     * @param msg The message to be displayed
     * @return String representing the input
     */
    public String getInput(String msg) {
        displayMessage(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display the given message to the terminal
     * @param msg The message to be displayed
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays the given message to the terminal in red text
     * @param msg The message to be displayed
     */
    public void displayErrorMessage(String msg) {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        System.out.println(RED + msg + RESET);
    }
}
