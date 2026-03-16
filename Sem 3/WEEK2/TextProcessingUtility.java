package javabootcamp.WEEK2;

import java.util.Scanner;

public class TextProcessingUtility {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input text
        System.out.print("Enter a text: ");
        String text = sc.nextLine();

        int choice;
        do {
            System.out.println("\n--- Text Processing Utility ---");
            System.out.println("1. Show Length");
            System.out.println("2. Convert to Uppercase");
            System.out.println("3. Convert to Lowercase");
            System.out.println("4. Trim Spaces");
            System.out.println("5. Extract Substring");
            System.out.println("6. Find Index of Word");
            System.out.println("7. Replace Word");
            System.out.println("8. Split into Words");
            System.out.println("9. Reverse String");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Length: " + text.length());
                    break;

                case 2:
                    System.out.println("Uppercase: " + text.toUpperCase());
                    break;

                case 3:
                    System.out.println("Lowercase: " + text.toLowerCase());
                    break;

                case 4:
                    System.out.println("Trimmed: \"" + text.trim() + "\"");
                    break;

                case 5:
                    System.out.print("Enter start index: ");
                    int start = sc.nextInt();
                    System.out.print("Enter end index: ");
                    int end = sc.nextInt();
                    sc.nextLine(); // consume newline
                    if (start >= 0 && end <= text.length() && start < end) {
                        System.out.println("Substring: " + text.substring(start, end));
                    } else {
                        System.out.println("Invalid indices!");
                    }
                    break;

                case 6:
                    System.out.print("Enter word to find: ");
                    String word = sc.nextLine();
                    int index = text.indexOf(word);
                    if (index != -1) {
                        System.out.println("First occurrence at index: " + index);
                    } else {
                        System.out.println("Word not found!");
                    }
                    break;

                case 7:
                    System.out.print("Enter word to replace: ");
                    String oldWord = sc.nextLine();
                    System.out.print("Enter new word: ");
                    String newWord = sc.nextLine();
                    String replaced = text.replace(oldWord, newWord);
                    System.out.println("After Replace: " + replaced);
                    break;

                case 8:
                    String[] words = text.trim().split("\\s+");
                    System.out.println("Words in the text:");
                    for (String w : words) {
                        System.out.println("- " + w);
                    }
                    break;

                case 9:
                    String reversed = new StringBuilder(text).reverse().toString();
                    System.out.println("Reversed: " + reversed);
                    break;

                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}

