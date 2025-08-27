package javabootcamp.WEEK2;

public class StringMethodsDemo {
    public static void main(String[] args) {
        String text = "  Hello Java World!  ";

        // Original String
        System.out.println("Original Text: \"" + text + "\"");

        // 1. Length of string
        System.out.println("Length: " + text.length());

        // 2. Trim extra spaces
        String trimmed = text.trim();
        System.out.println("Trimmed Text: \"" + trimmed + "\"");

        // 3. Convert to uppercase and lowercase
        System.out.println("Uppercase: " + trimmed.toUpperCase());
        System.out.println("Lowercase: " + trimmed.toLowerCase());

        // 4. Get a character at index
        System.out.println("Character at index 6: " + trimmed.charAt(6));

        // 5. Substring
        System.out.println("Substring (6 to 10): " + trimmed.substring(6, 10));

        // 6. Check prefix and suffix
        System.out.println("Starts with 'Hello': " + trimmed.startsWith("Hello"));
        System.out.println("Ends with 'World!': " + trimmed.endsWith("World!"));

        // 7. Search index of characters/words
        System.out.println("Index of 'Java': " + trimmed.indexOf("Java"));
        System.out.println("Last index of 'l': " + trimmed.lastIndexOf("l"));

        // 8. Replace word
        String replaced = trimmed.replace("Java", "Programming");
        System.out.println("After Replace: " + replaced);

        // 9. String comparison
        String word1 = "Hello";
        String word2 = "hello";
        System.out.println("Equals: " + word1.equals(word2));
        System.out.println("Equals Ignore Case: " + word1.equalsIgnoreCase(word2));

        // 10. Split into words
        String[] words = trimmed.split(" ");
        System.out.println("Words in the text:");
        for (String w : words) {
            System.out.println("- " + w);
        }
    }
}

