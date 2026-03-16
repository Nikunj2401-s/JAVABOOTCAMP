package javabootcamp.WEEK1.practiceproblems;

import java.util.Scanner;

public class SimpleTextProcessor {
    public static int countWords(String text) {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) return 0;
        return trimmed.split("\\s+").length; // split by one or more spaces
    }

    // Method to reverse text
    public static String reverseText(String text) {
        String reversed = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed += text.charAt(i);
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Simple Text Processor ===");
        System.out.print("Enter your text: ");
        String input = sc.nextLine();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Show Original Text");
            System.out.println("2. Convert to UPPERCASE");
            System.out.println("3. Convert to lowercase");
            System.out.println("4. Trim Extra Spaces");
            System.out.println("5. Find a Word");
            System.out.println("6. Replace a Word");
            System.out.println("7. Show Word Count");
            System.out.println("8. Reverse Text");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Original Text: " + input);
                    break;

                case 2:
                    System.out.println("UPPERCASE: " + input.toUpperCase());
                    break;

                case 3:
                    System.out.println("lowercase: " + input.toLowerCase());
                    break;

                case 4:
                    System.out.println("Trimmed: [" + input.trim() + "]");
                    break;

                case 5:
                    System.out.print("Enter word to find: ");
                    String findWord = sc.nextLine();
                    if (input.toLowerCase().contains(findWord.toLowerCase())) {
                        System.out.println("'" + findWord + "' found in text.");
                    } else {
                        System.out.println("'" + findWord + "' not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter word to replace: ");
                    String oldWord = sc.nextLine();
                    System.out.print("Enter new word: ");
                    String newWord = sc.nextLine();
                    System.out.println("Updated Text: " + input.replace(oldWord, newWord));
                    break;

                case 7:
                    System.out.println("Word Count: " + countWords(input));
                    break;

                case 8:
                    System.out.println("Reversed Text: " + reverseText(input));
                    break;

                case 9:
                    System.out.println("Exiting Text Processor...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
}
