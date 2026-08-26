
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskStorage {
    private String path;

    public TaskStorage(String path) {
        this.path = path;
    }

    /**
     * Create a file if it does not exist
     *
     * @throws AugustusException
     */
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

    /**
     * Saves the tasks
     * @param arr
     * @throws AugustusException
     */
    public void saveTasks(ArrayList<Task> arr) throws AugustusException{
        try {
            FileWriter write = new FileWriter(path);
            for (Task temp:arr){
                write.write(temp.toFileString()+"\n");
            }
            write.close();
        }catch (IOException e){
            throw new AugustusException("Unable to save tasks");
        }
    }
}