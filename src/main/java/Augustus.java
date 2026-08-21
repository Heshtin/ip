import java.util.Scanner;
public class Augustus {
    public static void main(String[] args) {
        String border = "__________________________";
        System.out.println(border);
        System.out.println("Hello! I'm Augustus");
        System.out.println("What can I do for you?");
        System.out.println(border);

        Scanner sc = new Scanner(System.in);

        while(true){
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(border);
                System.out.println("Bye. Thank you for using this chatbot");
                System.out.println(border);
                break;
            }
            System.out.println(border);
            System.out.println(input);
            System.out.println(border);

        }

        System.out.println("Hope to see you again soon!");
        System.out.println(border);

    }
}
