import java.util.Scanner;

public class TextUI {
    public String getInput(String msg) {
        displayMessage(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
    public void displayErrorMessage(String msg) {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        System.out.println(RED + msg + RESET);
    }
}
