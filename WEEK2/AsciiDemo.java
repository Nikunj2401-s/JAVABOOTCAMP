package javabootcamp.WEEK2;

import java.util.Scanner;

public class AsciiDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ASCII Character Manipulation ---");
            System.out.println("1. Convert Character to ASCII");
            System.out.println("2. Convert ASCII to Character");
            System.out.println("3. Shift Character (Caesar Cipher)");
            System.out.println("4. Check Character Type");
            System.out.println("5. Print ASCII Table (A-Z, a-z, 0-9)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a character: ");
                    char ch = sc.next().charAt(0);
                    int ascii = (int) ch;
                    System.out.println("ASCII value of '" + ch + "' = " + ascii);
                    break;

                case 2:
                    System.out.print("Enter an ASCII value (0–127): ");
                    int val = sc.nextInt();
                    if (val >= 0 && val <= 127) {
                        char c = (char) val;
                        System.out.println("Character for ASCII " + val + " = '" + c + "'");
                    } else {
                        System.out.println("Invalid ASCII range!");
                    }
                    break;

                case 3:
                    System.out.print("Enter a character: ");
                    char ch2 = sc.next().charAt(0);
                    System.out.print("Enter shift value: ");
                    int shift = sc.nextInt();
                    char shifted = (char) (ch2 + shift);
                    System.out.println("After shifting '" + ch2 + "' by " + shift + " → '" + shifted + "'");
                    break;

                case 4:
                    System.out.print("Enter a character: ");
                    char ch3 = sc.next().charAt(0);
                    if (Character.isUpperCase(ch3)) {
                        System.out.println("'" + ch3 + "' is an UPPERCASE letter.");
                    } else if (Character.isLowerCase(ch3)) {
                        System.out.println("'" + ch3 + "' is a LOWERCASE letter.");
                    } else if (Character.isDigit(ch3)) {
                        System.out.println("'" + ch3 + "' is a DIGIT.");
                    } else {
                        System.out.println("'" + ch3 + "' is a SPECIAL character.");
                    }
                    break;

                case 5:
                    System.out.println("ASCII Table (A-Z, a-z, 0-9):");
                    for (char c1 = 'A'; c1 <= 'Z'; c1++)
                        System.out.println(c1 + " → " + (int)c1);
                    for (char c1 = 'a'; c1 <= 'z'; c1++)
                        System.out.println(c1 + " → " + (int)c1);
                    for (char c1 = '0'; c1 <= '9'; c1++)
                        System.out.println(c1 + " → " + (int)c1);
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

