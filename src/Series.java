import java.util.ArrayList;

public class Series extends AMedia {
    private String endingYear;
    private ArrayList<Season> allSeasons;
    private ArrayList<String> seasonsAndEpisodes;

    public Series(String data) {
        super(data);
    }

    @Override
    public void setAllInformation() {
        seasonsAndEpisodes = new ArrayList<>();
        ArrayList<String> allCategories = new ArrayList<>();

        String releaseYear = "";
        String endingYear = "";
        String[] row = getData().split(";");
        String title = row[0].trim();

        if (row[1].trim().length() == 4) {
            releaseYear = row[1].trim();
            endingYear = releaseYear;
        } else if (row[1].trim().length() == 5) {
            releaseYear = row[1].trim().substring(0, 4);
            endingYear = "-";
        } else if (row[1].trim().length() >= 9) {
            String[] year = row[1].split("-");
            releaseYear = year[0].trim();
            endingYear = year[1].trim();
        }

        String categoriesData = row[2].trim();
        String[] seriesCategory = categoriesData.split(",");

        for (String category : seriesCategory) {
            allCategories.add(category.trim());
        }

        String ratingData = row[3].trim().replace(',', '.');
        double rating = Double.parseDouble(ratingData);

        String seasonsData = row[4].trim();
        String[] seriesSeasons = seasonsData.split(",");

        for (String season : seriesSeasons) {
            seasonsAndEpisodes.add(season.trim());
        }

        super.setTitle(title);
        super.setReleaseYear(releaseYear);
        super.setRating(rating);
        super.setCategories(allCategories);
        this.endingYear = endingYear;
        loadSeasons();
    }

    private void loadSeasons() {
        allSeasons = new ArrayList<>();
        //Arraylist<String> seasonsAndEpisodes:
        //"1-13", "2-13", "3-13", "4-13", "5-13", "6-21"
        for (String s : seasonsAndEpisodes) {
            Season season = new Season(s);
            allSeasons.add(season);
        }
    }

    @Override
    public String getType() {
        return "SERIES";
    }

    public String getEndingYear() {
        return endingYear;
    }

    public ArrayList<Season> getAllSeasons() {
        return allSeasons;
    }

    @Override
    public String toString() {
        return getType() +
                super.toString() +
                "\nEnding Year: " + getEndingYear() +
                "\nTotal number Of Seasons: " + getAllSeasons().size();
    }
}
