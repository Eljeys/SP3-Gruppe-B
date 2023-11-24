import java.util.ArrayList;

public interface List {
    ArrayList<AMedia> getAllMedias();
    boolean addMedia(AMedia media);
    boolean removeMedia(AMedia media);
    boolean saveList(User user);
    void loadList(User user);
    String getListType();
}