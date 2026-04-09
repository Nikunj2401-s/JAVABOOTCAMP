import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrainApp {
    
    private List<Bogie> passengerBogies;
    
    // Constructor to initialize the train with empty bogie list
    public TrainApp() {
        this.passengerBogies = new ArrayList<>();
    }
    
    // Method to add a passenger bogie
    public void addPassengerBogie(Bogie bogie) {
        passengerBogies.add(bogie);
        System.out.println("✓ Added bogie: " + bogie.getName() + " (Capacity: " + bogie.getCapacity() + ")");
    }
    
    // Method to sort bogies by capacity in ascending order
    public void sortByCapacityAscending() {
        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity));
        System.out.println("✓ Sorted bogies by capacity (ascending)");
    }
    
    // Method to sort bogies by capacity in descending order
    public void sortByCapacityDescending() {
        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());
        System.out.println("✓ Sorted bogies by capacity (descending)");
    }
    
    // Method to sort bogies by name alphabetically
    public void sortByName() {
        passengerBogies.sort(Comparator.comparing(Bogie::getName));
        System.out.println("✓ Sorted bogies by name (A-Z)");
    }
    
    // Method to display all bogies
    public void displayBogies() {
        System.out.println("\n--- Passenger Bogies Inventory ---");
        System.out.println("Bogie Name          | Type            | Capacity");
        System.out.println("─────────────────────────────────────────────────────");
        
        if (passengerBogies.isEmpty()) {
            System.out.println("No bogies registered");
        } else {
            for (Bogie bogie : passengerBogies) {
                System.out.println(bogie);
            }
        }
        
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("Total Bogies: " + passengerBogies.size());
        System.out.println("Total Capacity: " + getTotalCapacity() + " seats");
        System.out.println("──────────────────────────────────────────────────────\n");
    }
    
    // Method to display bogies with rankings
    public void displayRankedBogies() {
        System.out.println("\n--- Bogie Ranking by Capacity ---");
        System.out.println("Rank | Bogie Name          | Type            | Capacity");
        System.out.println("──────────────────────────────────────────────────────────");
        
        for (int i = 0; i < passengerBogies.size(); i++) {
            Bogie bogie = passengerBogies.get(i);
            System.out.printf("%3d  | %s%n", (i + 1), bogie);
        }
        
        System.out.println("──────────────────────────────────────────────────────────\n");
    }
    
    // Method to get total capacity
    public int getTotalCapacity() {
        return passengerBogies.stream().mapToInt(Bogie::getCapacity).sum();
    }
    
    // Method to find highest capacity bogie
    public Bogie getHighestCapacityBogie() {
        return passengerBogies.stream()
                .max(Comparator.comparingInt(Bogie::getCapacity))
                .orElse(null);
    }
    
    // Method to find lowest capacity bogie
    public Bogie getLowestCapacityBogie() {
        return passengerBogies.stream()
                .min(Comparator.comparingInt(Bogie::getCapacity))
                .orElse(null);
    }
    
    // Method to calculate average capacity
    public double getAverageCapacity() {
        if (passengerBogies.isEmpty()) return 0;
        return passengerBogies.stream()
                .mapToInt(Bogie::getCapacity)
                .average()
                .orElse(0);
    }
    
    // Getter for bogies list
    public List<Bogie> getPassengerBogies() {
        return passengerBogies;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC7: Sort Bogies by Capacity (Comparator)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Create passenger bogies
        System.out.println("Creating passenger bogies...");
        train.addPassengerBogie(new Bogie("Sleeper", 72, "Passenger"));
        train.addPassengerBogie(new Bogie("AC Chair", 96, "Passenger"));
        train.addPassengerBogie(new Bogie("First Class", 54, "Passenger"));
        train.addPassengerBogie(new Bogie("General", 150, "Passenger"));
        train.addPassengerBogie(new Bogie("Pantry Car", 0, "Service"));
        
        // Display bogies before sorting
        System.out.println("\nBogies before sorting (insertion order):");
        train.displayBogies();
        
        // Sort by capacity ascending
        System.out.println("Sorting by capacity (ascending)...");
        train.sortByCapacityAscending();
        train.displayRankedBogies();
        
        // Sort by capacity descending
        System.out.println("Sorting by capacity (descending)...");
        train.sortByCapacityDescending();
        train.displayRankedBogies();
        
        // Sort by name
        System.out.println("Sorting by name (alphabetically)...");
        train.sortByName();
        train.displayBogies();
        
        // Display capacity analysis
        System.out.println("\n--- Capacity Analysis ---");
        Bogie highest = train.getHighestCapacityBogie();
        Bogie lowest = train.getLowestCapacityBogie();
        double average = train.getAverageCapacity();
        
        System.out.println("Highest Capacity: " + (highest != null ? highest.getName() + " (" + highest.getCapacity() + " seats)" : "N/A"));
        System.out.println("Lowest Capacity: " + (lowest != null ? lowest.getName() + " (" + lowest.getCapacity() + " seats)" : "N/A"));
        System.out.printf("Average Capacity: %.2f seats%n", average);
        System.out.println("Total Capacity: " + train.getTotalCapacity() + " seats\n");
        
        System.out.println("✓ Custom sorting with Comparator successfully implemented!");
        System.out.println("Program continues...");
    }
}
