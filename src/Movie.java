import java.util.ArrayList;

public class Movie extends AMedia{
    public Movie(String title, int releaseYear, double rating, ArrayList<String> categories) {
        super(title, releaseYear, rating, categories);
    }

    @Override
    public void play() {

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
