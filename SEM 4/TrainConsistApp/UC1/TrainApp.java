import java.util.ArrayList;
import java.util.List;

public class TrainApp {
    
    private List<Bogie> bogies;
    
    // Constructor to initialize the train with empty bogie list
    public TrainApp() {
        this.bogies = new ArrayList<>();
    }
    
    // Method to display consist summary
    public void displayConsistSummary() {
        System.out.println("\n--- Train Consist Summary ---");
        System.out.println("Initial bogie count: " + bogies.size());
        System.out.println("Bogies: " + (bogies.isEmpty() ? "None" : bogies.toString()));
        System.out.println("---------------------------\n");
    }
    
    // Getter for bogies list
    public List<Bogie> getBogies() {
        return bogies;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        // Print welcome message
        System.out.println("=== Train Consist Management App ===");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Display initial consist summary
        train.displayConsistSummary();
        
        System.out.println("Program continues...");
    }
}
