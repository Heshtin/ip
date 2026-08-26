
import java.io.File;
import java.io.IOException;

public class TaskStorage {
    private String path;

    public TaskStorage(String path) {
        this.path = path;
    }

    public void createFile() throws AugustusException {
        try {
            File file = new File(path);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e){
            throw new AugustusException("Unable to access/create the data file");
        }
    }
}