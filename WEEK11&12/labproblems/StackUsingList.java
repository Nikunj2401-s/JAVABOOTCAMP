import java.util.*;

class StackUsingList {
    private List<Integer> stack;

    public StackUsingList() {
        stack = new ArrayList<>();
    }

    public void push(int item) {
        stack.add(item);
        System.out.println(item + " pushed to stack");
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return stack.remove(stack.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void display() {
        System.out.println("Stack: " + stack);
    }

    public static void main(String[] args) {
        StackUsingList s = new StackUsingList();
        s.push(10);
        s.push(20);
        s.push(30);
        s.display();
        System.out.println("Top Element: " + s.peek());
        System.out.println("Popped: " + s.pop());
        s.display();
    }
}
