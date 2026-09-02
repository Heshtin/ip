package augustus.ui;

import java.util.Scanner;

/**
 * Handles user input and displays messages to the user
 */
public class Ui {
    private static final String Border = "__________________________";
    private final Scanner scanner;

    /**
     * Creates a Ui and initializes the scanner for reading user input.
     */
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when Augustus starts
     */
    public void showIntro(){
        System.out.println(Border);
        System.out.println("Hello! I'm Augustus");
        System.out.println("What can I do for you?");
        System.out.println(Border);
    }

    /**
     * Reads the next line of user input
     *
     * @return the line entered by the user
     */
    public String readLine(){
        return scanner.nextLine();
    }

    /**
     * Displays a message surrounded by borders
     *
     * @param message Message to display
     */
    public void showMessage(String message){
        System.out.println(Border);
        System.out.println(message);
        System.out.println(Border);
    }

    /**
     * Displays the error message to the user
     *
     * @param message Error message to display
     */
    public void showError(String message){
        System.out.println(Border);
        System.out.println("ERROR: " + message);
        System.out.println(Border);
    }

    /**
     * Displays the farewell message when Augustus exits.
     */
    public void showExit(){
        System.out.println(Border);
        System.out.println("Bye. Thank you for using this chatbot");
        System.out.println(Border);
        System.out.println("Hope to see you again soon!");
        System.out.println(Border);
    }

    /**
     * Closes the scanner used to read user input.
     */
    public void scannerClose(){
        scanner.close();
    }

    /**
     * Displays the available commands when an unrecognized command is entered.
     */
    public void showCommands(){
        String commands = "Augustus does not recognise that command.\n"
                + "Available commands:\n"
                + " todo <description>\n"
                + " deadline <description> /by yyyy-MM-dd\n"
                + " event <description> /from (start) /to (end)\n"
                + " list\n"
                + " mark <number>\n"
                + " unmark <number>\n"
                + " delete <number>\n"
                + " bye";
        this.showMessage(commands);
    }

    /**
     * Displays a message indicating that a task was added.
     *
     * @param task Task that was added.
     */
    public void showAddTask(String task){
        this.showMessage("By order of Augustus, this task has been added:\n" + "   " + task);
    }

    /**
     * Displays the number of tasks currently stored.
     *
     * @param num Number of tasks.
     */
    public void showList(int num){
        this.showMessage("The empire now holds " + num + " tasks.");
    }
}
