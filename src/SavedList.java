import javax.print.attribute.standard.Media;
import java.util.ArrayList;

public class SavedList implements List{
    private Database fileIO;
    private ArrayList<AMedia> savedMedia = new ArrayList<>();

    @Override
    public ArrayList<AMedia> getMediaList() {
        return savedMedia;
    }

    @Override
    public boolean addMedia(AMedia media) {
        for (AMedia m: savedMedia) {
            if (m.toString().equals(media.toString())) {
                return false;
            }
        }

        savedMedia.add(media);
        return true;
    }

    @Override
    public boolean deleteMedia(AMedia media) {
        for (AMedia m: savedMedia) {
            if (m.toString().equals(media.toString())) {
                savedMedia.remove(m);
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveList(ArrayList<String> mediaData) {
        fileIO = new FileIO();
        fileIO.saveListData("saved", mediaData, savedMedia);
    }
}
