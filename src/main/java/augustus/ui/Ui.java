package augustus.ui;

import java.util.Scanner;

/**
 * Handles user input and creates messages to display to the user.
 */
public class Ui {
    private static final String BORDER = "__________________________";
    private final Scanner scanner;

    /**
     * Creates a Ui and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome message.
     *
     * @return Welcome message.
     */
    public String getIntroMessage() {
        return "Hello! I'm Augustus\n"
                + "What can I do for you?";
    }

    /**
     * Reads the next line of user input.
     *
     * @return The line entered by the user.
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Displays a message surrounded by borders.
     *
     * @param message Message to display.
     */
    public void showMessage(String message) {
        System.out.println(BORDER);
        System.out.println(message);
        System.out.println(BORDER);
    }

    /**
     * Returns an error message.
     *
     * @param message Error message.
     * @return Formatted error message.
     */
    public String getErrorMessage(String message) {
        return "ERROR: " + message;
    }

    /**
     * Returns the farewell message.
     *
     * @return Farewell message.
     */
    public String getExitMessage() {
        return "Bye. Thank you for using this chatbot\n"
                + "Hope to see you again soon!";
    }

    /**
     * Closes the scanner used to read user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Returns the available commands.
     *
     * @return Available commands message.
     */
    public String getCommandsMessage() {
        return "Augustus does not recognise that command.\n"
                + "Available commands:\n"
                + " todo <description>\n"
                + " deadline <description> /by yyyy-MM-dd\n"
                + " event <description> /from (start) /to (end)\n"
                + " list\n"
                + " find <keyword>\n"
                + " mark <number>\n"
                + " unmark <number>\n"
                + " delete <number>\n"
                + " tag <number> /t (description)\n"
                + " bye";
    }

    /**
     * Returns a message indicating that a task was added.
     *
     * @param task Task that was added.
     * @return Task added message.
     */
    public String getAddTaskMessage(String task) {
        return "By order of Augustus, this task has been added:\n"
                + "   " + task;
    }

    /**
     * Returns the current task count message.
     *
     * @param num Number of tasks.
     * @return Task count message.
     */
    public String getTaskCountMessage(int num) {
        return "The empire now holds " + num + " tasks.";
    }

    /**
     * Returns a message indicating that a task was marked as done.
     *
     * @param task Task that was marked.
     * @return Task marked message.
     */
    public String getMarkTaskMessage(String task) {
        return "I have marked this task as done:\n"
                + "   " + task;
    }

    /**
     * Returns a message indicating that a task was marked as undone.
     *
     * @param task Task that was unmarked.
     * @return Task unmarked message.
     */
    public String getUnmarkTaskMessage(String task) {
        return "I have marked this task as undone:\n"
                + "   " + task;
    }

    /**
     * Returns a message indicating that a task was deleted.
     *
     * @param task Task that was deleted.
     * @return Task deleted message.
     */
    public String getDeleteTaskMessage(String task) {
        return "Good, this task has been removed:\n"
                + "   " + task;
    }
}
