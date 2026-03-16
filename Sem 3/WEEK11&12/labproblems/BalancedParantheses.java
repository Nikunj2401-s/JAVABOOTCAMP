import java.util.*;

public class BalancedParantheses {
    public static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (char ch : expr.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String expr1 = "{[()()]}";
        String expr2 = "{[(])}";
        System.out.println(expr1 + " → " + (isBalanced(expr1) ? "Balanced" : "Not Balanced"));
        System.out.println(expr2 + " → " + (isBalanced(expr2) ? "Balanced" : "Not Balanced"));
    }
}
