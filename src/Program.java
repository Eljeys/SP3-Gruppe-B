public class Program {
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
