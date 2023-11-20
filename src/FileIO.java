import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements Database{

    public ArrayList<String> readUserData(String path) {
        ArrayList<String> data = new ArrayList<>();

        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                data.add(s);
            }
        } catch (FileNotFoundException e) {
            return null;
        }

        return data;
    }
    public ArrayList<String> readMovieData(String path) {
        ArrayList<String> movieData = new ArrayList<>();
        File filmFile = new File(path);
        try {
            Scanner scanner = new Scanner(filmFile);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                movieData.add(s);
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return movieData;
    }

    public ArrayList<Movie> getListOfMovies(ArrayList<String> readMovieData) {
        ArrayList<String> categories;
        ArrayList<Movie> listOfMovies = new ArrayList<>();
        for(String m: readMovieData) {
            String[] row = m.split(";");
            String title = row[0].trim();
            int releaseYear = Integer.parseInt(row[1].trim());
            String rat = row[3].trim().replace(',','.');
            double rating = Double.parseDouble(rat);
            String cat = row[2].trim();
            String[] category = cat.split(",");
            categories = new ArrayList<>();
            for(String c: category) {
                categories.add(c.trim());
            }
            listOfMovies.add(new Movie(title,releaseYear,rating,categories));

        }
        return listOfMovies;
    }

    @Override
    public void searchFilmByCategory(String category) {

    }


    @Override
    public void searchFilmByRating() {

    }

    @Override
    public void searchFilmByYear() {

    }

    @Override
    public void searchFilmByTitle() {

    }



}
