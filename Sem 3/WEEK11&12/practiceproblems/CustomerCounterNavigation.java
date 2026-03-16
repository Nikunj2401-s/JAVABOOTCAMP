import java.util.*;

public class CustomerCounterNavigation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<String> customerQueue = new LinkedList<>();

        while (true) {
            System.out.print("Enter 'arrive', 'serve', or 'exit': ");
            String action = sc.nextLine().toLowerCase();

            switch (action) {
                case "arrive":
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    customerQueue.add(name);
                    System.out.println(name + " joined the queue.");
                    break;

                case "serve":
                    if (!customerQueue.isEmpty()) {
                        String served = customerQueue.poll();
                        System.out.println("Serving customer: " + served);
                    } else {
                        System.out.println("No customers in the queue.");
                    }
                    break;

                case "exit":
                    System.out.println("Exiting customer service counter...");
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
