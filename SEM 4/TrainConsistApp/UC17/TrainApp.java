import java.util.Arrays;

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC17: Sort Bogie Names Using Arrays.sort()\n");

        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        System.out.println("Original bogie names: " + Arrays.toString(bogieNames));

        Arrays.sort(bogieNames);
        System.out.println("Sorted bogie names  : " + Arrays.toString(bogieNames));

        System.out.println("\nAdditional sorting examples:");

        String[] unsortedBogieNames = {"Luxury", "General", "Sleeper", "AC Chair"};
        System.out.println("Unsorted input: " + Arrays.toString(unsortedBogieNames));
        Arrays.sort(unsortedBogieNames);
        System.out.println("Sorted output : " + Arrays.toString(unsortedBogieNames));

        String[] alreadySorted = {"AC Chair", "First Class", "General"};
        System.out.println("Already sorted: " + Arrays.toString(alreadySorted));
        Arrays.sort(alreadySorted);
        System.out.println("After sort    : " + Arrays.toString(alreadySorted));

        String[] duplicateNames = {"Sleeper", "AC Chair", "Sleeper", "General"};
        System.out.println("Duplicates in: " + Arrays.toString(duplicateNames));
        Arrays.sort(duplicateNames);
        System.out.println("Sorted result: " + Arrays.toString(duplicateNames));

        String[] singleName = {"Sleeper"};
        System.out.println("Single element: " + Arrays.toString(singleName));
        Arrays.sort(singleName);
        System.out.println("After sort    : " + Arrays.toString(singleName));

        System.out.println("\nProgram continues after built-in sorting demonstration...");
    }
}
