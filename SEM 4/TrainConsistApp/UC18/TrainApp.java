public class TrainApp {

    public static boolean linearSearch(String[] bogieIds, String searchId) {
        for (String bogieId : bogieIds) {
            if (bogieId.equals(searchId)) {
                return true;
            }
        }
        return false;
    }

    public static void printSearchResult(String searchId, boolean found) {
        if (found) {
            System.out.println("Bogie ID '" + searchId + "' was found in the train consist.");
        } else {
            System.out.println("Bogie ID '" + searchId + "' was NOT found in the train consist.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC18: Linear Search for Bogie ID (Array-Based Searching)\n");

        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        System.out.print("Available bogie IDs: ");
        for (String id : bogieIds) {
            System.out.print(id + " ");
        }
        System.out.println("\n");

        String searchKey1 = "BG309";
        boolean found1 = linearSearch(bogieIds, searchKey1);
        printSearchResult(searchKey1, found1);

        String searchKey2 = "BG999";
        boolean found2 = linearSearch(bogieIds, searchKey2);
        printSearchResult(searchKey2, found2);

        String searchKey3 = "BG101";
        boolean found3 = linearSearch(bogieIds, searchKey3);
        printSearchResult(searchKey3, found3);

        String searchKey4 = "BG550";
        boolean found4 = linearSearch(bogieIds, searchKey4);
        printSearchResult(searchKey4, found4);

        String[] singleBogie = {"BG101"};
        String searchKey5 = "BG101";
        boolean found5 = linearSearch(singleBogie, searchKey5);
        System.out.println("\nSingle-element array search:");
        printSearchResult(searchKey5, found5);

        System.out.println("\nProgram continues after linear search demonstration...");
    }
}
