import java.util.ArrayList;

public class MainMenu extends AMenu{
    private ArrayList<String> movieData = new ArrayList<>();
    private ArrayList<String> serieData = new ArrayList<>();
    private ArrayList<AMedia> listOfMedias = new ArrayList<>();
    private ArrayList<AMedia> listOfSeries = new ArrayList<>();
    @Override
    void display() {
        boolean chooseOption = true;
        while (chooseOption) {
            textUI.displayMessage("""

                    MAIN MENU:
                    1. Search by title
                    2. Search by category
                    3. See list of watched Series And Movies
                    4. See list of favorites
                    5. Log out
                    """);

            String choice = textUI.getInput("Choose option: ");

            try {
                int menuOption = Integer.parseInt(choice);

                switch (menuOption) {
                    case 1:
                        //Search media by title
                        break;
                    case 2:
                        // search by category
                        break;
                    case 3:
                        // see list of watched Series And Movies
                        break;
                    case 4:
                        //See list of favorites
                        break;
                    case 5:
                        //logout
                        user = null;
                        chooseOption = false;
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

