import java.util.*;

public class PasswordTool {

    public static String analyzeStrength(String password) {
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSpecial = true;
        }

        if (password.length() >= 10 && hasUpper && hasLower && hasDigit && hasSpecial)
            return "Strong";
        else if (password.length() >= 6 && ((hasUpper && hasLower) || hasDigit))
            return "Medium";
        else
            return "Weak";
    }

    public static String generatePassword(int length) {
        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int ascii;
            int type = rand.nextInt(4);

            if (type == 0) ascii = rand.nextInt(26) + 65;      // A-Z
            else if (type == 1) ascii = rand.nextInt(26) + 97; // a-z
            else if (type == 2) ascii = rand.nextInt(10) + 48; // 0-9
            else ascii = rand.nextInt(15) + 33;                // special chars

            password.append((char) ascii);
        }
        return password.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a password to analyze: ");
        String input = sc.nextLine();
        System.out.println("Password Strength: " + analyzeStrength(input));

        System.out.print("Generate password of length: ");
        int len = sc.nextInt();
        String newPass = generatePassword(len);
        System.out.println("Generated Password: " + newPass);
        System.out.println("Strength: " + analyzeStrength(newPass));

        sc.close();
    }
}
