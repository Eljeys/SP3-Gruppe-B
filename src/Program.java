public class Program {
    /**
     * Method runs infinitely until a user decides to exit
     */
    public void run() {
        while (true) {
            StartMenu startMenu = new StartMenu();
            startMenu.display();
            User user = startMenu.getUserAccount();

            MainMenu mainMenu = new MainMenu(user);
            mainMenu.display();
        }
    }
}
