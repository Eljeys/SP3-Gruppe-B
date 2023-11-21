import javax.print.attribute.standard.Media;
import java.util.ArrayList;

public interface List {
    ArrayList<AMedia> getMediaList();
    void addMedia(Media media);
    void deleteMedia(Media media);
    void saveList(ArrayList<AMedia> listToSave);
    void updateList(ArrayList<AMedia> listToUpdate);

}