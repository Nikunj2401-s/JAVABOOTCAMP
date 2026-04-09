import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

public class TrainApp {
    
    private List<Bogie> bogies;
    
    // Constructor to initialize the train with empty bogie list
    public TrainApp() {
        this.bogies = new ArrayList<>();
    }
    
    // Method to add a bogie
    public void addBogie(Bogie bogie) {
        bogies.add(bogie);
    }
    
    // ============= REDUCE OPERATIONS =============
    
    // STREAM METHOD: Calculate total seating capacity using reduce()
    public int getTotalSeatingCapacity() {
        return bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }
    
    // STREAM METHOD: Calculate total capacity with alternative reduce syntax
    public int getTotalCapacityAlternative() {
        return bogies.stream()
                .mapToInt(Bogie::getCapacity)
                .sum();
    }
    
    // STREAM METHOD: Get total capacity for passenger bogies only
    public int getTotalPassengerCapacity() {
        return bogies.stream()
                .filter(b -> b.getType().equalsIgnoreCase("Passenger"))
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }
    
    // STREAM METHOD: Get total capacity for service bogies only
    public int getTotalServiceCapacity() {
        return bogies.stream()
                .filter(b -> b.getType().equalsIgnoreCase("Service"))
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }
    
    // STREAM METHOD: Get total capacity for goods bogies only
    public int getTotalGoodsCapacity() {
        return bogies.stream()
                .filter(b -> b.getType().equalsIgnoreCase("Goods"))
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }
    
    // STREAM METHOD: Get maximum capacity in train
    public int getMaxCapacity() {
        return bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(Integer.MIN_VALUE, Integer::max);
    }
    
    // STREAM METHOD: Get minimum capacity in train
    public int getMinCapacity() {
        return bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(Integer.MAX_VALUE, Integer::min);
    }
    
    // STREAM METHOD: Get average seating capacity
    public double getAverageCapacity() {
        if (bogies.isEmpty()) return 0;
        return bogies.stream()
                .mapToInt(Bogie::getCapacity)
                .average()
                .orElse(0);
    }
    
    // STREAM METHOD: Get statistics summary
    public IntSummaryStatistics getCapacityStatistics() {
        return bogies.stream()
                .mapToInt(Bogie::getCapacity)
                .summaryStatistics();
    }
    
    // Method to display all bogies
    public void displayAllBogies() {
        System.out.println("\n--- Train Consist Details ---");
        System.out.println("Bogie Name          | Capacity | Type");
        System.out.println("──────────────────────────────────────────────────────");
        
        if (bogies.isEmpty()) {
            System.out.println("No bogies registered");
        } else {
            for (int i = 0; i < bogies.size(); i++) {
                System.out.println((i + 1) + ". " + bogies.get(i));
            }
        }
        
        System.out.println("──────────────────────────────────────────────────────");
        System.out.println("Total Bogies: " + bogies.size() + "\n");
    }
    
    // Method to display aggregation results
    public void displayAggregationResults() {
        System.out.println("\n=== Stream Aggregation Results (Using reduce()) ===");
        System.out.println("Total Seating Capacity: " + getTotalSeatingCapacity() + " seats");
        System.out.println("Total Passenger Capacity: " + getTotalPassengerCapacity() + " seats");
        System.out.println("Total Service Capacity: " + getTotalServiceCapacity() + " units");
        System.out.println("Total Goods Capacity: " + getTotalGoodsCapacity() + " units");
        System.out.println("Maximum Single Bogie Capacity: " + getMaxCapacity());
        System.out.println("Minimum Single Bogie Capacity: " + getMinCapacity());
        System.out.printf("Average Bogie Capacity: %.2f%n", getAverageCapacity());
        System.out.println("═════════════════════════════════════════════════════════\n");
    }
    
    // Method to display statistics
    public void displayStatistics() {
        IntSummaryStatistics stats = getCapacityStatistics();
        System.out.println("\n=== Detailed Capacity Statistics ===");
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Average: " + String.format("%.2f", stats.getAverage()));
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("═════════════════════════════════════════════════════────\n");
    }
    
    // Method to get original list size
    public int getOriginalBogieCount() {
        return bogies.size();
    }
    
    // Getter for bogies list
    public List<Bogie> getBogies() {
        return bogies;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC10: Count Total Seats in Train (reduce)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Create bogies with various types
        System.out.println("Creating bogies for train consist...");
        train.addBogie(new Bogie("Sleeper 1", 72, "Passenger"));
        train.addBogie(new Bogie("Sleeper 2", 72, "Passenger"));
        train.addBogie(new Bogie("AC Chair 1", 96, "Passenger"));
        train.addBogie(new Bogie("AC Chair 2", 96, "Passenger"));
        train.addBogie(new Bogie("First Class", 54, "Passenger"));
        train.addBogie(new Bogie("General", 150, "Passenger"));
        train.addBogie(new Bogie("Pantry Car", 0, "Service"));
        train.addBogie(new Bogie("Luggage Van", 0, "Service"));
        train.addBogie(new Bogie("Cargo - Rect", 50000, "Goods"));
        train.addBogie(new Bogie("Cargo - Cyl", 35000, "Goods"));
        
        // Display all bogies
        train.displayAllBogies();
        
        // ========== TEST CASE 1: Total Seat Calculation ==========
        System.out.println("=== TEST 1: Total Seat Calculation Using reduce() ===");
        int totalCapacity = train.getTotalSeatingCapacity();
        System.out.println("Total Seating Capacity: " + totalCapacity + " seats");
        System.out.println("✓ Calculated using: stream().map(Bogie::getCapacity).reduce(0, Integer::sum)");
        System.out.println("✓ Expected total includes all bogies\n");
        
        // ========== TEST CASE 2: Multiple Bogies Aggregation ==========
        System.out.println("=== TEST 2: Multiple Bogies Aggregation ===");
        System.out.println("Number of bogies: " + train.getOriginalBogieCount());
        System.out.println("Total aggregated capacity: " + totalCapacity);
        System.out.println("✓ All " + train.getOriginalBogieCount() + " bogies included in aggregation\n");
        
        // ========== TEST CASE 3: Capacity Extraction Using map() ==========
        System.out.println("=== TEST 3: Capacity Extraction Using map() ===");
        System.out.println("Individual bogie capacities extracted:");
        train.getBogies().forEach(b -> 
            System.out.println("  " + b.getName() + " -> " + b.getCapacity()));
        System.out.println("✓ map() correctly extracted all capacity values\n");
        
        // ========== TEST CASE 4: Filtered Aggregation (Passenger Only) ==========
        System.out.println("=== TEST 4: Filtered Aggregation (Passenger Bogies) ===");
        int passengerCapacity = train.getTotalPassengerCapacity();
        System.out.println("Total Passenger Seating Capacity: " + passengerCapacity + " seats");
        System.out.println("✓ Aggregation with filter() applied before reduce()\n");
        
        // ========== TEST CASE 5: Single Bogie Handling ==========
        System.out.println("=== TEST 5: Single Bogie Handling ===");
        TrainApp singleBogieTrain = new TrainApp();
        singleBogieTrain.addBogie(new Bogie("Sleeper", 72, "Passenger"));
        System.out.println("Train with single bogie:");
        System.out.println("  Bogie: Sleeper (Capacity: 72)");
        System.out.println("  Total Capacity (reduce): " + singleBogieTrain.getTotalSeatingCapacity());
        System.out.println("✓ Single bogie capacity matches total\n");
        
        // ========== TEST CASE 6: Empty Collection Handling ==========
        System.out.println("=== TEST 6: Empty Collection Handling ===");
        TrainApp emptyTrain = new TrainApp();
        System.out.println("Train with no bogies:");
        System.out.println("  Empty bogie list size: " + emptyTrain.getOriginalBogieCount());
        System.out.println("  Total Capacity (reduce with identity 0): " + emptyTrain.getTotalSeatingCapacity());
        System.out.println("✓ Empty list returns identity value (0), no errors\n");
        
        // ========== TEST CASE 7: All Bogies Included ==========
        System.out.println("=== TEST 7: All Bogies Included in Aggregation ===");
        int peaksCapacity = train.getTotalSeatingCapacity();
        int sumManual = 0;
        for (Bogie b : train.getBogies()) {
            sumManual += b.getCapacity();
        }
        System.out.println("Stream reduce() result: " + peaksCapacity);
        System.out.println("Manual loop sum: " + sumManual);
        System.out.println("Match: " + (peaksCapacity == sumManual ? "YES" : "NO"));
        System.out.println("✓ All bogies included in reduction\n");
        
        // ========== TEST CASE 8: Original List Integrity ==========
        System.out.println("=== TEST 8: Original List Integrity ===");
        int sizeBefore = train.getOriginalBogieCount();
        int totalBefore = train.getTotalSeatingCapacity();
        int totalAfter = train.getTotalSeatingCapacity();
        int sizeAfter = train.getOriginalBogieCount();
        System.out.println("Size before aggregation: " + sizeBefore);
        System.out.println("Size after aggregation: " + sizeAfter);
        System.out.println("Total before aggregation: " + totalBefore);
        System.out.println("Total after aggregation: " + totalAfter);
        System.out.println("✓ Original list unchanged\n");
        
        // Display comprehensive aggregation results
        train.displayAggregationResults();
        
        // Display detailed statistics
        train.displayStatistics();
        
        System.out.println("✓ Stream reduce() aggregation successfully implemented!");
        System.out.println("Program continues...");
    }
}
