import javax.print.attribute.standard.Media;
import java.util.ArrayList;

public abstract class AMedia {
    private Database io;
    private String title;
    private int releaseYear;
    private double rating;
    private ArrayList<String> categories;

    public AMedia(String title, int releaseYear, double rating, ArrayList<String> categories) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categories = categories;
    }

   abstract void play();
   public void saveToList(User user, String listType) {
       ArrayList<AMedia> medias = user.getList(listType);
       if (!medias.contains(this)) {
           medias.add(this);
           user.setList(listType, medias);
       }
   }
   public void removeFromList(User user, String listType) {
       ArrayList<AMedia> medias = user.getList(listType);
       if (medias.contains(this)) {
           medias.remove(this);
           user.setList(listType, medias);
       }
   }
   abstract String getMediaType();

   public String getTitle() {
       return title;
   }
   public int getReleaseYear() {
       return releaseYear;
   }
   public double getRating() {
       return rating;
   }

   public String getCategories() {
       String c = "";
       for (String s: categories) {
           c = c.concat(s) + ", ";
       }

       return c.substring(0,c.length()-1);
   }

    @Override
    public String toString() {
        return "\nTitle: " + getTitle() +
                "\nRelease Year: " + getReleaseYear() +
                "\nRating: " + getRating() +
                "\nCategories: " + getCategories();
    }
}
