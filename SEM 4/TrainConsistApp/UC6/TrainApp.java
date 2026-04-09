import java.util.HashMap;
import java.util.Map;

public class TrainApp {
    
    private Map<String, Integer> bogieCapacityMap;
    
    // Constructor to initialize the train with empty capacity map
    public TrainApp() {
        this.bogieCapacityMap = new HashMap<>();
    }
    
    // Method to map a bogie to its capacity
    public void addBogieCapacity(String bogie, int capacity) {
        Integer previous = bogieCapacityMap.put(bogie, capacity);
        if (previous == null) {
            System.out.println("✓ Mapped bogie '" + bogie + "' with capacity: " + capacity);
        } else {
            System.out.println("✓ Updated bogie '" + bogie + "' capacity from " + previous + " to " + capacity);
        }
    }
    
    // Method to get the capacity of a specific bogie
    public Integer getCapacity(String bogie) {
        return bogieCapacityMap.get(bogie);
    }
    
    // Method to check if a bogie exists in the map
    public boolean containsBogie(String bogie) {
        return bogieCapacityMap.containsKey(bogie);
    }
    
    // Method to remove a bogie from the map
    public void removeBogie(String bogie) {
        if (bogieCapacityMap.remove(bogie) != null) {
            System.out.println("✓ Removed bogie: " + bogie);
        } else {
            System.out.println("✗ Bogie not found: " + bogie);
        }
    }
    
    // Method to calculate total capacity
    public int getTotalCapacity() {
        int total = 0;
        for (int capacity : bogieCapacityMap.values()) {
            total += capacity;
        }
        return total;
    }
    
    // Method to display all bogie-capacity mappings
    public void displayBogieCapacities() {
        System.out.println("\n--- Bogie Capacity Mapping ---");
        if (bogieCapacityMap.isEmpty()) {
            System.out.println("No bogies registered");
        } else {
            System.out.println("Bogie Name          | Capacity");
            System.out.println("------------------------------------");
            for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
                System.out.printf("%-20s| %d%n", entry.getKey(), entry.getValue());
            }
            System.out.println("------------------------------------");
            System.out.println("Total Bogies: " + bogieCapacityMap.size());
            System.out.println("Total Capacity: " + getTotalCapacity());
        }
        System.out.println("-------------------------------\n");
    }
    
    // Method to display capacity details using different iteration methods
    public void displayCapacityDetails() {
        System.out.println("\n--- Detailed Capacity Information ---");
        int count = 1;
        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println(count + ". " + entry.getKey() + 
                             " -> Capacity: " + entry.getValue() + " units");
            count++;
        }
        System.out.println("------------------------------------\n");
    }
    
    // Getter for the capacity map
    public Map<String, Integer> getBogieCapacityMap() {
        return bogieCapacityMap;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC6: Map Bogie to Capacity (HashMap)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Create bogie-capacity mappings for Passenger bogies
        System.out.println("Creating bogie-capacity mappings...");
        train.addBogieCapacity("Sleeper", 72);
        train.addBogieCapacity("AC Chair", 96);
        train.addBogieCapacity("First Class", 54);
        train.addBogieCapacity("Pantry Car", 0);  // No seating capacity
        
        // Display capacity mappings
        System.out.println("\nBogie capacity information:");
        train.displayBogieCapacities();
        
        // Display detailed capacity information using entrySet()
        System.out.println("Detailed capacity breakdown:");
        train.displayCapacityDetails();
        
        // Look up specific bogie capacity
        System.out.println("Looking up bogie capacities...");
        String bogieToLookup = "Sleeper";
        if (train.containsBogie(bogieToLookup)) {
            System.out.println("✓ Capacity of '" + bogieToLookup + "': " + 
                             train.getCapacity(bogieToLookup) + " seats");
        } else {
            System.out.println("✗ Bogie '" + bogieToLookup + "' not found");
        }
        
        // Update a bogie capacity
        System.out.println("\nUpdating AC Chair capacity...");
        train.addBogieCapacity("AC Chair", 100);  // Update capacity
        
        // Add more goods bogies
        System.out.println("\nAdding goods bogies...");
        train.addBogieCapacity("Cargo - Rectangular", 50000);
        train.addBogieCapacity("Cargo - Cylindrical", 35000);
        
        // Display final mappings
        System.out.println("\nFinal bogie capacity mappings:");
        train.displayBogieCapacities();
        
        System.out.println("✓ Bogie-capacity mapping successfully implemented!");
        System.out.println("Program continues...");
    }
}
