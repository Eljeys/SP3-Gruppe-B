import java.util.ArrayList;

public class ListOfWatchedMedia implements List{
    private Database fileIO = new FileIO();
    private ArrayList<AMedia<?>> watchedMedia = new ArrayList<>();

    @Override
    public ArrayList<AMedia<?>> getAllMedias() {
        return watchedMedia;
    }

    @Override
    public boolean addMedia(AMedia<?> media) {
        for (AMedia<?> m: watchedMedia) {
            if (m.getInfo().equals(media.getInfo())) {
                return false;
            }
        }

        watchedMedia.add(media);
        return true;
    }

    @Override
    public boolean deleteMedia(AMedia<?> media) {
        for (AMedia<?> m: watchedMedia) {
            if (m.getInfo().equals(media.getInfo())) {
                watchedMedia.remove(m);
                return true;
            }
        }
        return false;
    }

    @Override
    public String getListType() {
        return "watched";
    }

    @Override
    public void saveList(User user) {
        fileIO.saveListData(user,this.getListType(), getAllMedias());
    }

    @Override
    public void loadList(User user) {
        watchedMedia = fileIO.loadListData(user, this.getListType());
    }
}