package javabootcamp.WEEK2;

import java.util.Scanner;

public class CaseConverter {
    public static String toUpperCase(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch = (char)(ch - 32);
            }
            result += ch;
        }
        return result;
    }
    public static String toLowerCase(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char)(ch + 32);
            }
            result += ch;
        }
        return result;
    }
    public static String toTitleCase(String str) {
        String result = "";
        boolean newWord = true;
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == ' ') {
                result += ch;
                newWord = true; 
            } else {
                if (newWord) {
                    if (ch >= 'a' && ch <= 'z') {
                        ch = (char)(ch - 32);
                    }
                    newWord = false;
                } else {
                    if (ch >= 'A' && ch <= 'Z') {
                        ch = (char)(ch + 32);
                    }
                }
                result += ch;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.println("Uppercase: " + toUpperCase(text));
        System.out.println("Lowercase: " + toLowerCase(text));
        System.out.println("Title Case: " + toTitleCase(text));
        
        sc.close();
    }
}

