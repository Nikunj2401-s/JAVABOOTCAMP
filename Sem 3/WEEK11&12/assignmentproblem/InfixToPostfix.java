import java.util.Stack;

public class InfixToPostfix {
    // Method to check operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    // Convert infix to postfix
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                stack.pop();
            } else { // operator
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    result.append(stack.pop());
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            result.append(stack.pop());

        return result.toString();
    }

    public static void main(String[] args) {
        String infix = "A+B*C";
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
    }
}
