import java.util.LinkedHashSet;
import java.util.Set;

public class TrainApp {
    
    private Set<String> trainFormation;
    
    // Constructor to initialize the train with empty formation
    public TrainApp() {
        this.trainFormation = new LinkedHashSet<>();
    }
    
    // Method to attach a bogie to the train
    public void attachBogie(String bogie) {
        if (trainFormation.add(bogie)) {
            System.out.println("✓ Attached bogie: " + bogie);
        } else {
            System.out.println("✗ Duplicate bogie ignored: " + bogie);
        }
    }
    
    // Method to check if a bogie exists in the formation
    public boolean isAttached(String bogie) {
        return trainFormation.contains(bogie);
    }
    
    // Method to remove a bogie from the train
    public void detachBogie(String bogie) {
        if (trainFormation.remove(bogie)) {
            System.out.println("✓ Detached bogie: " + bogie);
        } else {
            System.out.println("✗ Bogie not found: " + bogie);
        }
    }
    
    // Method to display the train formation in insertion order
    public void displayFormation() {
        System.out.println("\n--- Train Formation (Insertion Order) ---");
        if (trainFormation.isEmpty()) {
            System.out.println("Train is empty");
        } else {
            System.out.println("Bogies attached in order:");
            int position = 1;
            for (String bogie : trainFormation) {
                if (position == 1) {
                    System.out.println("[FRONT] " + position + ". " + bogie);
                } else if (position == trainFormation.size()) {
                    System.out.println("        " + position + ". " + bogie + " [REAR]");
                } else {
                    System.out.println("        " + position + ". " + bogie);
                }
                position++;
            }
        }
        System.out.println("Total bogies: " + trainFormation.size());
        System.out.println("----------------------------------------\n");
    }
    
    // Method to get the formation size
    public int getFormationSize() {
        return trainFormation.size();
    }
    
    // Getter for the formation
    public Set<String> getFormation() {
        return trainFormation;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC5: Preserve Insertion Order of Bogies (LinkedHashSet)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Attach bogies to the train
        System.out.println("Attaching bogies to the train...");
        train.attachBogie("Engine");
        train.attachBogie("Sleeper");
        train.attachBogie("Cargo");
        train.attachBogie("Guard Coach");
        
        // Display formation after initial attachment
        System.out.println("\nTrain formation after initial attachment:");
        train.displayFormation();
        
        // Attempt to attach duplicate bogie
        System.out.println("Attempting to attach duplicate bogie...");
        train.attachBogie("Sleeper");
        train.attachBogie("Engine");
        
        // Display formation after duplicate attempts
        System.out.println("\nTrain formation after duplicate attempts:");
        train.displayFormation();
        
        // Check if specific bogies are attached
        System.out.println("Checking bogie attachments...");
        System.out.println("Is 'Sleeper' attached? " + (train.isAttached("Sleeper") ? "Yes" : "No"));
        System.out.println("Is 'Pantry Car' attached? " + (train.isAttached("Pantry Car") ? "Yes" : "No"));
        
        // Detach a bogie
        System.out.println("\nDetaching 'Cargo' bogie...");
        train.detachBogie("Cargo");
        
        // Display final formation
        System.out.println("\nFinal train formation:");
        train.displayFormation();
        
        System.out.println("✓ Insertion order preserved with uniqueness enforced!");
        System.out.println("Program continues...");
    }
}
