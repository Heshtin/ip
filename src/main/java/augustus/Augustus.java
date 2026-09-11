package augustus;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import augustus.exception.AugustusException;
import augustus.parser.Parser;
import augustus.storage.TaskStorage;
import augustus.task.Deadline;
import augustus.task.Event;
import augustus.task.Task;
import augustus.task.TaskList;
import augustus.task.ToDo;
import augustus.ui.Ui;

/**
 * Represents the main Augustus chatbot application.
 * Coordinates user interaction, task management, parsing, and storage.
 */
public class Augustus {
    private static final String DEFAULT_FILE_PATH = "./src/data/augustus.txt";

    private TaskStorage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Creates an Augustus chatbot using the specified file for task storage.
     *
     * @param filePath Path of the file used to store tasks.
     */
    public Augustus(String filePath) {
        this.ui = new Ui();
        this.storage = new TaskStorage(filePath);
        try {
            storage.createFile();
            tasks = new TaskList(storage.loadTasks());
        } catch (AugustusException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main command loop and processes user commands until the user exits.
     */
    public void run() {
        ui.showIntro();
        while (true) {
            String input = ui.readLine();
            try {
                String command = Parser.getCommand(input);

                if (command.equals("bye")) {
                    ui.showExit();
                    break;
                } else if (command.equals("list")) {
                    StringBuilder listMessage = new StringBuilder("These are the tasks in the list: \n");
                    for (int i = 0; i < tasks.getTaskCount(); i++) {
                        listMessage.append((i + 1) + ". " + tasks.get(i) + "\n");
                    }
                    ui.showMessage(listMessage.toString());

                } else if (command.equals("find")) {
                    String keyword = Parser.parseFind(input);
                    ArrayList<Task> matchingTasks = tasks.find(keyword);

                    StringBuilder message =
                            new StringBuilder("Here are the matching tasks in your list:\n");

                    for (int i = 0; i < matchingTasks.size(); i++) {
                        message.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
                    }

                    ui.showMessage(message.toString());

                } else if (command.equals("mark")) {
                    int num = Parser.parseTaskNumber(input);
                    if (num < 1 || num > tasks.getTaskCount()) {
                        throw new AugustusException("Write a valid task number");
                    }
                    Task task = tasks.get(num - 1);
                    task.markDone();
                    storage.saveTasks(tasks.getTasks());

                    ui.showMessage("I have marked this task as done:\n" + "   " + task);

                } else if (command.equals("unmark")) {
                    int num = Parser.parseTaskNumber(input);
                    if (num < 1 || num > tasks.getTaskCount()) {
                        throw new AugustusException("Write a valid task number");
                    }
                    Task task = tasks.get(num - 1);
                    task.markNotDone();

                    storage.saveTasks(tasks.getTasks());
                    ui.showMessage("I have marked this task as undone:\n" + "   " + task);

                } else if (command.equals("todo")) {
                    String description = Parser.parseTodo(input);
                    Task task = new ToDo(description);
                    tasks.add(task);
                    storage.saveTasks(tasks.getTasks());

                    ui.showAddTask(task.toString());
                    ui.showTaskCount(tasks.getTaskCount());

                } else if (command.equals("deadline")) {
                    String[] details = Parser.parseDeadline(input);

                    String description = details[0];
                    String dateString = details[1];

                    LocalDate by;
                    try {
                        by = LocalDate.parse(dateString);
                    } catch (DateTimeException e) {
                        throw new AugustusException("The date is not in yyyy-MM-dd format");
                    }

                    Task task = new Deadline(description, by);
                    tasks.add(task);
                    storage.saveTasks(tasks.getTasks());

                    ui.showAddTask(task.toString());
                    ui.showTaskCount(tasks.getTaskCount());

                } else if (command.equals("event")) {
                    String[] details = Parser.parseEvent(input);

                    String description = details[0];
                    String from = details[1];
                    String to = details[2];

                    Task task = new Event(description, from, to);
                    tasks.add(task);

                    storage.saveTasks(tasks.getTasks());

                    ui.showAddTask(task.toString());
                    ui.showTaskCount(tasks.getTaskCount());

                } else if (command.equals("delete")) {
                    int num = Parser.parseTaskNumber(input);

                    if (num < 1 || num > tasks.getTaskCount()) {
                        throw new AugustusException("Write a valid task number");
                    }

                    Task removedTask = tasks.delete(num - 1);
                    storage.saveTasks(tasks.getTasks());

                    ui.showMessage("Good, this task has been removed:\n" + "   " + removedTask);
                    ui.showTaskCount(tasks.getTaskCount());
                } else {
                    ui.showCommands();
                }
            } catch (AugustusException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner();
    }

    /**
     * Processes the user's input and returns Augustus's response.
     *
     * @param input user input to process
     * @return response generated by Augustus
     */
    public String getResponse(String input) {
        try {
            String command = Parser.getCommand(input);

            if (command.equals("bye")) {
                return "Bye. Thank you for using this chatbot!\n"
                        + "Hope to see you again soon!";
            } else if (command.equals("list")) {
                StringBuilder message =
                        new StringBuilder("These are the tasks in the list:\n");

                for (int i = 0; i < tasks.getTaskCount(); i++) {
                    message.append(i + 1)
                            .append(". ")
                            .append(tasks.get(i))
                            .append("\n");
                }

                return message.toString();

            } else if (command.equals("find")) {
                String keyword = Parser.parseFind(input);
                ArrayList<Task> matchingTasks = tasks.find(keyword);

                StringBuilder message =
                        new StringBuilder("Here are the matching tasks in your list:\n");

                for (int i = 0; i < matchingTasks.size(); i++) {
                    message.append(i + 1)
                            .append(". ")
                            .append(matchingTasks.get(i))
                            .append("\n");
                }

                return message.toString();

            } else if (command.equals("mark")) {
                int num = Parser.parseTaskNumber(input);

                if (num < 1 || num > tasks.getTaskCount()) {
                    throw new AugustusException("Write a valid task number");
                }

                Task task = tasks.get(num - 1);
                task.markDone();
                storage.saveTasks(tasks.getTasks());

                return "I have marked this task as done:\n   " + task;

            } else if (command.equals("unmark")) {
                int num = Parser.parseTaskNumber(input);

                if (num < 1 || num > tasks.getTaskCount()) {
                    throw new AugustusException("Write a valid task number");
                }

                Task task = tasks.get(num - 1);
                task.markNotDone();
                storage.saveTasks(tasks.getTasks());

                return "I have marked this task as undone:\n   " + task;

            } else if (command.equals("todo")) {
                String description = Parser.parseTodo(input);
                Task task = new ToDo(description);

                tasks.add(task);
                storage.saveTasks(tasks.getTasks());

                return "Got it. I've added this task:\n"
                        + "   " + task
                        + "\nThe empire now holds " + tasks.getTaskCount() + " tasks.";

            } else if (command.equals("deadline")) {
                String[] details = Parser.parseDeadline(input);

                String description = details[0];
                String dateString = details[1];

                LocalDate by;
                try {
                    by = LocalDate.parse(dateString);
                } catch (DateTimeException e) {
                    throw new AugustusException(
                            "The date is not in yyyy-MM-dd format");
                }

                Task task = new Deadline(description, by);
                tasks.add(task);
                storage.saveTasks(tasks.getTasks());

                return "Got it. I've added this task:\n"
                        + "   " + task
                        + "\nThe empire now holds " + tasks.getTaskCount() + " tasks.";

            } else if (command.equals("event")) {
                String[] details = Parser.parseEvent(input);

                String description = details[0];
                String from = details[1];
                String to = details[2];

                Task task = new Event(description, from, to);
                tasks.add(task);
                storage.saveTasks(tasks.getTasks());

                return "Got it. I've added this task:\n"
                        + "   " + task
                        + "\nThe empire now holds " + tasks.getTaskCount() + " tasks.";

            } else if (command.equals("delete")) {
                int num = Parser.parseTaskNumber(input);

                if (num < 1 || num > tasks.getTaskCount()) {
                    throw new AugustusException("Write a valid task number");
                }

                Task removedTask = tasks.delete(num - 1);
                storage.saveTasks(tasks.getTasks());

                return "Good, this task has been removed:\n"
                        + "   " + removedTask
                        + "\nThe empire now holds " + tasks.getTaskCount() + " tasks.";
            }

            return "Augustus does not recognise that command.";

        } catch (AugustusException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    /**
     * Starts the Augustus chatbot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Augustus(DEFAULT_FILE_PATH).run();
    }
}

