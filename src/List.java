import javax.print.attribute.standard.Media;
import java.util.ArrayList;

public interface List {
    ArrayList<AMedia> getMediaList();
    boolean addMedia(AMedia media);
    void deleteMedia(AMedia media);
    void saveList(ArrayList<String> mediaData);
}