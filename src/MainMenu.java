import java.util.ArrayList;

public class MainMenu extends AMenu{
    private ArrayList<String> movieData = new ArrayList<>();
    private ArrayList<String> serieData = new ArrayList<>();
    private ArrayList<AMedia> listOfMedias = new ArrayList<>();
    private ArrayList<AMedia> listOfSeries = new ArrayList<>();
    @Override
    void display() {
        boolean writeName = true;
        while (writeName) {
            textUI.displayMessage("\n1. Search for a specific category of films!");
            textUI.displayMessage("2. Search for a specific rating of films!");
            textUI.displayMessage("3. Search for a specific year of films!");
            textUI.displayMessage("4. Search for a specific title of films!");

            String choice = textUI.getInput("\nInput your choice 1-4 or (q) if you want to quit: ");
            choice = choice.toLowerCase();
            switch (choice) {
                case "1":
                    displayMediaByCategory(searchMediaByCategory("Drama"));
                    displayAllMedia(listOfMedias);
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "q":
                    writeName = false;
                default:
                    textUI.displayMessage("You haven't typed 1-4! Try again og type (q) if you want to quit!\n");
                    break;
            }
        }
    }

    ArrayList<AMedia> getListOfMedias() {
        movieData = fileIO.readMediaData("data/100bedstefilm.txt");
        serieData = fileIO.readMediaData( "data/100bedsteserier.txt");

        listOfMedias = fileIO.getListOfMovies(movieData);
        listOfSeries = fileIO.getListOfSeries(serieData);
        listOfMedias.addAll(listOfSeries);
        return listOfMedias;
    }


    public ArrayList<AMedia> searchMediaByCategory(String category) {
        ArrayList<AMedia> medias = getListOfMedias();
        ArrayList<AMedia> movieByCategory = new ArrayList<>();
        for (AMedia m : medias) {
            String categories = m.getCategories();
            if (categories.toLowerCase().contains(category.toLowerCase())) {
                movieByCategory.add(m);
            }
        }
        return movieByCategory;
    }

    void displayMediaByCategory(ArrayList<AMedia> listOfMedias) {
        textUI.displayMessage("I have found "+listOfMedias.size()+" movies with Drama as category");
        for(AMedia m: listOfMedias) {
            textUI.displayMessage(m.toString());
        }

    }
    void displayAllMedia(ArrayList<AMedia> listOfMedias) {
        for(AMedia m: listOfMedias) {
            System.out.println(m.toString());
        }
    }
}

