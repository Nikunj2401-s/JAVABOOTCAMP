import java.util.LinkedList;

public class TrainApp {
    
    private LinkedList<String> trainConsist;
    
    // Constructor to initialize the train with empty consist
    public TrainApp() {
        this.trainConsist = new LinkedList<>();
    }
    
    // Method to add a bogie at the end of the consist
    public void addLast(String bogie) {
        trainConsist.addLast(bogie);
        System.out.println("✓ Added at end: " + bogie);
    }
    
    // Method to add a bogie at the beginning of the consist
    public void addFirst(String bogie) {
        trainConsist.addFirst(bogie);
        System.out.println("✓ Added at beginning: " + bogie);
    }
    
    // Method to add a bogie at a specific position
    public void addAtPosition(int index, String bogie) {
        if (index >= 0 && index <= trainConsist.size()) {
            trainConsist.add(index, bogie);
            System.out.println("✓ Added at position " + index + ": " + bogie);
        } else {
            System.out.println("✗ Invalid position: " + index);
        }
    }
    
    // Method to remove the first bogie
    public void removeFirst() {
        if (!trainConsist.isEmpty()) {
            String removed = trainConsist.removeFirst();
            System.out.println("✓ Removed from beginning: " + removed);
        } else {
            System.out.println("✗ Train consist is empty");
        }
    }
    
    // Method to remove the last bogie
    public void removeLast() {
        if (!trainConsist.isEmpty()) {
            String removed = trainConsist.removeLast();
            System.out.println("✓ Removed from end: " + removed);
        } else {
            System.out.println("✗ Train consist is empty");
        }
    }
    
    // Method to get the first bogie
    public String getFirst() {
        return trainConsist.isEmpty() ? null : trainConsist.getFirst();
    }
    
    // Method to get the last bogie
    public String getLast() {
        return trainConsist.isEmpty() ? null : trainConsist.getLast();
    }
    
    // Method to display the entire train consist
    public void displayConsist() {
        System.out.println("\n--- Train Consist (Ordered) ---");
        if (trainConsist.isEmpty()) {
            System.out.println("Train is empty");
        } else {
            System.out.println("Train formation (front to back):");
            for (int i = 0; i < trainConsist.size(); i++) {
                if (i == 0) {
                    System.out.println("[FRONT] " + (i + 1) + ". " + trainConsist.get(i));
                } else if (i == trainConsist.size() - 1) {
                    System.out.println("        " + (i + 1) + ". " + trainConsist.get(i) + " [REAR]");
                } else {
                    System.out.println("        " + (i + 1) + ". " + trainConsist.get(i));
                }
            }
        }
        System.out.println("Total bogies: " + trainConsist.size());
        System.out.println("--------------------------------\n");
    }
    
    // Method to get the consist size
    public int getSize() {
        return trainConsist.size();
    }
    
    // Getter for the consist
    public LinkedList<String> getConsist() {
        return trainConsist;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC4: Maintain Ordered Bogie IDs (LinkedList)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Add bogies to form the train consist
        System.out.println("Building train consist...");
        train.addLast("Engine");
        train.addLast("Sleeper");
        train.addLast("AC Chair");
        train.addLast("Cargo");
        train.addLast("Guard Coach");
        
        // Display initial consist
        System.out.println("\nInitial train consist:");
        train.displayConsist();
        
        // Insert a Pantry Car at position 2
        System.out.println("Inserting Pantry Car at position 2...");
        train.addAtPosition(2, "Pantry Car");
        
        // Display consist after insertion
        System.out.println("\nConsist after adding Pantry Car at position 2:");
        train.displayConsist();
        
        // Remove the first bogie (Engine should stay, so remove from logical position)
        System.out.println("Removing first bogie...");
        train.removeFirst();
        
        // Display consist after removing first
        System.out.println("\nConsist after removing first bogie:");
        train.displayConsist();
        
        // Remove the last bogie
        System.out.println("Removing last bogie...");
        train.removeLast();
        
        // Display final consist
        System.out.println("\nFinal train consist:");
        train.displayConsist();
        
        System.out.println("✓ Train formation maintained successfully!");
        System.out.println("Program continues...");
    }
}
