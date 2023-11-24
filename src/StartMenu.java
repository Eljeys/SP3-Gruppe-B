import java.util.ArrayList;

public class StartMenu extends AMenu {
    /**
     * Displays the available actions for start menu
     */
    @Override
    public void display() {
        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("""
                                        
                    START MENU:
                    1. Login
                    2. Create Account
                    3. Exit
                    """);

            String input = chooseMenuOption();

            try {
                int menuOption = Integer.parseInt(input);

                switch (menuOption) {
                    case 1:
                        login();
                        break;
                    case 2:
                        createAccount();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        errorNotAnOption();
                        break;
                }

                if (user != null) {
                    choosingAction = false;
                }
            } catch (NumberFormatException e) {
                errorNotANumber();
            }
        }
    }

    /**
     * Loads all user accounts from database and checks if user exists
     */
    private void login() {
        fileIO = new FileIO();
        ArrayList<String> data = fileIO.loadAllUsers("data/userData.txt");

        if (!data.isEmpty()) {
            String[] userData = validateUsername(data);
            if (userData != null) {
                validatePassword(userData);
            }
        } else {
            textUI.displayErrorMessage("\nThere's no user accounts.");
        }
    }

    /**
     * Validates the username
     * @param data Array list of Strings that represents all users in the database
     * @return String array which is either null or containing username and password
     */
    private String[] validateUsername(ArrayList<String> data) {
        boolean isValidatingUsername = true;
        String[] validUser = null;

        while (isValidatingUsername) {
            String typedUsername = textUI.getInput("\nInput username or back to start menu (q): ");

            if (typedUsername.equalsIgnoreCase(goBack)) {
                isValidatingUsername = false;
            } else {

                String[] userdata = getUserData(data, typedUsername);

                if (!userdata[0].isEmpty() && !userdata[1].isEmpty()) {
                    validUser = new String[2];
                    validUser[0] = userdata[0];
                    validUser[1] = userdata[1];
                    isValidatingUsername = false;
                } else {
                    textUI.displayErrorMessage("\nCould not find user.");
                }
            }
        }
        return validUser;
    }

    /**
     * Checks if the given input matches an existing username
     * @param data Array list of Strings that represents all users in the database
     * @param typedUsername
     * @return String array which is either empty or containing username and password
     */
    private String[] getUserData(ArrayList<String> data, String typedUsername) {
        String username = "";
        String password = "";
        for (String s : data) {
            String[] row = s.split(",");
            String usernameData = row[0];
            String passwordData = row[1];

            if (usernameData.equalsIgnoreCase(typedUsername)) {
                username = usernameData;
                password = passwordData;
            }
        }

        return new String[]{username, password};
    }

    /**
     * Validates the password by checking if the input matches the password saved in the database
     * @param userData String array containing username and password
     */
    private void validatePassword(String[] userData) {
        boolean isValidatingPassword = true;
        while (isValidatingPassword) {
            String typedPassword = textUI.getInput("\nInput password or back to start menu (q): ");

            if (userData[1].equals(typedPassword)) {
                user = new User(userData[0], userData[1]);
                isValidatingPassword = false;
            } else if (typedPassword.equalsIgnoreCase(goBack)) {
                isValidatingPassword = false;
            } else {
                textUI.displayErrorMessage("\nPassword did not match!");
            }
        }
    }

    /**
     * Loads all user accounts in database and check if user already exists
     */
    private void createAccount() {
        fileIO = new FileIO();
        ArrayList<String> data = fileIO.loadAllUsers("data/userData.txt");

        String username = createUsername(data);
        if (!username.isEmpty()) {
            createPassword(username);
        }
    }

    /**
     * Creates the username if it's not found in the database
     * @param data Array list of Strings that represents all users in the database
     * @return "" or username
     */
    private String createUsername(ArrayList<String> data) {
        String username = "";
        boolean isCreatingUsername = true;
        while (isCreatingUsername) {
            String typedUsername = textUI.getInput("\nCreate username (Must begin with a letter) or back to start menu (q): ");

            char firstCharacter = typedUsername.charAt(0);
            if (typedUsername.equalsIgnoreCase(goBack)) {
                isCreatingUsername = false;
            } else if (!Character.isDigit(firstCharacter)) {
                boolean userExists = doesUserExists(data, typedUsername);

                if (!userExists) {
                    username = typedUsername;
                }

                isCreatingUsername = false;
            } else {
                textUI.displayErrorMessage("\nMust begin with a letter!");
            }
        }
        return username;
    }

    /**
     * Creates the password for the user account being created.
     * If password is created successfully a User object will be created and saved to the database.
     * @param username The given username to save
     */
    private void createPassword(String username) {
        boolean isCreatingPassword = true;
        while (isCreatingPassword) {
            String password = textUI.getInput("\nCreate password (Minimum 8 characters) or back to start menu (q): ");

            if (password.length() >= 8) {
                fileIO = new FileIO();
                boolean userSavedToFile = fileIO.saveUserData("data/userData.txt", username, password);

                if (!userSavedToFile) {
                    textUI.displayMessage("\nCould not create an account.");
                } else {
                    String answer = textUI.getInput("\nYou have now created an account. Log in? Y/N: ");
                    if (answer.equalsIgnoreCase(confirm)) {
                        user = new User(username, password);
                    }
                }
                isCreatingPassword = false;
            } else if (password.equalsIgnoreCase(goBack)) {
                isCreatingPassword = false;
            } else {
                textUI.displayErrorMessage("\nMust be minimum 8 characters long!");
            }
        }
    }

    /**
     * Checks if a given username already exists in the database
     * @param data Array list of Strings that represents all users in the database
     * @param username The username to check
     * @return true or false depending on if user exists
     */
    private boolean doesUserExists(ArrayList<String> data, String username) {
        boolean userExists = false;
        String[] userData = getUserData(data,username);
        if (!userData[0].isEmpty()) {
            userExists = true;
        }

        if (userExists) {
            String answer = textUI.getInput("\nUsername already exists. Do you want to login? Y/N: ");

            if (answer.equalsIgnoreCase(confirm)) {
                validatePassword(userData);
            } else {
                textUI.displayMessage("\nReturning you to Start Menu.");
            }
        }
        return userExists;
    }

    /**
     * Gets the user account
     * @return A user object
     */
    public User getUserAccount() {
        return user;
    }
}
