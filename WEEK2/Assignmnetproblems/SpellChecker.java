import java.util.*;

public class SpellChecker {
    
    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                                 Math.min(dp[i][j - 1],
                                          dp[i - 1][j - 1]));
            }
        }
        return dp[m][n];
    }

    public static List<String> getSuggestions(String word, List<String> dictionary) {
        List<String> suggestions = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for (String dictWord : dictionary) {
            int distance = editDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (distance < minDistance) {
                minDistance = distance;
                suggestions.clear();
                suggestions.add(dictWord);
            } else if (distance == minDistance) {
                suggestions.add(dictWord);
            }
        }
        return suggestions;
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(
            "apple", "orange", "banana", "grape", "pineapple", "mango", "peach"
        );

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = sc.nextLine();

        if (dictionary.contains(word.toLowerCase())) {
            System.out.println("✅ Word is spelled correctly.");
        } else {
            System.out.println("❌ Word is misspelled.");
            List<String> suggestions = getSuggestions(word, dictionary);
            System.out.println("Did you mean? " + suggestions);
        }

        sc.close();
    }
}
