package augustus.ui;

import java.util.Scanner;

public class Ui {
    private static final String Border = "__________________________";
    private final Scanner scanner;

    public Ui(){
        this.scanner = new Scanner(System.in);
    }
    public void showIntro(){
        System.out.println(Border);
        System.out.println("Hello! I'm Augustus");
        System.out.println("What can I do for you?");
        System.out.println(Border);
    }

    public String readLine(){
        return scanner.nextLine();
    }

    public void showMessage(String message){
        System.out.println(Border);
        System.out.println(message);
        System.out.println(Border);
    }

    public void showError(String message){
        System.out.println(Border);
        System.out.println("ERROR: " + message);
        System.out.println(Border);
    }

    public void showExit(){
        System.out.println(Border);
        System.out.println("Bye. Thank you for using this chatbot");
        System.out.println(Border);
        System.out.println("Hope to see you again soon!");
        System.out.println(Border);
    }
    public void scannerClose(){
        scanner.close();
    }
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
    public void showAddTask(String task){
        this.showMessage("By order of Augustus, this task has been added:\n" + "   " + task);
    }

    public void showList(int num){
        this.showMessage("The empire now holds " + num + " tasks.");
    }
}
