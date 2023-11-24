import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements Database {

    /**
     *
     * @param path data/userData.txt
     * @return a String ArrayList with all the users
     * this method loads all the user in the data/userData.txt
     */
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

    /**
     *
     * @param path data/userData.txt
     * @param username the typed name
     * @param password the typed password
     * @return true if the writing-process succeeds, false if not
     */
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

    /**
     *
     * @param path either data/100bedstefilm.txt or data/100bedsteserier.txt
     * @return an ArrayList of medias for either Series og Movie
     * This method is called to times from MainMenu(loadLibrary()) to make a complete AMedia-ArrayList containing Movies and Series
     */
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

    /**
     *
     * @param user the user logged in
     * @param listType either favorites or watched
     * @param listOfMedias a ArrayList containing all our medias
     * @return true if it was possible to create the .txt-file containing either the users favorite- or watched-list
     * this method writes both type of AMedia-ArrayList to a .txt-file ex. data/olefavoritesList.txt
     */
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

    /**
     *
     * @param user the user logged in
     * @param listType favorites or watched
     * @return a ArrayList of all the data of either Series or Movie
     * this method creates a ArrayList with all the data of our medias by running through the users favorites and movie-lists and comparing them to our media lists.
     */
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
