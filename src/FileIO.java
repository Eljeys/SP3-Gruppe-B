import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements Database {

    public ArrayList<String> loadAllUsers(String path) {
        ArrayList<String> users = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();

            while (scan.hasNextLine()) {
                String userData = scan.nextLine();
                users.add(userData);
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return users;
    }

    public boolean saveUserData(String path, String username, String password) {
        try {
            FileWriter writer = new FileWriter(path, true);
            String userData = "\n" + username + "," + password;
            writer.write(userData);
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public ArrayList<String> loadAllMedias(String path) {
        ArrayList<String> medias = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String mediaData = scan.nextLine();
                medias.add(mediaData);
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return medias;
    }

    public boolean saveListData(User user, String listType, ArrayList<AMedia> listOfMedias) {
        try {
            FileWriter writer = new FileWriter("data/" + user.getUsername() + listType + "List.txt");

            String newLine = "";
            for (AMedia media : listOfMedias) {
                String mediaData = newLine + media.getData();
                writer.write(mediaData);
                newLine = "\n";
            }

            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public ArrayList<AMedia> loadListData(User user, String listType) {
        ArrayList<AMedia> medias = new ArrayList<>();
        ArrayList<String> listData = loadAllMedias("data/" + user.getUsername() + listType + "List.txt");

        ArrayList<String> allMovies = loadAllMedias("data/100bedstefilm.txt");
        ArrayList<String> allSeries = loadAllMedias("data/100bedsteserier.txt");

        if (listData != null) {
            for (String mediaData : listData) {
                for (String movieData: allMovies) {
                    if (mediaData.equals(movieData)) {
                        AMedia movie = new Movie(movieData);
                        medias.add(movie);
                    }
                }

                for (String seriesData: allSeries) {
                    if (mediaData.equals(seriesData)) {
                        AMedia series = new Series(seriesData);
                        medias.add(series);
                    }
                }
            }
        }
        return medias;
    }
}
