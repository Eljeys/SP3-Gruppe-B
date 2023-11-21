import java.util.ArrayList;

public class StartMenu extends AMenu{
    @Override
    void display() {
        boolean chooseInput = true;
        while(chooseInput) {
            textUI.displayMessage("""

                    START MENU:
                    1. Login
                    2. Create Account
                    3. Exit
                    """);

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
                        if (user != null) {
                            chooseInput = false;
                        }
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
            outerLoop:
            while (true) {
                String typedUsername = textUI.getInput("Input username or back to start menu (q): ");

                if (typedUsername.equalsIgnoreCase("q")) {
                    break;
                } else {

                    String p = "";
                    String u = "";
                    for (String s : data) {
                        String[] row = s.split(",");
                        String username = row[0];
                        String password = row[1];

                        if (username.equalsIgnoreCase(typedUsername)) {
                            u = username;
                            p = password;
                        }
                    }

                    if (p.isEmpty() && u.isEmpty()) {
                        textUI.displayMessage("Could not find user with given username.");
                    } else {
                        while (true) {
                            String typedPassword = textUI.getInput("Input password or back to start menu (q): ");

                            if (p.equals(typedPassword)) {
                                user = new User(u, p);
                                break outerLoop;
                            } else if (typedPassword.equalsIgnoreCase("q")) {
                                break outerLoop;
                            } else {
                                textUI.displayMessage("Password did not match!");
                            }
                        }
                    }
                    }
                }
        } else {
            textUI.displayMessage("Could not login.");
        }
    }

    private void createAccount() {
        fileIO = new FileIO();
        ArrayList<String> data = fileIO.readUserData("data/userData.txt");

        outerLoop:
        while (true) {
            String username = textUI.getInput("Create username (Must begin with a letter) or back to start menu (q): ");

            char firstCharacter = username.charAt(0);
            if (username.equalsIgnoreCase("q")) {
                break;
            } else if (!Character.isDigit(firstCharacter)) {

                boolean exist = false;
                for (String s: data) {
                    boolean userFound = s.toLowerCase().contains(username.toLowerCase());
                    if (userFound) {
                        exist = true;
                        break;
                    }
                }

                if (exist) {
                    String answer = textUI.getInput("Username already exists. Do you want to login? Y/N: ");

                    if (answer.equalsIgnoreCase("Y")) {
                        login();
                    } else {
                        textUI.displayMessage("\nReturning you to Start Menu.");
                    }
                    break;
                } else {
                    while (true) {
                        String password = textUI.getInput("Create password (Minimum 8 characters) or back to start menu (q): ");

                        if (password.length() >= 8) {
                            fileIO = new FileIO();
                            boolean userSavedToFile = fileIO.saveUserData("data/userData.txt", username, password);

                            if (!userSavedToFile) {
                                textUI.displayMessage("Could not create an account.");
                            } else {
                                String answer = textUI.getInput("You have now created an account. Log in? Y/N: ");
                                if (answer.equalsIgnoreCase("Y")) {
                                    user = new User(username, password);
                                }
                            }
                            break outerLoop;
                        } else if (password.equalsIgnoreCase("q")) {
                            break outerLoop;
                        } else {
                            textUI.displayMessage("Must be minimum 8 characters long! Try again.");
                        }
                    }
                }
            } else {
                textUI.displayMessage("Must begin with a letter! Try again.");
            }
        }
    }

    public User userLoggedin() {
        return user;
    }
}
