import java.util.ArrayList;

public class Movie extends AMedia{
    private Database io;
    private String title;
    private int releaseYear;
    private double rating;
    private ArrayList<String> categories;

    public Movie(String title, int releaseYear, double rating, ArrayList<String> categories) {
        super(title, releaseYear, rating, categories);
    }

    @Override
    public void play() {
        System.out.println(getMovie()+" afspilles!");

    }
    @Override
    public void saveToList() {

    }
    @Override
    public void removeFromList() {

    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public ArrayList<String> getCategories() {
        return categories;
    }

}
