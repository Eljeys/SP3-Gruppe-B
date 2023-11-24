import java.util.ArrayList;

public abstract class AMedia {
    protected final String data;
    protected String title;
    protected String releaseYear;
    protected double rating;
    protected ArrayList<String> categories;

    public AMedia(String data) {
        this.data = data;
        setAllInformation();
    }

    /**
     * @return That a media is playing as a String
     */
    public String play() {
        return "\n***" + title + " is now playing***";
    }

    abstract void setAllInformation();

    /**
     * Gets all the data from AMedia object
     * @return All data as a String
     */
    public String getData() {
        return data;
    }

    abstract String getType();

    /**
     * Gets the title of AMedia object
     * @return The title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the release year of AMedia object
     * @return The release year as a String
     */
    public String getReleaseYear() {
        return releaseYear;
    }

    /**
     * Gets the rating of AMedia object
     * @return The rating as a double
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the title of AMedia object
     * @param title The media title as a String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the release year of AMedia object
     * @param releaseYear The media release year as a String
     */
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Sets the rating of AMedia object
     * @param rating The media rating as a double
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Sets the categories of AMedia object
     * @param categories The media categories as a String array
     */
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    /**
     * Gets the categories of AMedia object
     * @return All categories as a String
     */
    public String getCategories() {
        String c = "";
        for (String s : categories) {
            c = c.concat(s) + ", ";
        }

        return c.substring(0, c.length() - 2);
    }

    /**
     * Display AMedia object a certain way
     * @return The String to display
     */
    @Override
    public String toString() {
        return "\nTitle: " + getTitle() +
                "\nRating: " + getRating() +
                "\nCategories: " + getCategories() +
                "\nRelease Year: " + getReleaseYear();
    }
}
