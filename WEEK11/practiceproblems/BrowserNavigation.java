import java.util.*;

public class BrowserNavigation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> backStack = new Stack<>();
        Stack<String> forwardStack = new Stack<>();
        String currentPage = "Home";

        while (true) {
            System.out.println("\nCurrent Page: " + currentPage);
            System.out.print("Enter 'visit', 'back', 'forward', or 'exit': ");
            String action = sc.nextLine().toLowerCase();

            switch (action) {
                case "visit":
                    System.out.print("Enter new page name: ");
                    String newPage = sc.nextLine();
                    backStack.push(currentPage);
                    currentPage = newPage;
                    forwardStack.clear();
                    break;

                case "back":
                    if (!backStack.isEmpty()) {
                        forwardStack.push(currentPage);
                        currentPage = backStack.pop();
                    } else {
                        System.out.println("No pages to go back to.");
                    }
                    break;

                case "forward":
                    if (!forwardStack.isEmpty()) {
                        backStack.push(currentPage);
                        currentPage = forwardStack.pop();
                    } else {
                        System.out.println("No pages to go forward to.");
                    }
                    break;

                case "exit":
                    System.out.println("Exiting browser simulation...");
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
