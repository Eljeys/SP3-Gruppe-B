import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private int numberOfEpisodes;

    public Season(String seasonAndEpisode) {
        //"1-13"
        String[] numbers = seasonAndEpisode.split("-");
        seasonNumber = Integer.parseInt(numbers[0]);
        numberOfEpisodes = Integer.parseInt(numbers[1]);
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }
}
