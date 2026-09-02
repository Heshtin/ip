package augustus.storage;

import augustus.exception.AugustusException;
import augustus.task.Deadline;
import augustus.task.Event;
import augustus.task.Task;
import augustus.task.ToDos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the saving and loading of tasks from a data file
 */
public class TaskStorage {
    private String path;

    /**
     * Creates a TaskStorage that uses the specified file path
     *
     * @param path Path of the file used to store tasks.
     */
    public TaskStorage(String path) {
        this.path = path;
    }

    /**
     * Create the data file and its parent directories if they do not exist
     *
     * @throws AugustusException if the data file cannot be created or accessed
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
     * Saves all the tasks to the data file
     *
     * @param arr Tasks to be saved
     * @throws AugustusException If the tasks cannot be saved to the file
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
     * Loads the tasks from the data file
     *
     * @return A list containing the tasks loaded from the file
     * @throws AugustusException If the tasks cannot be loaded or the stored data is invalid
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
                    LocalDate by = LocalDate.parse(seg[3]);
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