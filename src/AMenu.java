public abstract class AMenu {
    protected TextUI textUI = new TextUI();
    protected Database fileIO = new FileIO();
    protected User user;
    protected final String exit = "exit";
    protected final String goBack = "q";
    protected final String confirm = "y";
    abstract void display();

    /**
     * Asks to choose menu option
     * @return The user input as a String
     */
    protected String chooseMenuOption() {
        return textUI.getInput("Choose menu option : ");
    }

    /**
     * Asks to choose option or go back
     * @return The user input as a string
     */
    protected String chooseOption() {
        return textUI.getInput( "\nChoose option or go back (q): ");
    }

    /**
     * Display this error when user chooses an option that is not available
     */
    protected void errorNotAnOption() {
        textUI.displayErrorMessage("\nNot an option!");
    }

    /**
     * Display this error when user does not input a number
     */
    protected void errorNotANumber() {
        textUI.displayErrorMessage("\nChoose a number!");
    }
}
