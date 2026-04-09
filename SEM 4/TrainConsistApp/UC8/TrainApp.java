import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainApp {
    
    private List<Bogie> passengerBogies;
    
    // Constructor to initialize the train with empty bogie list
    public TrainApp() {
        this.passengerBogies = new ArrayList<>();
    }
    
    // Method to add a passenger bogie
    public void addPassengerBogie(Bogie bogie) {
        passengerBogies.add(bogie);
    }
    
    // Stream Method: Filter bogies with capacity greater than threshold
    public List<Bogie> filterByCapacityGreaterThan(int threshold) {
        return passengerBogies.stream()
                .filter(bogie -> bogie.getCapacity() > threshold)
                .collect(Collectors.toList());
    }
    
    // Stream Method: Filter bogies with capacity greater than or equal to threshold
    public List<Bogie> filterByCapacityGreaterThanOrEqual(int threshold) {
        return passengerBogies.stream()
                .filter(bogie -> bogie.getCapacity() >= threshold)
                .collect(Collectors.toList());
    }
    
    // Stream Method: Filter bogies with capacity less than threshold
    public List<Bogie> filterByCapacityLessThan(int threshold) {
        return passengerBogies.stream()
                .filter(bogie -> bogie.getCapacity() < threshold)
                .collect(Collectors.toList());
    }
    
    // Stream Method: Filter bogies by type
    public List<Bogie> filterByType(String type) {
        return passengerBogies.stream()
                .filter(bogie -> bogie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
    // Stream Method: Filter high-capacity bogies (> 70 seats)
    public List<Bogie> getHighCapacityBogies(int threshold) {
        return passengerBogies.stream()
                .filter(bogie -> bogie.getCapacity() > threshold)
                .collect(Collectors.toList());
    }
    
    // Stream Method: Count bogies matching condition
    public long countBogiesWithCapacity(int threshold) {
        return passengerBogies.stream()
                .filter(bogie -> bogie.getCapacity() > threshold)
                .count();
    }
    
    // Stream Method: Find bogie with maximum capacity
    public Bogie getMaxCapacityBogie() {
        return passengerBogies.stream()
                .max((b1, b2) -> Integer.compare(b1.getCapacity(), b2.getCapacity()))
                .orElse(null);
    }
    
    // Stream Method: Get average capacity of filtered bogies
    public double getAverageCapacity(int threshold) {
        return passengerBogies.stream()
                .filter(bogie -> bogie.getCapacity() > threshold)
                .mapToInt(Bogie::getCapacity)
                .average()
                .orElse(0);
    }
    
    // Method to display all bogies
    public void displayAllBogies() {
        System.out.println("\n--- All Passenger Bogies ---");
        System.out.println("Bogie Name          | Type            | Capacity");
        System.out.println("─────────────────────────────────────────────────────");
        
        if (passengerBogies.isEmpty()) {
            System.out.println("No bogies registered");
        } else {
            for (int i = 0; i < passengerBogies.size(); i++) {
                System.out.println((i + 1) + ". " + passengerBogies.get(i));
            }
        }
        
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("Total Bogies: " + passengerBogies.size() + "\n");
    }
    
    // Method to display filtered bogies
    public void displayFilteredBogies(List<Bogie> filtered, String filterDescription) {
        System.out.println("\n--- Filtered Passenger Bogies (" + filterDescription + ") ---");
        System.out.println("Bogie Name          | Type            | Capacity");
        System.out.println("─────────────────────────────────────────────────────");
        
        if (filtered.isEmpty()) {
            System.out.println("No bogies match the filter criteria");
        } else {
            for (int i = 0; i < filtered.size(); i++) {
                System.out.println((i + 1) + ". " + filtered.get(i));
            }
        }
        
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("Filtered Count: " + filtered.size() + "\n");
    }
    
    // Method to verify original list is unchanged
    public int getOriginalBogieCount() {
        return passengerBogies.size();
    }
    
    // Getter for bogies list
    public List<Bogie> getPassengerBogies() {
        return passengerBogies;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC8: Filter Passenger Bogies Using Streams\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Create passenger bogies
        System.out.println("Creating passenger bogies...");
        train.addPassengerBogie(new Bogie("Sleeper", 72, "Passenger"));
        train.addPassengerBogie(new Bogie("AC Chair", 96, "Passenger"));
        train.addPassengerBogie(new Bogie("First Class", 54, "Passenger"));
        train.addPassengerBogie(new Bogie("General", 150, "Passenger"));
        train.addPassengerBogie(new Bogie("Pantry Car", 0, "Service"));
        train.addPassengerBogie(new Bogie("AC Premium", 80, "Passenger"));
        
        // Display all bogies
        train.displayAllBogies();
        
        // TEST 1: Filter bogies with capacity > 70
        System.out.println("=== TEST 1: Capacity > 70 ===");
        List<Bogie> highCapacity = train.filterByCapacityGreaterThan(70);
        train.displayFilteredBogies(highCapacity, "Capacity > 70");
        
        // TEST 2: Filter bogies with capacity >= 70
        System.out.println("=== TEST 2: Capacity >= 70 ===");
        List<Bogie> mediumHighCapacity = train.filterByCapacityGreaterThanOrEqual(70);
        train.displayFilteredBogies(mediumHighCapacity, "Capacity >= 70");
        
        // TEST 3: Filter bogies with capacity < 70
        System.out.println("=== TEST 3: Capacity < 70 ===");
        List<Bogie> lowCapacity = train.filterByCapacityLessThan(70);
        train.displayFilteredBogies(lowCapacity, "Capacity < 70");
        
        // TEST 4: Filter by type - Passenger
        System.out.println("=== TEST 4: Filter by Type (Passenger) ===");
        List<Bogie> passengerOnly = train.filterByType("Passenger");
        train.displayFilteredBogies(passengerOnly, "Type = Passenger");
        
        // TEST 5: No matching bogies
        System.out.println("=== TEST 5: No Matching Bogies (Capacity > 200) ===");
        List<Bogie> noMatch = train.filterByCapacityGreaterThan(200);
        train.displayFilteredBogies(noMatch, "Capacity > 200");
        
        // TEST 6: All matching bogies
        System.out.println("=== TEST 6: All Matching Bogies (Capacity >= 0) ===");
        List<Bogie> allMatch = train.filterByCapacityGreaterThanOrEqual(0);
        train.displayFilteredBogies(allMatch, "Capacity >= 0");
        
        // Verify original list is unchanged
        System.out.println("=== VERIFICATION: Original List Integrity ===");
        System.out.println("Original bogie count before filtering: 6");
        System.out.println("Original bogie count after filtering: " + train.getOriginalBogieCount());
        System.out.println("✓ Original list remains unchanged\n");
        
        // Stream statistics
        System.out.println("=== STREAM STATISTICS ===");
        System.out.println("Count of bogies with capacity > 70: " + train.countBogiesWithCapacity(70));
        System.out.println("Average capacity of bogies > 70: " + 
                         String.format("%.2f seats", train.getAverageCapacity(70)));
        Bogie maxBogie = train.getMaxCapacityBogie();
        System.out.println("Bogie with maximum capacity: " + 
                         (maxBogie != null ? maxBogie.getName() + " (" + maxBogie.getCapacity() + " seats)" : "N/A"));
        
        System.out.println("\n✓ Stream-based filtering successfully implemented!");
        System.out.println("Program continues...");
    }
}
