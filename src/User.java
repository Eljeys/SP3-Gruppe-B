import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<AMedia> getList(String listType) {
        ArrayList<AMedia> allMediaInList = new ArrayList<>();
        if (listType.equalsIgnoreCase("watched")) {
            List watchedList = new WatchedList();
            allMediaInList = watchedList.getMediaList();
        } else if (listType.equalsIgnoreCase("saved")) {
            List savedList = new SavedList();
            allMediaInList = savedList.getMediaList();
        }
        return allMediaInList;
    }

    public void setList(String listType, ArrayList<AMedia> listToSave) {
        if (listType.equalsIgnoreCase("watched")) {
            List watchedList = new WatchedList();
            watchedList.saveList(listToSave);
        } else if (listType.equalsIgnoreCase("saved")) {
            List savedList = new SavedList();
            savedList.saveList(listToSave);
        }
    }
}
