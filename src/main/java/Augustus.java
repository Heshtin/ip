import java.time.DateTimeException;
import java.time.LocalDate;

public class Augustus {
    private TaskStorage storage;
    private TaskList tasks;
    private Ui ui;

    public Augustus(String filepath){
        this.ui = new Ui();
        this.storage = new TaskStorage(filepath);
        try{
            storage.createFile();
            tasks = new TaskList(storage.loadTasks());
        } catch (AugustusException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showIntro();
        while(true) {
            String input = ui.readLine();
            try {
                String command = Parser.getCommand(input);

                if (command.equals("bye")) {
                    ui.showExit();
                    break;

                } else if (command.equals("list")) {
                    StringBuilder showList = new StringBuilder("These are the tasks in the list: \n");
                    for (int i = 0; i < tasks.size(); i++) {
                        showList.append((i + 1) + ". " + tasks.get(i)+"\n");
                    }
                    ui.showMessage(showList.toString());

                } else if (command.equals("mark")) {
                    int num = Parser.parseTaskNum(input);
                    if (num < 1 || num > tasks.size()) {
                        throw new AugustusException("Write a valid task number");
                    }
                    Task task = tasks.get(num - 1);
                    task.markDone();
                    storage.saveTasks(tasks.getTasks());

                    ui.showMessage("I have marked this task as done:\n" + "   " + task);

                } else if (command.equals("unmark")) {
                    int num = Parser.parseTaskNum(input);
                    if (num < 1 || num > tasks.size()) {
                        throw new AugustusException("Write a valid task number");
                    }
                    Task task = tasks.get(num - 1);
                    task.markNotDone();

                    storage.saveTasks(tasks.getTasks());
                    ui.showMessage("I have marked this task as undone:\n" + "   " + task);

                } else if (command.equals("todo")) {
                    String description = Parser.parseTodo(input);
                    Task task = new ToDos(description);
                    tasks.add(task);
                    storage.saveTasks(tasks.getTasks());

                    ui.showAddTask(task.toString());
                    ui.showList(tasks.size());

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
                    ui.showList(tasks.size());

                } else if (command.equals("event")) {
                    String[] details = Parser.parseEvent(input);

                    String description = details[0];
                    String from = details[1];
                    String to = details[2];

                    Task task = new Event(description, from, to);
                    tasks.add(task);

                    storage.saveTasks(tasks.getTasks());

                    ui.showAddTask(task.toString());
                    ui.showList(tasks.size());

                } else if (command.equals("delete")) {
                    int num = Parser.parseTaskNum(input);

                    if (num < 1 || num > tasks.size()) {
                        throw new AugustusException("Write a valid task number");
                    }

                    Task removedTask = tasks.delete(num - 1);
                    storage.saveTasks(tasks.getTasks());

                    ui.showMessage("Good, this task has been removed:\n" + "   " + removedTask);
                    ui.showList(tasks.size());
                } else {
                    ui.showCommands();
                }
            } catch (AugustusException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.scannerClose();
    }

    public static void main(String[] args) {
        new Augustus("./src/data/augustus.txt").run();
    }
}

