public class User {
    private String username;
    private String password;
    private final List listOfWatchedMedia = new ListOfWatchedMedia();
    private final List listOfFavorites = new ListOfFavorites();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username
     * @return The username as a String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the current username to the given username
     * @param username String representing the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password. (Method not in use)
     * @return The password as a String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the current password to the given password. (Method not in use)
     * @param password String representing the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets either a favorite list or watched list depending on the list type
     * @param listType String representing a type of list
     * @return null or List object either ListOfFavorites or ListOfWatchedMedia
     */
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
