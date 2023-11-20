import java.util.ArrayList;

public abstract class AMedia {
    private Database io;
    private String title;
    private int releaseYear;
    private double rating;
    private ArrayList<String> categories;

   abstract void play();
   abstract void saveToList();
   abstract void removeFromList();
   abstract String getTitle();
   abstract int getReleaseYear();
   abstract double getRating();
   abstract ArrayList<String> getCategories();

}
