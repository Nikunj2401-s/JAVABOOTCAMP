package javabootcamp.WEEK1.labprograms;

import java.util.*;

public class Q4_ShortestLongest {

    // Custom findLength
    public static int findLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {}
        return count;
    }

    // Split text (ignores multiple spaces)
    public static String[] splitText(String s) {
        List<String> wordsList = new ArrayList<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (word.length() > 0) {
                    wordsList.add(word.toString());
                    word.setLength(0);
                }
            } else {
                word.append(c);
            }
        }
        if (word.length() > 0) {
            wordsList.add(word.toString());
        }

        return wordsList.toArray(new String[0]);
    }

    // Word with lengths
    public static String[][] wordWithLength(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(findLength(words[i]));
        }
        return result;
    }

    // Find shortest and longest words
    public static String[] findShortestLongest(String[][] table) {
        String shortest = table[0][0];
        String longest = table[0][0];
        int minLen = Integer.parseInt(table[0][1]);
        int maxLen = minLen;

        for (String[] row : table) {
            int len = Integer.parseInt(row[1]);
            if (len < minLen) {
                minLen = len;
                shortest = row[0];
            }
            if (len > maxLen) {
                maxLen = len;
                longest = row[0];
            }
        }
        return new String[]{shortest, longest};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] words = splitText(input);
        String[][] table = wordWithLength(words);
        String[] result = findShortestLongest(table);

        System.out.println("Shortest word: " + result[0]);
        System.out.println("Longest word: " + result[1]);

        sc.close();
    }
}

