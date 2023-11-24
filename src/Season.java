import java.util.ArrayList;

public class Season {
    private final int seasonNumber;
    private final int numberOfEpisodes;

    public Season(String seasonAndEpisode) {
        //"1-13"
        String[] number = seasonAndEpisode.split("-");
        seasonNumber = Integer.parseInt(number[0]);
        numberOfEpisodes = Integer.parseInt(number[1]);
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }
}
