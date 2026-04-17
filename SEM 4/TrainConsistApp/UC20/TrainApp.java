import java.util.Arrays;

public class TrainApp {

    public static boolean validateAndSearch(String[] bogieIds, String searchId) {
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("Cannot search bogie IDs: no bogies are available in the train consist.");
        }

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = bogieIds[mid].compareTo(searchId);

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
            System.out.println("Bogie ID '" + searchId + "' was found after validation.");
        } else {
            System.out.println("Bogie ID '" + searchId + "' was NOT found after validation.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC20: Exception Handling During Search Operations\n");

        String[] emptyBogieIds = {};
        try {
            System.out.println("Searching an empty bogie list...");
            validateAndSearch(emptyBogieIds, "BG101");
        } catch (IllegalStateException ex) {
            System.out.println("✗ " + ex.getMessage());
        }

        System.out.println();
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412"};
        Arrays.sort(bogieIds);
        System.out.println("Sorted bogie IDs available: " + Arrays.toString(bogieIds));

        try {
            boolean found = validateAndSearch(bogieIds, "BG205");
            printSearchResult("BG205", found);
        } catch (IllegalStateException ex) {
            System.out.println("✗ " + ex.getMessage());
        }

        try {
            boolean found = validateAndSearch(bogieIds, "BG999");
            printSearchResult("BG999", found);
        } catch (IllegalStateException ex) {
            System.out.println("✗ " + ex.getMessage());
        }

        try {
            boolean found = validateAndSearch(new String[] {"BG101"}, "BG101");
            System.out.println();
            printSearchResult("BG101", found);
        } catch (IllegalStateException ex) {
            System.out.println("✗ " + ex.getMessage());
        }

        System.out.println("\nProgram continues after defensive search validation...");
    }
}
