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
        ArrayList<Task> tasks = new ArrayList<>();

        while(true) {
            String input = sc.nextLine();

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
            }
            else if(input.startsWith("mark ")){
                int num = Integer.parseInt(input.substring(5));
                Task temp = tasks.get(num-1);
                temp.markDone();
                System.out.println(border);
                System.out.println("I have marked this task as done:");
                System.out.println("   "+temp);
                System.out.println(border);
            }
            else if(input.startsWith("unmark ")){
                int num = Integer.parseInt(input.substring(7));
                Task temp = tasks.get(num-1);
                temp.markNotDone();
                System.out.println(border);
                System.out.println("I have marked this task as not done:");
                System.out.println("   "+temp);
                System.out.println(border);
            }
            else if(input.startsWith("todo ")){
                String temp = input.substring(5);
                ToDos task1 = new ToDos(temp);
                tasks.add(task1);
                System.out.println(border);
                System.out.println("By order of Augustus, this task has been added:");
                System.out.println("   " + task1);
                System.out.println("The empire now holds " + tasks.size() + " tasks.");
                System.out.println(border);

            }
            else if(input.startsWith("deadline ")){
                int index = input.indexOf(" /by ");
                String temp = input.substring(9,index);
                String by = input.substring(index+5);
                Deadline task1 = new Deadline(temp,by);
                tasks.add(task1);
                System.out.println(border);
                System.out.println("By order of Augustus, this task has been added:");
                System.out.println("   " + task1);
                System.out.println("The empire now holds " + tasks.size() + " tasks.");
                System.out.println(border);

            }
            else if(input.startsWith("event ")){
                int index1 = input.indexOf(" /from ");
                int index2 = input.indexOf(" /to ");
                String temp = input.substring(6,index1);
                String from = input.substring(index1+7,index2);
                String to = input.substring(index2+5);
                Event task1 = new Event(temp,from,to);
                tasks.add(task1);
                System.out.println(border);
                System.out.println("By order of Augustus, this task has been added:");
                System.out.println("   " + task1);
                System.out.println("The empire now holds " + tasks.size() + " tasks.");
                System.out.println(border);
            }
            else {
                System.out.println(border);
                System.out.println("You have said: " + input);
                System.out.println(border);
            }
        }
        sc.close();

        System.out.println("Hope to see you again soon!");
        System.out.println(border);

    }
}
