package javabootcamp.WEEK1.labprograms;

import java.util.*;

public class Q2_SplitCompare {

    // Custom split (handles multiple spaces)
    public static String[] splitText(String s) {
        List<String> wordsList = new ArrayList<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (word.length() > 0) {   // avoid empty strings
                    wordsList.add(word.toString());
                    word.setLength(0);
                }
            } else {
                word.append(c);
            }
        }

        if (word.length() > 0) {  // add last word
            wordsList.add(word.toString());
        }

        return wordsList.toArray(new String[0]);
    }

    // Compare two arrays
    public static boolean compareArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] customSplit = splitText(input);
        String[] builtInSplit = input.trim().split("\\s+"); // built-in handles multiple spaces

        System.out.println("Custom Split: " + Arrays.toString(customSplit));
        System.out.println("Built-in Split: " + Arrays.toString(builtInSplit));
        System.out.println("Are both equal? " + compareArrays(customSplit, builtInSplit));

        sc.close();
    }
}

