import java.util.ArrayList;

public class ListOfFavorites implements List{
    private Database fileIO = new FileIO();
    private ArrayList<AMedia<?>> favoriteMedias = new ArrayList<>();

    @Override
    public ArrayList<AMedia<?>> getAllMedias() {
        return favoriteMedias;
    }

    @Override
    public String getListType() {
        return "favorites";
    }

    @Override
    public boolean addMedia(AMedia<?> media) {
        for (AMedia<?> m: favoriteMedias) {
            if (m.getInfo().equals(media.getInfo())) {
                return false;
            }
        }

        favoriteMedias.add(media);
        return true;
    }

    @Override
    public boolean deleteMedia(AMedia<?> media) {
        for (AMedia<?> m: favoriteMedias) {
            if (m.getInfo().equals(media.getInfo())) {
                favoriteMedias.remove(m);
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveList(User user) {
        fileIO.saveListData(user,this.getListType(), this.getAllMedias());
    }

    @Override
    public void loadList(User user) {
        favoriteMedias = fileIO.loadListData(user,this.getListType());
    }
}
