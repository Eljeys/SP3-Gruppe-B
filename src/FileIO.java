import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    public boolean saveUserData(String path, String username, String password) {
        try {
            FileWriter writer = new FileWriter(path,true);
            String textToSave = "\n"+username + "," + password;
            writer.write(textToSave);
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public ArrayList<String> readMediaData(String path) {
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

    public ArrayList<AMedia> getListOfMovies(ArrayList<String> readMovieData) {
        ArrayList<String> categories;
        ArrayList<AMedia> listOfMovies = new ArrayList<>();
        for(String m: readMovieData) {
            String[] row = m.split(";");
            String title = row[0].trim();
            String releaseYear = row[1].trim();
            String r = row[3].trim().replace(',','.');
            double rating = Double.parseDouble(r);
            String c = row[2].trim();
            String[] category = c.split(",");
            categories = new ArrayList<>();
            for(String s: category) {
                categories.add(s.trim());
            }
            listOfMovies.add(new Movie(title,releaseYear,rating,categories));

        }
        return listOfMovies;
    }

    public ArrayList<AMedia> getListOfSeries(ArrayList<String> readSeriesData) {
        ArrayList<String> categories;
        ArrayList<String> seasonsAndEpisodes;
        ArrayList<AMedia> listOfSeries = new ArrayList<>();
        String releaseYear = "";
        String endingYear = "";
        for(String m: readSeriesData) {
            String[] row = m.split(";");
            String title = row[0].trim();
            if(row[1].trim().length() == 4) {
                releaseYear = row[1].trim();
                endingYear = releaseYear;
            } else if(row[1].trim().length() == 5) {
                releaseYear = row[1].trim().substring(0,4); // Her tager jeg de f�rste 5 karakterer og trimmer og f�r derved �rstallet
                endingYear = "-";
            } else if(row[1].trim().length() >=9) {
                String[] year = row[1].split("-");
                releaseYear = year[0].trim();
                endingYear = year[1].trim();
            }
            String c = row[2].trim();
            String[] category = c.split(",");
            categories = new ArrayList<>();
            for(String s: category) {
                categories.add(s.trim());
            }

            String r = row[3].trim().replace(',','.');
            double rating = Double.parseDouble(r);

            String season = row[4].trim();
            String[] seasons = season.split(",");
            seasonsAndEpisodes = new ArrayList<>();
            for(String s: seasons) {
                seasonsAndEpisodes.add(s.trim());
            }
            listOfSeries.add(new Series(title,releaseYear,endingYear,categories,rating,seasonsAndEpisodes));

        }
        return listOfSeries;
    }

    public boolean saveListData(String listType, ArrayList<String> mediaData, ArrayList<AMedia> listData){
        try {
            FileWriter writer = new FileWriter("data/" + listType+ ".txt");

            for (AMedia m: listData) {
                for (String s : mediaData) {
                    if (s.toLowerCase().contains(m.getTitle().toLowerCase())) {
                        writer.write(s + "\n");
                    }
                }
            }

            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
