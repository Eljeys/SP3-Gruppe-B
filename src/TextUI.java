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
}
