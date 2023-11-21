public abstract class AMenu {
    TextUI textUI = new TextUI();
    Database fileIO = new FileIO();
    User user;
    abstract void display();
}
