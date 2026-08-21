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
        ArrayList<String> tasks = new ArrayList<>();

        while(true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(border);
                System.out.println("Bye. Thank you for using this chatbot");
                System.out.println(border);
                break;
            } else if (input.equals("list")) {
                System.out.println(border);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println(border);
            }
            else {
                tasks.add(input);
                System.out.println(border);
                System.out.println("added: " +input);
                System.out.println(border);
            }
        }
        sc.close();

        System.out.println("Hope to see you again soon!");
        System.out.println(border);

    }
}
