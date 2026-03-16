import java.util.*;

public class PrintQueueSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<String> printQueue = new LinkedList<>();

        while (true) {
            System.out.print("Enter 'add', 'print', or 'exit': ");
            String action = sc.nextLine().toLowerCase();

            switch (action) {
                case "add":
                    System.out.print("Enter print job name: ");
                    String job = sc.nextLine();
                    printQueue.add(job);
                    System.out.println("Added '" + job + "' to the print queue.");
                    break;

                case "print":
                    if (!printQueue.isEmpty()) {
                        String currentJob = printQueue.poll();
                        System.out.println("Printing job: " + currentJob);
                    } else {
                        System.out.println("No jobs in the queue.");
                    }
                    break;

                case "exit":
                    System.out.println("Exiting print queue system...");
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
