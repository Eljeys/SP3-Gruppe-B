import javax.print.attribute.standard.Media;
import java.util.ArrayList;

public class WatchedList implements List{
    private Database fileIO;
    private ArrayList<AMedia> watchedMedia;

    public WatchedList() {
        watchedMedia = new ArrayList<>();
    }

    @Override
    public ArrayList<AMedia> getMediaList() {
        return watchedMedia;
    }

    @Override
    public boolean addMedia(AMedia media) {
        for (AMedia m: watchedMedia) {
            if (m.toString().equals(media.toString())) {
                return false;
            }
        }

        watchedMedia.add(media);
        return true;
    }

    @Override
    public void deleteMedia(AMedia media) {
        watchedMedia.remove(media);
    }

    @Override
    public void saveList(ArrayList<String> mediaData) {
        fileIO = new FileIO();
        fileIO.saveListData("watched", mediaData, watchedMedia);
    }
}
