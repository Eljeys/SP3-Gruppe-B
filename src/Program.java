public class Program {
    public void run() {
        while (true) {
            StartMenu startMenu = new StartMenu();
            startMenu.display();

            MainMenu mainMenu = new MainMenu();
            mainMenu.display();
        }
    }
}
