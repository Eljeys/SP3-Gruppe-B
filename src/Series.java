import java.util.ArrayList;

public class Series extends AMedia <Series> {
    private String endingYear;
    private ArrayList<String> seasonAndEpisodes;
    private ArrayList<Season> seasons = new ArrayList<>();

    public Series(String info) {
        super(info);
    }

    @Override
    public void play() {

    }

    @Override
    public void setMediaData() {
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> seasonsAndEpisodes = new ArrayList<>();

        String releaseYear = "";
        String endingYear = "";
        String[] row = getInfo().split(";");
        String title = row[0].trim();

        if (row[1].trim().length() == 4) {
            releaseYear = row[1].trim();
            endingYear = releaseYear;
        } else if (row[1].trim().length() == 5) {
            releaseYear = row[1].trim().substring(0, 4); // Her tager jeg de f�rste 5 karakterer og trimmer og f�r derved �rstallet
            endingYear = "-";
        } else if (row[1].trim().length() >= 9) {
            String[] year = row[1].split("-");
            releaseYear = year[0].trim();
            endingYear = year[1].trim();
        }

        String c = row[2].trim();
        String[] category = c.split(",");

        for (String s : category) {
            categories.add(s.trim());
        }

        String r = row[3].trim().replace(',', '.');
        double rating = Double.parseDouble(r);

        String season = row[4].trim();
        String[] seasons = season.split(",");

        for (String s : seasons) {
            seasonsAndEpisodes.add(s.trim());
        }

        super.setTitle(title);
        super.setReleaseYear(releaseYear);
        super.setRating(rating);
        super.setCategories(categories);
        this.endingYear = endingYear;
        this.seasonAndEpisodes = seasonsAndEpisodes;
    }

    @Override
    public Series getMediaData() {
        return this;
    }

    public String getEndingYear() {
        return endingYear;
    }

    public ArrayList<Season> getSeasons() {
        //Arraylist<String> seasonsAndEpisodes:
        //"1-13", "2-13", "3-13", "4-13", "5-13", "6-21"
        for (String s : seasonAndEpisodes) {
            Season season = new Season(s);
            seasons.add(season);
        }

        return seasons;
    }

    @Override
    public String getMediaType() {
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
