import java.util.ArrayList;

public class StartMenu extends AMenu{
    @Override
    void display() {
        boolean chooseInput = true;
        while(chooseInput) {
            textUI.displayMessage("1. Login\n" +
                    "2. Create Account\n" +
                    "3. Exit\n");

            String choice = textUI.getInput("Choose option: ");

            try {
                int menuOption = Integer.parseInt(choice);

                switch (menuOption) {
                    case 1:
                        login();
                        if (user != null) {
                            chooseInput = false;
                        }
                        break;
                    case 2:
                        createAccount();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        textUI.displayMessage("Not a Menu option!");
                        break;
                }

            } catch (NumberFormatException e) {
                textUI.displayMessage("Choose a number!");
            }
        }
    }

    private void login() {
        fileIO = new FileIO();
        ArrayList<String> data = fileIO.readUserData("data/userData.txt");

        if (!data.isEmpty()) {
            for (String s : data) {
                String[] row = s.split(",");
                String name = row[0];
                String password = row[1];

                boolean writeName = true;
                while (writeName) {
                    String username = textUI.getInput("Input username or back to start menu (q): ");

                    if (username.equalsIgnoreCase(name)) {
                        boolean writePassword = true;
                        while (writePassword) {
                            String typedPassword = textUI.getInput("Input password or back to start menu (q): ");

                            if (password.equals(typedPassword)) {
                                user = new User(name, password);
                                writeName = false;
                                writePassword = false;
                            } else if (typedPassword.equalsIgnoreCase("q")) {
                                writeName = false;
                                writePassword = false;
                            } else {
                                textUI.displayMessage("Password did not match!");
                            }
                        }
                    } else if (username.equalsIgnoreCase("q")) {
                        writeName = false;
                    } else {
                        textUI.displayMessage("Could not find user with given username!");
                    }
                }
            }
        } else {
            textUI.displayMessage("Could not login.");
        }
    }

    private void createAccount() {

    }
}
