import java.util.ArrayList;

public interface Database {
    ArrayList<String> readUserData(String path);
    boolean saveUserData(String path, String username, String password);

    ArrayList<String> readMediaData(String path);

    ArrayList<AMedia> getListOfMovies(ArrayList<String> readMovieData);
    ArrayList<AMedia> getListOfSeries(ArrayList<String> readMovieData);
}
