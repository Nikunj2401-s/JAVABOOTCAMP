import java.util.*;

public class UndoFeature {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> textStack = new Stack<>();

        while (true) {
            System.out.print("Enter 'type', 'undo', or 'exit': ");
            String action = sc.nextLine().toLowerCase();

            switch (action) {
                case "type":
                    System.out.print("Enter word to type: ");
                    String word = sc.nextLine();
                    textStack.push(word);
                    System.out.println("Current Text: " + String.join(" ", textStack));
                    break;

                case "undo":
                    if (!textStack.isEmpty()) {
                        String removed = textStack.pop();
                        System.out.println("Undo: Removed '" + removed + "'");
                    } else {
                        System.out.println("Nothing to undo.");
                    }
                    System.out.println("Current Text: " + String.join(" ", textStack));
                    break;

                case "exit":
                    System.out.println("Exiting editor...");
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
