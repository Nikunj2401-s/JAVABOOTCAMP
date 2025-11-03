import java.util.Stack;

public class UndoFeature {
    public static void main(String[] args) {
        Stack<String> actions = new Stack<>();
        actions.push("Type 'Hello'");
        actions.push("Type 'World'");
        actions.push("Delete 'World'");

        System.out.println("Current Actions: " + actions);
        System.out.println("Undoing last action: " + actions.pop());
        System.out.println("After Undo: " + actions);
    }
}
