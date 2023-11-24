import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private int numberOfEpisodes;
    private final String seasonAndEpisodes;

    public Season(String seasonAndEpisodes) {
        this.seasonAndEpisodes = seasonAndEpisodes;
        setSeasonsAndEpisodes();
    }

    /**
     * Split the data that's passes as a String when creating a Season object.
     * Sets the season number and total number of episodes in the season.
     */
    private void setSeasonsAndEpisodes() {
        //"1-13"
        String[] number = seasonAndEpisodes.split("-");
        seasonNumber = Integer.parseInt(number[0]);
        numberOfEpisodes = Integer.parseInt(number[1]);
    }

    /**
     * Gets the season number
     * @return the season number as an int
     */
    public int getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Gets the number of episodes in a season
     * @return the number of episodes as an int
     */
    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }
}
