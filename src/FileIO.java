import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements Database{

    public ArrayList<String> readUserData(String path) {
        ArrayList<String> data = new ArrayList<>();

        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                data.add(s);
            }
        } catch (FileNotFoundException e) {
            return null;
        }

        return data;
    }
}
