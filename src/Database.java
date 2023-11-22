import java.util.ArrayList;

public interface Database {
    ArrayList<String> loadUserData(String path);
    boolean saveUserData(String path, String username, String password);

    ArrayList<String> loadMediaData(String path);

    boolean saveListData(User user, String listType, ArrayList<AMedia<?>> listData);

    ArrayList<AMedia<?>> loadListData(User user, String listType);
}
