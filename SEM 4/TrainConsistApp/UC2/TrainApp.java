import java.util.ArrayList;
import java.util.List;

public class TrainApp {
    
    private List<PassengerBogie> passengerBogies;
    
    // Constructor to initialize the train with empty passenger bogie list
    public TrainApp() {
        this.passengerBogies = new ArrayList<>();
    }
    
    // Method to add a passenger bogie
    public void addPassengerBogie(PassengerBogie bogie) {
        passengerBogies.add(bogie);
        System.out.println("✓ Added: " + bogie);
    }
    
    // Method to remove a passenger bogie
    public void removePassengerBogie(PassengerBogie bogie) {
        if (passengerBogies.remove(bogie)) {
            System.out.println("✓ Removed: " + bogie);
        } else {
            System.out.println("✗ Bogie not found: " + bogie);
        }
    }
    
    // Method to check if a passenger bogie exists
    public boolean containsPassengerBogie(PassengerBogie bogie) {
        return passengerBogies.contains(bogie);
    }
    
    // Method to display all passenger bogies
    public void displayPassengerBogies() {
        System.out.println("\n--- Passenger Bogies ---");
        if (passengerBogies.isEmpty()) {
            System.out.println("No passenger bogies in the train");
        } else {
            for (int i = 0; i < passengerBogies.size(); i++) {
                System.out.println((i + 1) + ". " + passengerBogies.get(i));
            }
        }
        System.out.println("Total bogies: " + passengerBogies.size());
        System.out.println("------------------------\n");
    }
    
    // Getter for passenger bogies list
    public List<PassengerBogie> getPassengerBogies() {
        return passengerBogies;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC2: Add Passenger Bogies to Train\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Create passenger bogies
        PassengerBogie sleeper = new PassengerBogie("Sleeper", 72);
        PassengerBogie acChair = new PassengerBogie("AC Chair", 96);
        PassengerBogie firstClass = new PassengerBogie("First Class", 54);
        
        // Add passenger bogies
        System.out.println("Adding passenger bogies...");
        train.addPassengerBogie(sleeper);
        train.addPassengerBogie(acChair);
        train.addPassengerBogie(firstClass);
        
        // Display bogies after insertion
        System.out.println("\nAfter adding bogies:");
        train.displayPassengerBogies();
        
        // Remove a bogie
        System.out.println("Removing AC Chair bogie...");
        train.removePassengerBogie(acChair);
        
        // Check if Sleeper exists
        System.out.println("\nChecking if Sleeper bogie exists...");
        if (train.containsPassengerBogie(sleeper)) {
            System.out.println("✓ Sleeper bogie exists in the train");
        } else {
            System.out.println("✗ Sleeper bogie does not exist");
        }
        
        // Display final list state
        System.out.println("\nFinal bogie list:");
        train.displayPassengerBogies();
        
        System.out.println("Program continues...");
    }
}
