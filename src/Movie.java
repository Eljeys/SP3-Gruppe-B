import java.util.ArrayList;

public class Movie extends AMedia <Movie>{
    public Movie(String info) {
        super(info);
    }

    @Override
    public void play() {

    }

    @Override
    public void setMediaData() {
        ArrayList<String> categories = new ArrayList<>();

        String[] row = getInfo().split(";");
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

    @Override
    public Movie getMediaData() {
        return this;
    }

    @Override
    public String getMediaType() {
        return "MOVIE";
    }

    @Override
    public String toString() {
        return getMediaType() +
                super.toString();
    }
}
