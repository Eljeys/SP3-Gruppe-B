import java.util.ArrayList;

public class ListOfFavorites implements List{
    private final Database fileIO = new FileIO();
    private ArrayList<AMedia> allFavoriteMedias = new ArrayList<>();

    /**
     * Gets all AMedia objects from the list of favorites
     * @return Array list of all favorite medias
     */
    @Override
    public ArrayList<AMedia> getAllMedias() {
        return allFavoriteMedias;
    }

    /**
     * Gets the type of list which is a favorite list
     * @return Type of list as a String
     */
    @Override
    public String getListType() {
        return "favorites";
    }

    /**
     * Adds the given AMedia object to the favorite list
     * @param media The given media to add
     * @return true or false depending on if it has been added
     */
    @Override
    public boolean addMedia(AMedia media) {
        for (AMedia favorite: allFavoriteMedias) {
            if (favorite.getData().equals(media.getData())) {
                return false;
            }
        }

        allFavoriteMedias.add(media);
        return true;
    }

    /**
     * Removes the given AMedia object from the favorite list
     * @param media The given media to remove
     * @return true or false depending on if it has been removed
     */
    @Override
    public boolean removeMedia(AMedia media) {
        for (AMedia favorite: allFavoriteMedias) {
            if (favorite.getData().equals(media.getData())) {
                allFavoriteMedias.remove(favorite);
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the favorite list to the Database under the given user
     * @param user The user whose favorite list is saved
     * @return true of false depending on if the list was saved
     */
    @Override
    public boolean saveList(User user) {
        return fileIO.saveListData(user, this.getListType(), this.getAllMedias());
    }

    /**
     * Loads the favorite list from the Database from the given user
     * @param user The user whose favorite list is being loaded
     */
    @Override
    public void loadList(User user) {
        allFavoriteMedias = fileIO.loadListData(user, this.getListType());
    }
}
