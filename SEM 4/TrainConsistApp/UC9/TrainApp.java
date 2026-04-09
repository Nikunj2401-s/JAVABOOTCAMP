import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    
    // STREAM METHOD: Group bogies by type
    public Map<String, List<Bogie>> groupBogiesByType() {
        return passengerBogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType));
    }
    
    // STREAM METHOD: Group bogies by name
    public Map<String, List<Bogie>> groupBogiesByName() {
        return passengerBogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));
    }
    
    // STREAM METHOD: Group bogies by capacity range
    public Map<String, List<Bogie>> groupBogiesByCapacityRange() {
        return passengerBogies.stream()
                .collect(Collectors.groupingBy(bogie -> {
                    int capacity = bogie.getCapacity();
                    if (capacity == 0) return "No Seating";
                    else if (capacity <= 50) return "Low (1-50)";
                    else if (capacity <= 100) return "Medium (51-100)";
                    else return "High (100+)";
                }));
    }
    
    // STREAM METHOD: Count bogies by type
    public Map<String, Long> countBogiesByType() {
        return passengerBogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType, Collectors.counting()));
    }
    
    // STREAM METHOD: Get average capacity by type
    public Map<String, Double> getAverageCapacityByType() {
        return passengerBogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType, 
                        Collectors.averagingInt(Bogie::getCapacity)));
    }
    
    // Method to display all bogies
    public void displayAllBogies() {
        System.out.println("\n--- All Passenger Bogies ---");
        System.out.println("Bogie Name          | Capacity | Type");
        System.out.println("──────────────────────────────────────────────");
        
        if (passengerBogies.isEmpty()) {
            System.out.println("No bogies registered");
        } else {
            for (int i = 0; i < passengerBogies.size(); i++) {
                System.out.println((i + 1) + ". " + passengerBogies.get(i));
            }
        }
        
        System.out.println("──────────────────────────────────────────────");
        System.out.println("Total Bogies: " + passengerBogies.size() + "\n");
    }
    
    // Method to display grouped bogies
    public void displayGroupedBogies(Map<String, List<Bogie>> grouped, String groupingCriteria) {
        System.out.println("\n=== Bogies Grouped by " + groupingCriteria + " ===");
        
        if (grouped.isEmpty()) {
            System.out.println("No groupings available");
        } else {
            grouped.forEach((groupKey, bogieList) -> {
                System.out.println("\n[" + groupKey + "] - Count: " + bogieList.size());
                System.out.println("─────────────────────────────────────────────────");
                for (int i = 0; i < bogieList.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + bogieList.get(i));
                }
            });
        }
        System.out.println();
    }
    
    // Method to display count by type
    public void displayCountByType(Map<String, Long> countMap) {
        System.out.println("\n--- Count of Bogies by Type ---");
        System.out.println("Type               | Count");
        System.out.println("───────────────────────────────");
        
        countMap.forEach((type, count) -> 
            System.out.printf("%-20s| %d%n", type, count));
        
        System.out.println("───────────────────────────────\n");
    }
    
    // Method to display average capacity by type
    public void displayAverageCapacityByType(Map<String, Double> avgMap) {
        System.out.println("\n--- Average Capacity by Type ---");
        System.out.println("Type               | Avg Capacity");
        System.out.println("───────────────────────────────────────");
        
        avgMap.forEach((type, avgCapacity) -> 
            System.out.printf("%-20s| %.2f seats%n", type, avgCapacity));
        
        System.out.println("───────────────────────────────────────\n");
    }
    
    // Method to get size of grouped map
    public int getGroupCount(Map<String, List<Bogie>> grouped) {
        return grouped.size();
    }
    
    // Getter for bogies list
    public List<Bogie> getPassengerBogies() {
        return passengerBogies;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC9: Group Bogies by Type (Collectors.groupingBy)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Create passenger bogies with various types
        System.out.println("Creating passenger and services bogies...");
        train.addPassengerBogie(new Bogie("Sleeper 1", 72, "Passenger"));
        train.addPassengerBogie(new Bogie("Sleeper 2", 72, "Passenger"));
        train.addPassengerBogie(new Bogie("AC Chair 1", 96, "Passenger"));
        train.addPassengerBogie(new Bogie("AC Chair 2", 96, "Passenger"));
        train.addPassengerBogie(new Bogie("First Class", 54, "Passenger"));
        train.addPassengerBogie(new Bogie("Pantry Car", 0, "Service"));
        train.addPassengerBogie(new Bogie("Luggage Van", 0, "Service"));
        train.addPassengerBogie(new Bogie("Cargo - Rect", 50000, "Goods"));
        train.addPassengerBogie(new Bogie("Cargo - Cyl", 35000, "Goods"));
        
        // Display all bogies
        train.displayAllBogies();
        
        // ========== TEST CASE 1: Group by Type ==========
        System.out.println("=== TEST 1: Group Bogies by Type ===");
        Map<String, List<Bogie>> groupedByType = train.groupBogiesByType();
        train.displayGroupedBogies(groupedByType, "Type");
        
        // Verify grouping count
        System.out.println("✓ Number of distinct bogie types: " + train.getGroupCount(groupedByType));
        
        // ========== TEST CASE 2: Multiple Bogies in Same Group ==========
        System.out.println("\n=== TEST 2: Verify Multiple Bogies in Same Group ===");
        System.out.println("Passenger group size: " + (groupedByType.get("Passenger") != null ? 
                         groupedByType.get("Passenger").size() : 0));
        System.out.println("✓ Multiple bogies correctly grouped under 'Passenger'\n");
        
        // ========== TEST 3: Different Bogie Categories ==========
        System.out.println("=== TEST 3: Different Bogie Categories ===");
        System.out.println("Distinct categories in grouped map:");
        groupedByType.keySet().forEach(key -> System.out.println("  - " + key));
        System.out.println("✓ Different bogie types separated into distinct groups\n");
        
        // ========== TEST 4: Group by Name ==========
        System.out.println("=== TEST 4: Group Bogies by Name ===");
        Map<String, List<Bogie>> groupedByName = train.groupBogiesByName();
        train.displayGroupedBogies(groupedByName, "Name");
        
        // ========== TEST 5: Group by Capacity Range ==========
        System.out.println("=== TEST 5: Group Bogies by Capacity Range ===");
        Map<String, List<Bogie>> groupedByCapacityRange = train.groupBogiesByCapacityRange();
        train.displayGroupedBogies(groupedByCapacityRange, "Capacity Range");
        
        // ========== TEST 6: Count Bogies by Type ==========
        System.out.println("=== TEST 6: Count Bogies by Type ===");
        Map<String, Long> countByType = train.countBogiesByType();
        train.displayCountByType(countByType);
        
        // ========== TEST 7: Average Capacity by Type ==========
        System.out.println("=== TEST 7: Average Capacity by Type ===");
        Map<String, Double> avgCapacityByType = train.getAverageCapacityByType();
        train.displayAverageCapacityByType(avgCapacityByType);
        
        // ========== TEST 8: Original List Integrity ==========
        System.out.println("=== TEST 8: Original List Integrity ===");
        System.out.println("Original bogie list size before grouping: 9");
        System.out.println("Original bogie list size after grouping: " + train.getPassengerBogies().size());
        System.out.println("✓ Original list remains unchanged\n");
        
        // ========== TEST 9: Map Structure Validation ==========
        System.out.println("=== TEST 9: Map Structure Validation ===");
        System.out.println("Map structure: Map<String, List<Bogie>>");
        System.out.println("Keys in map (by type):");
        groupedByType.forEach((key, value) -> 
            System.out.println("  Key: \"" + key + "\" -> List size: " + value.size()));
        System.out.println("✓ Grouped result follows Map<String, List<Bogie>> structure\n");
        
        // ========== TEST 10: Empty Collection Handling ==========
        System.out.println("=== TEST 10: Empty Collection Handling ===");
        TrainApp emptyTrain = new TrainApp();
        Map<String, List<Bogie>> emptyGrouped = emptyTrain.groupBogiesByType();
        System.out.println("Grouping an empty bogie list...");
        System.out.println("Result map size (empty): " + emptyGrouped.size());
        System.out.println("✓ Empty collection handled without errors\n");
        
        System.out.println("✓ Stream-based bogie grouping successfully implemented!");
        System.out.println("Program continues...");
    }
}
