package javabootcamp.WEEK1.practiceproblems;

public class StringDemo {
    public static void main(String[] args) {
        // Different ways to create strings

        // 1. Using string literal
        String str1 = "Hello";

        // 2. Using new keyword
        String str2 = new String("World");

        // 3. From character array
        char[] charArray = { 'J', 'a', 'v', 'a' };
        String str3 = new String(charArray);

        // 4. From byte array
        byte[] byteArray = { 65, 66, 67, 68 };  // ASCII values of A, B, C, D
        String str4 = new String(byteArray);

        // Display created strings
        System.out.println("String 1 (literal): " + str1);
        System.out.println("String 2 (new keyword): " + str2);
        System.out.println("String 3 (char array): " + str3);
        System.out.println("String 4 (byte array): " + str4);

        // Basic Manipulation

        // Concatenation
        String combined = str1 + " " + str2;
        System.out.println("Concatenated String: " + combined);

        // Length
        System.out.println("Length of str3: " + str3.length());

        // Character at index
        System.out.println("Character at index 2 of str1: " + str1.charAt(2));

        // Substring
        System.out.println("Substring of str2 (1 to 3): " + str2.substring(1, 4));

        // Changing case
        System.out.println("Uppercase str1: " + str1.toUpperCase());
        System.out.println("Lowercase str2: " + str2.toLowerCase());

        // Equality check
        String anotherStr = "Hello";
        System.out.println("str1 equals anotherStr? " + str1.equals(anotherStr));
        System.out.println("str1 == anotherStr? " + (str1 == anotherStr)); // reference check

        // Replace characters
        System.out.println("Replace 'a' with 'o' in str3: " + str3.replace('a', 'o'));

        // Trim spaces
        String spaced = "   Java Programming   ";
        System.out.println("Before trim: [" + spaced + "]");
        System.out.println("After trim: [" + spaced.trim() + "]");
    }
    
}
