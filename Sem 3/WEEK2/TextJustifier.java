package javabootcamp.WEEK2;

import java.util.*;

public class TextJustifier {

    public static List<String> justifyText(String[] words, int width) {
        List<String> result = new ArrayList<>();
        List<String> line = new ArrayList<>();
        int lineLength = 0;

        for (String word : words) {
            if (lineLength + word.length() + line.size() > width) {
                result.add(justifyLine(line, lineLength, width));
                line.clear();
                lineLength = 0;
            }
            line.add(word);
            lineLength += word.length();
        }

        StringBuilder lastLine = new StringBuilder();
        for (int i = 0; i < line.size(); i++) {
            if (i > 0) lastLine.append(" ");
            lastLine.append(line.get(i));
        }
        while (lastLine.length() < width) lastLine.append(" ");
        result.add(lastLine.toString());

        return result;
    }

    private static String justifyLine(List<String> line, int lineLength, int width) {
        if (line.size() == 1) {
            StringBuilder sb = new StringBuilder(line.get(0));
            while (sb.length() < width) sb.append(" ");
            return sb.toString();
        }
        int totalSpaces = width - lineLength;
        int spaceSlots = line.size() - 1;
        int evenSpace = totalSpaces / spaceSlots;
        int extraSpace = totalSpaces % spaceSlots;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.size(); i++) {
            sb.append(line.get(i));
            if (i < spaceSlots) {
                for (int s = 0; s < evenSpace; s++) sb.append(" ");
                if (extraSpace > 0) {
                    sb.append(" ");
                    extraSpace--;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.print("Enter line width: ");
        int width = sc.nextInt();

        String[] words = text.split("\\s+");
        List<String> justified = justifyText(words, width);

        System.out.println("\nJustified Text:");
        for (String line : justified) {
            System.out.println(line);
        }

        sc.close();
    }
}

