import java.util.*;

public class TextCompressor {

    public static String compress(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        Map<Character, String> encoding = new HashMap<>();
        int code = 1;
        for (Map.Entry<Character, Integer> e : list) {
            encoding.put(e.getKey(), Integer.toString(code++));
        }

        StringBuilder compressed = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressed.append(encoding.get(c)).append(" ");
        }

        return compressed.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String text = sc.nextLine();

        String compressed = compress(text);
        System.out.println("Original: " + text);
        System.out.println("Compressed: " + compressed);

        sc.close();
    }
}
