import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements Database {

    public ArrayList<String> loadUserData(String path) {
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

    public boolean saveUserData(String path, String username, String password) {
        try {
            FileWriter writer = new FileWriter(path, true);
            String textToSave = "\n" + username + "," + password;
            writer.write(textToSave);
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public ArrayList<String> loadMediaData(String path) {
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

    public boolean saveListData(User user, String listType, ArrayList<AMedia<?>> listData) {
        try {
            FileOutputStream writer = new FileOutputStream("data/" + user.getUsername() + listType + "List.txt");

            String newLine = "";
            for (AMedia<?> m : listData) {
                String test = newLine + m.getInfo();
                writer.write(test.getBytes());
                newLine = "\n";
            }

            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public ArrayList<AMedia<?>> loadListData(User user, String listType) {
        ArrayList<AMedia<?>> medias = new ArrayList<>();
        ArrayList<String> data = loadMediaData("data/" + user.getUsername() + listType + "List.txt");

        ArrayList<String> allMovies = loadMediaData("data/100bedstefilm.txt");
        ArrayList<String> allSeries = loadMediaData("data/100bedsteserier.txt");

        if (data != null) {
            for (String info : data) {
                for (String movieData: allMovies) {
                    if (info.equals(movieData)) {
                        AMedia<Movie> movie = new Movie(movieData);
                        medias.add(movie);
                    }
                }

                for (String seriesData: allSeries) {
                    if (info.equals(seriesData)) {
                        AMedia<Series> series = new Series(seriesData);
                        medias.add(series);
                    }
                }
            }
        }

        return medias;
    }
}
