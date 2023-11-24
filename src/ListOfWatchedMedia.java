import java.util.ArrayList;

public class ListOfWatchedMedia implements List{
    private final Database fileIO = new FileIO();
    private ArrayList<AMedia> allWatchedMedia = new ArrayList<>();

    /**
     * Gets all AMedia objects from the list of watched
     * @return Array list of all watched medias
     */
    @Override
    public ArrayList<AMedia> getAllMedias() {
        return allWatchedMedia;
    }

    /**
     * Gets the type of list which is a watched list
     * @return Type of list as a String
     */
    @Override
    public String getListType() {
        return "watched";
    }

    /**
     * Adds the given AMedia object to the watched list
     * @param media The given media to add
     * @return true or false depending on if it has been added
     */
    @Override
    public boolean addMedia(AMedia media) {
        for (AMedia watched: allWatchedMedia) {
            if (watched.getData().equals(media.getData())) {
                return false;
            }
        }

        allWatchedMedia.add(media);
        return true;
    }

    /**
     * Removes the given AMedia object from the watched list
     * @param media The given media to remove
     * @return true or false depending on if it has been removed
     */
    @Override
    public boolean removeMedia(AMedia media) {
        for (AMedia watched: allWatchedMedia) {
            if (watched.getData().equals(media.getData())) {
                allWatchedMedia.remove(watched);
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the watched list to the Database under the given user
     * @param user The user whose watched list is saved
     * @return true of false depending on if the list was saved
     */
    @Override
    public boolean saveList(User user) {
        return fileIO.saveListData(user, this.getListType(), getAllMedias());
    }

    /**
     * Loads the watched list from the Database from the given user
     * @param user The user whose watched list is being loaded
     */
    @Override
    public void loadList(User user) {
        allWatchedMedia = fileIO.loadListData(user, this.getListType());
    }
}