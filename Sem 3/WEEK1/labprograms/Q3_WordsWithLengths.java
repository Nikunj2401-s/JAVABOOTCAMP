package javabootcamp.WEEK1.labprograms;

import java.util.Scanner;

public class Q3_WordsWithLengths {

    // Custom findLength method
    public static int findLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string reached
        }
        return count;
    }

    // Custom split (basic, by spaces)
    public static String[] splitText(String s) {
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') count++;
        }

        String[] words = new String[count];
        StringBuilder word = new StringBuilder();
        int index = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words[index++] = word.toString();
                word.setLength(0);
            } else {
                word.append(s.charAt(i));
            }
        }
        words[index] = word.toString();
        return words;
    }

    // Build table with words and lengths
    public static String[][] wordWithLength(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(findLength(words[i]));
        }
        return result;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] words = splitText(input);
        String[][] table = wordWithLength(words);

        System.out.println("\nWord\tLength");
        for (String[] row : table) {
            System.out.println(row[0] + "\t" + row[1]);
        }

        sc.close();
    }
}

