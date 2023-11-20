import java.util.ArrayList;

public class Movie extends AMedia{
    private Database io;
    private String title;
    private int releaseYear;
    private double rating;
    private ArrayList<String> categories;

    public Movie(String title, int releaseYear, double rating, ArrayList<String> categories) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categories = categories;
    }

    @Override
    void play() {

    }
    @Override
    void saveToList() {

    }
    @Override
    void removeFromList() {

    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    int getReleaseYear() {
        return releaseYear;
    }

    @Override
    double getRating() {
        return rating;
    }

    @Override
    ArrayList<String> getCategories() {
        return categories;
    }
}
