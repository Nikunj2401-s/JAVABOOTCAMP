package javabootcamp.WEEK1.Assignementproblems;

import java.util.Scanner;

public class CharacterFrequency {

    public static Object[][] findCharFrequency(String text) {
        int[] freq = new int[256];
        int len = 0;
        try {
            while (true) {
                text.charAt(len);
                len++;
            }
        } catch (IndexOutOfBoundsException e) {
        }

        for (int i = 0; i < len; i++) {
            freq[text.charAt(i)]++;
        }

        Object[][] result = new Object[len][2];
        int index = 0;
        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            if (freq[ch] != 0) {
                result[index][0] = ch;
                result[index][1] = freq[ch];
                freq[ch] = 0;
                index++;
            }
        }

        Object[][] finalResult = new Object[index][2];
        for (int i = 0; i < index; i++) {
            finalResult[i][0] = result[i][0];
            finalResult[i][1] = result[i][1];
        }

        return finalResult;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();
        Object[][] frequencies = findCharFrequency(text);

        System.out.println("Character Frequencies:");
        for (Object[] row : frequencies) {
            System.out.println(row[0] + " -> " + row[1]);
        }
    }
}
