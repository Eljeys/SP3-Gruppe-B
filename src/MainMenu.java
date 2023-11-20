import java.util.ArrayList;

public class MainMenu extends AMenu{
    private ArrayList<String> movieData = new ArrayList<>();
    private ArrayList<Movie> listOfMovies = new ArrayList<>();
    private ArrayList<String> categories;
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
                    displayMoviesByCategory(searchFilmByCategory("Drama"));
                    break;
                case "2":
                    fileIO.searchFilmByRating();
                    break;
                case "3":
                    fileIO.searchFilmByYear();
                    break;
                case "4":
                    fileIO.searchFilmByTitle();
                    break;
                case "q":
                    writeName = false;
                default:
                    textUI.displayMessage("You haven't typed 1-4! Try again og type (q) if you want to quit!\n");
                    break;
            }
        }
    }
    void displayMovies() {
        movieData = fileIO.readMovieData("data/100bedstefilm.txt");
        listOfMovies = fileIO.getListOfMovies(movieData);
        for(Movie m: listOfMovies) {
            textUI.displayMessage(m.toString());
        }
    }

    ArrayList<Movie> getListOfMovies() {
        movieData = fileIO.readMovieData("data/100bedstefilm.txt");
        listOfMovies = fileIO.getListOfMovies(movieData);
        return listOfMovies;
    }


    public ArrayList<Movie> searchFilmByCategory(String category) {
        ArrayList<Movie> movies = getListOfMovies();
        ArrayList<Movie> movieByCategory = new ArrayList<>();
        for (Movie m : movies) {
            String categories = m.getCategories();
            if (categories.toLowerCase().contains(category.toLowerCase())) {
                movieByCategory.add(m);
            }
        }
        return movieByCategory;
    }

    void displayMoviesByCategory(ArrayList<Movie> listOfMovies) {
        textUI.displayMessage("I have found "+listOfMovies.size()+" movies with Drama as category");
        for(Movie m: listOfMovies) {
            textUI.displayMessage(m.toString());
        }

    }
}

