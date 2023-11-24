import java.util.ArrayList;

public class ListOfFavorites implements List{
    private final Database fileIO = new FileIO();
    private ArrayList<AMedia> allFavoriteMedias = new ArrayList<>();

    @Override
    public ArrayList<AMedia> getAllMedias() {
        return allFavoriteMedias;
    }

    @Override
    public String getListType() {
        return "favorites";
    }

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

    @Override
    public boolean deleteMedia(AMedia media) {
        for (AMedia favorite: allFavoriteMedias) {
            if (favorite.getData().equals(media.getData())) {
                allFavoriteMedias.remove(favorite);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean saveList(User user) {
        return fileIO.saveListData(user, this.getListType(), this.getAllMedias());
    }

    @Override
    public void loadList(User user) {
        allFavoriteMedias = fileIO.loadListData(user, this.getListType());
    }
}
