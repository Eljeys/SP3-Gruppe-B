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

    public String play() {
        return "\n***" + title + " is now playing***";
    }

    abstract void setAllInformation();

    public String getData() {
        return data;
    }

    abstract String getType();

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public String getCategories() {
        String c = "";
        for (String s : categories) {
            c = c.concat(s) + ", ";
        }

        return c.substring(0, c.length() - 2);
    }
    @Override
    public String toString() {
        return "\nTitle: " + getTitle() +
                "\nRating: " + getRating() +
                "\nCategories: " + getCategories() +
                "\nRelease Year: " + getReleaseYear();
    }
}
