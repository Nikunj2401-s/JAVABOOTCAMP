import java.util.*;

public class CSVFormatter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter CSV-like data (rows separated by newline, end with empty line):");
        List<String> rows = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            rows.add(line);
        }

        if (rows.isEmpty()) {
            System.out.println("No data entered.");
            return;
        }

        int colCount = rows.get(0).split(",").length;
        int[] maxLen = new int[colCount];

        for (String row : rows) {
            String[] cols = row.split(",");
            for (int i = 0; i < cols.length; i++) {
                cols[i] = cols[i].trim();
                maxLen[i] = Math.max(maxLen[i], cols[i].length());
            }
        }

        System.out.println("\nFormatted Table:");
        for (String row : rows) {
            String[] cols = row.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cols.length; i++) {
                sb.append(pad(cols[i].trim(), maxLen[i] + 2));
            }
            System.out.println(sb.toString());
        }

        sc.close();
    }

    private static String pad(String s, int length) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
