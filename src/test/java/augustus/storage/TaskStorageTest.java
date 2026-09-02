package augustus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import augustus.exception.AugustusException;
import augustus.task.Task;
import java.time.LocalDate;

import augustus.task.Deadline;
import augustus.task.Event;
import augustus.task.ToDos;

public class TaskStorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void loadTasks_validFile_loadsTasksCorrectly()
            throws IOException, AugustusException {

        Path file = tempDir.resolve("augustus.txt");

        String data = "T | 1 | read book\n"
                + "D | 0 | submit assignment | 2026-09-10\n"
                + "E | 0 | project meeting | 2pm | 4pm\n";

        Files.writeString(file, data);

        TaskStorage storage = new TaskStorage(file.toString());

        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(3, tasks.size());
        assertEquals("T | 1 | read book", tasks.get(0).toFileString());
        assertEquals("D | 0 | submit assignment | 2026-09-10", tasks.get(1).toFileString());
        assertEquals("E | 0 | project meeting | 2pm | 4pm", tasks.get(2).toFileString());
    }

    @Test
    public void loadTasks_emptyFile_returnsEmptyList()
            throws IOException, AugustusException {

        Path file = tempDir.resolve("empty.txt");
        Files.createFile(file);

        TaskStorage storage = new TaskStorage(file.toString());
        ArrayList<Task> tasks = storage.loadTasks();
        assertEquals(0, tasks.size());
    }
    @Test
    public void saveTasks_validTasks_savesCorrectly()
            throws IOException, AugustusException {

        Path file = tempDir.resolve("augustus.txt");

        TaskStorage storage = new TaskStorage(file.toString());
        storage.createFile();

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("read book"));
        tasks.add(new Deadline("submit assignment", LocalDate.of(2026, 9, 10)));
        tasks.add(new Event("project meeting", "2pm", "4pm"));

        storage.saveTasks(tasks);
        String result = Files.readString(file);

        String expected = "T | 0 | read book\n"
                + "D | 0 | submit assignment | 2026-09-10\n"
                + "E | 0 | project meeting | 2pm | 4pm\n";

        assertEquals(expected, result);
    }
}