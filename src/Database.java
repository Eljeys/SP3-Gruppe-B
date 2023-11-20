import java.util.ArrayList;

public interface Database {
    ArrayList<String> readUserData(String path);

    ArrayList<String> readMovieData(String path);

    ArrayList<Movie> getListOfMovies(ArrayList<String> readMovieData);

    void searchFilmByCategory(String category);

    void searchFilmByRating();

    void searchFilmByYear();

    void searchFilmByTitle();
}
