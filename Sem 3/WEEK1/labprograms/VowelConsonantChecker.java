package javabootcamp.WEEK1.labprograms;

import java.util.Scanner;

public class VowelConsonantChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take input
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        // Loop through each character
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Check if it's a letter
            if (Character.isLetter(ch)) {
                // Convert to lowercase for easy comparison
                char lowerCh = Character.toLowerCase(ch);

                // Check vowel or consonant
                if (lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u') {
                    System.out.println(ch + " → Vowel");
                } else {
                    System.out.println(ch + " → Consonant");
                }
            } else {
                System.out.println(ch + " → Not a Letter");
            }
        }

        sc.close();
    }
}

