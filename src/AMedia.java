import javax.print.attribute.standard.Media;
import java.util.ArrayList;

public abstract class AMedia {
    private Database io;
    private String title;
    private String releaseYear;
    private double rating;
    private ArrayList<String> categories;

    public AMedia(String title, String releaseYear, double rating, ArrayList<String> categories) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categories = categories;
    }

   abstract void play();

   public void saveToList(User user, String listType, AMedia media) {
       List list = user.getList(listType);
       list.addMedia(media);
   }

   public void removeFromList(User user, String listType, AMedia media) {
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
