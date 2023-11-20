import java.util.ArrayList;

public class Series extends AMedia {
    private int endingYear;
    private ArrayList<String> seasonAndEpisodes;

    public Series(String title, int releaseYear, int endingYear, ArrayList<String> categories, double rating, ArrayList<String> seasonAndEpisodes) {
        super(title,releaseYear, rating, categories);
        this.endingYear = endingYear;
        this.seasonAndEpisodes = seasonAndEpisodes;
    }

    public int getEndingYear() {
        return endingYear;
    }

    public ArrayList<Season> getSeasons() {
        ArrayList<Season> seasons = new ArrayList<>();
        return seasons;
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
    String getTitle() {
        return null;
    }

    @Override
    int getReleaseYear() {
        return 0;
    }

    @Override
    double getRating() {
        return 0;
    }

    @Override
    ArrayList<String> getCategories() {
        return null;
    }
}
