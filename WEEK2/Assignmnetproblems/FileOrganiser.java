import java.io.*;
import java.util.*;

public class FileOrganiser {

    public static void organizeFiles(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("Invalid directory.");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("No files found.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String name = file.getName();
                int dot = name.lastIndexOf(".");
                String ext = (dot == -1) ? "unknown" : name.substring(dot + 1).toLowerCase();

                String category = getCategory(ext);
                File newDir = new File(folderPath + "/" + category);
                if (!newDir.exists()) newDir.mkdir();

                String newName = analyzeAndRename(file, ext);
                File newFile = new File(newDir, newName);

                if (file.renameTo(newFile)) {
                    System.out.println("Moved: " + file.getName() + " -> " + newFile.getPath());
                } else {
                    System.out.println("Failed: " + file.getName());
                }
            }
        }
    }

    private static String getCategory(String ext) {
        switch (ext) {
            case "txt": return "TextFiles";
            case "jpg":
            case "png": return "Images";
            case "pdf": return "Documents";
            default: return "Others";
        }
    }

    private static String analyzeAndRename(File file, String ext) {
        String baseName = file.getName();
        if (ext.equals("txt")) {
            int wordCount = countWords(file);
            return "words_" + wordCount + "_" + baseName;
        }
        return "file_" + baseName;
    }

    private static int countWords(File file) {
        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                sc.next();
                count++;
            }
        } catch (Exception e) {
            return 0;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter folder path to organize: ");
        String path = sc.nextLine();

        organizeFiles(path);
        sc.close();
    }
}
