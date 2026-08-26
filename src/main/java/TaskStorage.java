
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
     * Saves the tasks for each operation such as adding, deleting, unmark, mark
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

    /**
     * Loads the tasks from the text file
     * @return ArrayList<Task>
     * @throws AugustusException
     */
    public ArrayList<Task> loadTasks() throws AugustusException{
        ArrayList<Task> temp = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.isBlank()){
                    continue;
                }
                String[] seg = line.split(" \\| ");
                String taskType = seg[0];
                boolean mark  = seg[1].equals("1");
                String desc = seg[2];
                Task task;
                if(taskType.equals("T")){
                    task = new ToDos(desc);
                } else if(taskType.equals("D")){
                    String by = seg[3];
                    task = new Deadline(desc,by);
                } else if(taskType.equals("E")){
                    String from = seg[3];
                    String to = seg[4];
                    task = new Event(desc,from,to);
                } else {
                    throw new AugustusException("Invalid task type in data file");
                }
                if(mark){
                    task.markDone();
                }
                temp.add(task);
            }

            sc.close();

        }catch (IOException e){
            throw new AugustusException("Unable to load tasks");
        }
        return temp;
    }

}