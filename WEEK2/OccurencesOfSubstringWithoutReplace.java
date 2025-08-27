package javabootcamp.WEEK2;

public class OccurencesOfSubstringWithoutReplace {
    public static String findAndReplace(String text, String oldSub, String newSub) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            if (i + oldSub.length() <= text.length() &&
                text.substring(i, i + oldSub.length()).equals(oldSub)) {
                result.append(newSub);
                i += oldSub.length();  
            } else {
                result.append(text.charAt(i)); 
                i++;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "the cat sat on the mat with another cat";
        String oldSub = "cat";
        String newSub = "dog";

        System.out.println("Original Text: " + text);
        System.out.println("Modified Text: " + findAndReplace(text, oldSub, newSub));
    }
}

