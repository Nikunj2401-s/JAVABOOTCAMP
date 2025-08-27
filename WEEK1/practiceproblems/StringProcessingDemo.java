package javabootcamp.WEEK1.practiceproblems;

import java.util.Scanner;

public class StringProcessingDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take user input
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        // Display original string
        System.out.println("\nOriginal String: " + input);

        // 1. Length of string
        System.out.println("Length: " + input.length());

        // 2. Convert to uppercase
        System.out.println("Uppercase: " + input.toUpperCase());

        // 3. Convert to lowercase
        System.out.println("Lowercase: " + input.toLowerCase());

        // 4. First and last character
        if (!input.isEmpty()) {
            System.out.println("First character: " + input.charAt(0));
            System.out.println("Last character: " + input.charAt(input.length() - 1));
        }

        // 5. Check if string contains "java"
        System.out.println("Contains 'java'? " + input.toLowerCase().contains("java"));

        // 6. Replace spaces with underscores
        System.out.println("Replace spaces: " + input.replace(" ", "_"));

        // 7. Trim leading and trailing spaces
        System.out.println("Trimmed: [" + input.trim() + "]");

        // 8. Substring example (if input is long enough)
        if (input.length() >= 4) {
            System.out.println("Substring (1 to 4): " + input.substring(1, 4));
        } else {
            System.out.println("Substring (1 to 4): Not enough characters");
        }

        // 9. Reverse string manually
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        System.out.println("Reversed: " + reversed);

        sc.close();
    }
    
}
