package javabootcamp.WEEK1.Assignementproblems;

import java.util.Scanner;

public class FrequencyWithUnique {

    public static int findLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return count;
    }

    public static char[] uniqueCharacters(String text) {
        int len = findLength(text);
        char[] temp = new char[len];
        int uniqueCount = 0;

        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < uniqueCount; j++) {
                if (temp[j] == ch) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                temp[uniqueCount] = ch;
                uniqueCount++;
            }
        }

        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    public static String[][] charFrequency(String text) {
        int[] freq = new int[256];
        int len = findLength(text);
        for (int i = 0; i < len; i++) {
            freq[text.charAt(i)]++;
        }

        char[] unique = uniqueCharacters(text);
        String[][] result = new String[unique.length][2];
        for (int i = 0; i < unique.length; i++) {
            result[i][0] = String.valueOf(unique[i]);
            result[i][1] = String.valueOf(freq[unique[i]]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        String[][] frequencies = charFrequency(text);
        System.out.println("Character Frequencies:");
        for (String[] row : frequencies) {
            System.out.println(row[0] + " -> " + row[1]);
        }
    }
}

