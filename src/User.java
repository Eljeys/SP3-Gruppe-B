public class User {
    private String username;
    private String password;
    private final List listOfWatchedMedia = new ListOfWatchedMedia();
    private final List listOfFavorites = new ListOfFavorites();

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

    public List getList(String listType) {
        List list = null;

        if (listType.equalsIgnoreCase(listOfWatchedMedia.getListType())) {
            list = listOfWatchedMedia;
        } else if (listType.equalsIgnoreCase(listOfFavorites.getListType())) {
            list = listOfFavorites;
        }

        if (list != null) {
            list.loadList(this);
        }

        return list;
    }
}
