import java.util.Arrays;

public class TrainApp {

    public static boolean binarySearch(String[] sortedBogieIds, String searchId) {
        int low = 0;
        int high = sortedBogieIds.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedBogieIds[mid].compareTo(searchId);

            if (comparison == 0) {
                return true;
            }
            if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void printSearchResult(String searchId, boolean found) {
        if (found) {
            System.out.println("Bogie ID '" + searchId + "' was found using binary search.");
        } else {
            System.out.println("Bogie ID '" + searchId + "' was NOT found using binary search.");
        }
    }

    public static void printBogieIds(String title, String[] bogieIds) {
        System.out.println(title + Arrays.toString(bogieIds));
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC19: Binary Search for Bogie ID (Optimized Searching)\n");

        String[] unsortedBogieIds = {"BG309", "BG101", "BG550", "BG205", "BG412"};
        printBogieIds("Unsorted bogie IDs: ", unsortedBogieIds);

        Arrays.sort(unsortedBogieIds);
        printBogieIds("Sorted bogie IDs  : ", unsortedBogieIds);

        String[] testKeys = {"BG309", "BG999", "BG101", "BG550", "BG205"};
        for (String key : testKeys) {
            boolean found = binarySearch(unsortedBogieIds, key);
            printSearchResult(key, found);
        }

        String[] singleBogie = {"BG101"};
        printBogieIds("\nSingle-element array: ", singleBogie);
        printSearchResult("BG101", binarySearch(singleBogie, "BG101"));

        String[] emptyBogieList = {};
        printBogieIds("Empty bogie list: ", emptyBogieList);
        printSearchResult("BG101", binarySearch(emptyBogieList, "BG101"));

        System.out.println("\nProgram continues after optimized search demonstration...");
    }
}
