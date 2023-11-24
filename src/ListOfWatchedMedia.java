import java.util.ArrayList;

public class ListOfWatchedMedia implements List{
    private final Database fileIO = new FileIO();
    private ArrayList<AMedia> allWatchedMedia = new ArrayList<>();

    @Override
    public ArrayList<AMedia> getAllMedias() {
        return allWatchedMedia;
    }

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

    @Override
    public boolean deleteMedia(AMedia media) {
        for (AMedia watched: allWatchedMedia) {
            if (watched.getData().equals(media.getData())) {
                allWatchedMedia.remove(watched);
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
    public boolean saveList(User user) {
        return fileIO.saveListData(user, this.getListType(), getAllMedias());
    }

    @Override
    public void loadList(User user) {
        allWatchedMedia = fileIO.loadListData(user, this.getListType());
    }
}