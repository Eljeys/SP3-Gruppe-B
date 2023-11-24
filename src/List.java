import java.util.ArrayList;

public interface List {
    ArrayList<AMedia> getAllMedias();
    boolean addMedia(AMedia media);
    boolean deleteMedia(AMedia media);
    boolean saveList(User user);
    void loadList(User user);
    String getListType();
}