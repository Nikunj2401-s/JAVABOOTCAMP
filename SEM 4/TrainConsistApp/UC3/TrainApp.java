import java.util.HashSet;
import java.util.Set;

public class TrainApp {
    
    private Set<String> bogieIDs;
    
    // Constructor to initialize the train with empty bogie ID set
    public TrainApp() {
        this.bogieIDs = new HashSet<>();
    }
    
    // Method to add a bogie ID
    public void addBogieID(String id) {
        if (bogieIDs.add(id)) {
            System.out.println("✓ Added bogie ID: " + id);
        } else {
            System.out.println("✗ Duplicate bogie ID ignored: " + id);
        }
    }
    
    // Method to check if a bogie ID exists
    public boolean containsBogieID(String id) {
        return bogieIDs.contains(id);
    }
    
    // Method to display all unique bogie IDs
    public void displayBogieIDs() {
        System.out.println("\n--- Unique Bogie IDs ---");
        if (bogieIDs.isEmpty()) {
            System.out.println("No bogie IDs registered");
        } else {
            System.out.println("Bogie IDs in the train:");
            int count = 1;
            for (String id : bogieIDs) {
                System.out.println(count + ". " + id);
                count++;
            }
        }
        System.out.println("Total unique bogies: " + bogieIDs.size());
        System.out.println("------------------------\n");
    }
    
    // Method to get the size of bogie ID set
    public int getBogieIDCount() {
        return bogieIDs.size();
    }
    
    // Getter for bogie IDs set
    public Set<String> getBogieIDs() {
        return bogieIDs;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC3: Track Unique Bogie IDs (HashSet)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Add bogie IDs (including duplicates intentionally)
        System.out.println("Adding bogie IDs to the train...");
        train.addBogieID("BG101");
        train.addBogieID("BG102");
        train.addBogieID("BG103");
        train.addBogieID("BG101");  // Duplicate - should be ignored
        train.addBogieID("BG104");
        train.addBogieID("BG102");  // Duplicate - should be ignored
        train.addBogieID("BG105");
        train.addBogieID("BG101");  // Another duplicate - should be ignored
        
        // Display unique bogie IDs
        System.out.println("\nFinal state after automatic deduplication:");
        train.displayBogieIDs();
        
        // Check if specific bogie IDs exist
        System.out.println("Checking bogie ID existence...");
        System.out.println("BG101 exists? " + (train.containsBogieID("BG101") ? "Yes" : "No"));
        System.out.println("BG106 exists? " + (train.containsBogieID("BG106") ? "Yes" : "No"));
        
        System.out.println("\n✓ Uniqueness constraint enforced successfully!");
        System.out.println("Program continues...");
    }
}
