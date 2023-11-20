import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private int numberOfEpisodes;

    private String seasonsAndEpisodes;

    public Season(String seasonAndEpisode) {
        this.seasonsAndEpisodes = seasonAndEpisode;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }
}
