import java.util.ArrayList;
import java.util.Scanner;

public class Augustus {
    public static void main(String[] args) {
        String border = "__________________________";
        System.out.println(border);
        System.out.println("Hello! I'm Augustus");
        System.out.println("What can I do for you?");
        System.out.println(border);

        Scanner sc = new Scanner(System.in);

        //access the file for task storage
        TaskStorage storage = new TaskStorage("./src/data/augustus.txt");
        try{
            storage.createFile();
        }catch (AugustusException e){
            System.out.println(e.getMessage());
        }
        ArrayList<Task> tasks = new ArrayList<>();

        while(true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println(border);
                    System.out.println("Bye. Thank you for using this chatbot");
                    System.out.println(border);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(border);
                    System.out.println("These are the tasks in the list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(border);
                } else if (input.startsWith("mark ")) {
                    int num;
                    try {
                        num = Integer.parseInt(input.substring(5));
                    } catch (NumberFormatException e){
                        throw new AugustusException("mark must be followed by a number");
                    }
                    if(num<1 || num >tasks.size()){
                        throw new AugustusException("Write a valid task number");
                    }
                    Task temp = tasks.get(num - 1);
                    temp.markDone();
                    storage.saveTasks(tasks);

                    System.out.println(border);
                    System.out.println("I have marked this task as done:");
                    System.out.println("   " + temp);
                    System.out.println(border);
                } else if (input.startsWith("unmark ")) {
                    int num;
                    try {
                        num = Integer.parseInt(input.substring(7));
                    } catch (NumberFormatException e){
                        throw new AugustusException("unmark must be followed by a number");
                    }
                    if(num<1 || num >tasks.size()){
                        throw new AugustusException("Write a valid task number");
                    }
                    Task temp = tasks.get(num - 1);
                    temp.markNotDone();
                    storage.saveTasks(tasks);

                    System.out.println(border);
                    System.out.println("I have marked this task as not done:");
                    System.out.println("   " + temp);
                    System.out.println(border);
                } else if (input.startsWith("todo ")) {
                    String temp = input.substring(5);
                    if (temp.isEmpty()){
                        throw new AugustusException("You cannot enter the empire without a decription");
                    }
                    ToDos task1 = new ToDos(temp);
                    tasks.add(task1);
                    storage.saveTasks(tasks);

                    System.out.println(border);
                    System.out.println("By order of Augustus, this task has been added:");
                    System.out.println("   " + task1);
                    System.out.println("The empire now holds " + tasks.size() + " tasks.");
                    System.out.println(border);

                } else if (input.startsWith("deadline ")) {
                    int index = input.indexOf(" /by ");
                    if (index == -1){
                        throw new AugustusException("A deadline must contain /by followed the date or time");
                    }
                    String temp = input.substring(9, index);
                    String by = input.substring(index + 5);
                    if (temp.isEmpty()){
                        throw new AugustusException("The deadline must have a description");
                    }
                    if (by.isEmpty()){
                        throw new AugustusException("Write when this deadline is due");
                    }
                    Deadline task1 = new Deadline(temp, by);
                    tasks.add(task1);
                    storage.saveTasks(tasks);

                    System.out.println(border);
                    System.out.println("By order of Augustus, this task has been added:");
                    System.out.println("   " + task1);
                    System.out.println("The empire now holds " + tasks.size() + " tasks.");
                    System.out.println(border);

                } else if (input.startsWith("event ")) {
                    int index1 = input.indexOf(" /from ");
                    int index2 = input.indexOf(" /to ");
                    if(index1 ==-1 || index2 ==-1 || index2 < index1){
                        throw new AugustusException("The message should include /from and /to");
                    }
                    String temp = input.substring(6, index1);
                    String from = input.substring(index1 + 7, index2);
                    String to = input.substring(index2 + 5);
                    if (temp.isEmpty()){
                        throw new AugustusException("The event must have a description");
                    }
                    if (from.isEmpty() || to.isEmpty()){
                        throw new AugustusException("Write when this event starts and ends");
                    }
                    Event task1 = new Event(temp, from, to);
                    tasks.add(task1);
                    storage.saveTasks(tasks);

                    System.out.println(border);
                    System.out.println("By order of Augustus, this task has been added:");
                    System.out.println("   " + task1);
                    System.out.println("The empire now holds " + tasks.size() + " tasks.");
                    System.out.println(border);
                } else if(input.startsWith("delete ")){
                    int num = Integer.parseInt(input.substring(7).trim());
                    Task removedTask = tasks.remove(num - 1);
                    storage.saveTasks(tasks);

                    System.out.println(border);
                    System.out.println("Good, this task has been removed:");
                    System.out.println("   " + removedTask);
                    System.out.println("The empire now holds " + tasks.size() + " tasks.");
                    System.out.println(border);

                } else {
                    String commands = "Available commands:\n todo <description>\n deadline <description> /by (date/time)\n" +
                            " event <description> /from (start) /to (end)\n list \n mark\n unmark\n bye\n";
                    throw new AugustusException("Augustus does not recognise that command.\n"+commands);
                }
            } catch (AugustusException e){
                System.out.println(border);
                System.out.println("ERROR: " + e.getMessage());
                System.out.println(border);
            }
        }
        sc.close();

        System.out.println("Hope to see you again soon!");
        System.out.println(border);

    }
}
