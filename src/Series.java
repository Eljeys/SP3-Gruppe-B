import java.util.ArrayList;

public class Series extends AMedia {
    private int endingYear;
    private ArrayList<String> seasonAndEpisodes;
    private ArrayList<Season> seasons = new ArrayList<>();

    public Series(String title, int releaseYear, int endingYear, ArrayList<String> categories, double rating, ArrayList<String> seasonAndEpisodes) {
        super(title,releaseYear, rating, categories);
        this.endingYear = endingYear;
        this.seasonAndEpisodes = seasonAndEpisodes;
    }

    public int getEndingYear() {
        return endingYear;
    }

    public ArrayList<Season> getSeasons() {
        //Arraylist<String> seasonsAndEpisodes:
        //"1-13", "2-13", "3-13", "4-13", "5-13", "6-21"
        for (String s: seasonAndEpisodes) {
            Season season = new Season(s);
            seasons.add(season);
        }
        return seasons;
    }

    @Override
    public void play() {

    }

    @Override
    String getMediaType() {
        return "SERIES";
    }

    @Override
    public String toString() {
        return getMediaType() +
                super.toString() +
                "\nEnding Year: " + getEndingYear() +
                "\nTotal number Of Seasons: " + getSeasons().size();
    }
}
