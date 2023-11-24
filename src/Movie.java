import java.util.ArrayList;

public class Movie extends AMedia {
    public Movie(String data) {
        super(data);
    }

    /**
     * Splits all the data that's passed as a String when creating a Movie object.
     * Sets all the information for the Movie object.
     */
    @Override
    public void setAllInformation() {
        ArrayList<String> categories = new ArrayList<>();

        String[] row = getData().split(";");
        String title = row[0].trim();
        String releaseYear = row[1].trim();
        String r = row[3].trim().replace(',', '.');
        double rating = Double.parseDouble(r);
        String c = row[2].trim();
        String[] category = c.split(",");
        for (String s : category) {
            categories.add(s.trim());
        }

        super.setTitle(title);
        super.setReleaseYear(releaseYear);
        super.setRating(rating);
        super.setCategories(categories);
    }

    /**
     * Gets the type of object which is Movie
     * @return The type as a String
     */
    @Override
    public String getType() {
        return "MOVIE";
    }

    /**
     * Display Movie object a certain way
     * @return The String to display
     */
    @Override
    public String toString() {
        return getType() +
                super.toString();
    }
}
