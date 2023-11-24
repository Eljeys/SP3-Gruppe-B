public abstract class AMenu {
    protected TextUI textUI = new TextUI();
    protected Database fileIO = new FileIO();
    protected User user;
    protected final String exit = "exit";
    protected final String goBack = "q";
    protected final String confirm = "y";
    abstract void display();
    protected String chooseMenuOption() {
        return textUI.getInput("Choose menu option : ");
    }
    protected String chooseOption() {
        return textUI.getInput( "\nChoose option or go back (q): ");
    }

    protected void wrongOption() {
        textUI.displayErrorMessage("\nNot an option!");
    }

    protected void errorNotANumber() {
        textUI.displayErrorMessage("\nChoose a number!");
    }
}
