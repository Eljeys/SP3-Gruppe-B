import java.util.ArrayList;

public abstract class AMedia {
    private Database io;
    private String title;
    private int releaseYear;
    private double rating;
    private ArrayList<String> categories;

    public AMedia(String title, int releaseYear, double rating, ArrayList<String> categories) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categories = categories;
    }

   abstract void play();
   abstract void saveToList();
   abstract void removeFromList();
   abstract String getTitle();
   abstract int getReleaseYear();
   abstract double getRating();
   abstract ArrayList<String> getCategories();

}
