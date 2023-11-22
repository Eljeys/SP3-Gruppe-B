import java.util.ArrayList;

public abstract class AMedia <T> {
    private Database io;
    private String info;
    private String title;
    private String releaseYear;
    private double rating;
    private ArrayList<String> categories;

    public AMedia(String mediaData) {
        this.info = mediaData;
        setMediaData();
    }

    abstract void setMediaData();
    abstract T getMediaData();

    abstract void play();

    public String getInfo() {
        return info;
    }

    public void saveToList(User user, String listType, AMedia<?> media) {
        List list = user.getList(listType);
        list.addMedia(media);
    }

    public void removeFromList(User user, String listType, AMedia<?> media) {
        List list = user.getList(listType);
        list.deleteMedia(media);
    }

   abstract String getMediaType();
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
       for (String s: categories) {
           c = c.concat(s) + ", ";
       }

       return c.substring(0,c.length()-2);
   }

    @Override
    public String toString() {
        return "\nTitle: " + getTitle() +
                "\nRating: " + getRating() +
                "\nCategories: " + getCategories() +
                "\nRelease Year: " + getReleaseYear();
    }
}
