import java.util.*;

public class TextCalculator {

    public static double evaluate(String expression) {
        return eval(new StringTokenizer(expression, "+-*/() ", true));
    }

    private static double eval(StringTokenizer tokens) {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        while (tokens.hasMoreTokens()) {
            String t = tokens.nextToken().trim();
            if (t.isEmpty()) continue;

            if (t.matches("\\d+")) {
                values.push(Double.parseDouble(t));
            } else if (t.equals("(")) {
                ops.push('(');
            } else if (t.equals(")")) {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            } else if ("+-*/".contains(t)) {
                char op = t.charAt(0);
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(op)) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(op);
            }
        }

        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String expr = sc.nextLine();

        try {
            double result = evaluate(expr);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Invalid expression.");
        }

        sc.close();
    }
}
