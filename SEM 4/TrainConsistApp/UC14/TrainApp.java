import java.util.ArrayList;
import java.util.List;

public class TrainApp {
    private List<PassengerBogie> bogies;

    public TrainApp() {
        this.bogies = new ArrayList<>();
    }

    public void addBogie(PassengerBogie bogie) {
        bogies.add(bogie);
    }

    public List<PassengerBogie> getBogies() {
        return bogies;
    }

    public static void printBogies(String title, List<PassengerBogie> bogies) {
        System.out.println("\n" + title);
        System.out.println("ID       | Type         | Capacity");
        System.out.println("-----------------------------------");
        if (bogies.isEmpty()) {
            System.out.println("No valid passenger bogies were added.");
        } else {
            for (PassengerBogie bogie : bogies) {
                System.out.println(bogie);
            }
        }
        System.out.println("-----------------------------------");
        System.out.println("Total valid bogies: " + bogies.size() + "\n");
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC14: Handle Invalid Bogie Capacity (Custom Exception)\n");

        TrainApp app = new TrainApp();

        System.out.println("Creating valid passenger bogies...");
        try {
            app.addBogie(new PassengerBogie("PB001", "Sleeper", 72));
            app.addBogie(new PassengerBogie("PB002", "AC Chair", 54));
            app.addBogie(new PassengerBogie("PB003", "First Class", 95));
            System.out.println("✓ Valid bogies created successfully\n");
        } catch (InvalidCapacityException e) {
            System.out.println("✗ Unexpected exception while creating valid bogies: " + e.getMessage());
        }

        System.out.println("Attempting to create bogie with negative capacity...");
        try {
            app.addBogie(new PassengerBogie("PB004", "Sleeper", -10));
            System.out.println("✗ Error: Invalid bogie was created with negative capacity");
        } catch (InvalidCapacityException e) {
            System.out.println("✓ Exception caught: " + e.getMessage());
        }

        System.out.println("Attempting to create bogie with zero capacity...");
        try {
            app.addBogie(new PassengerBogie("PB005", "AC Chair", 0));
            System.out.println("✗ Error: Invalid bogie was created with zero capacity");
        } catch (InvalidCapacityException e) {
            System.out.println("✓ Exception caught: " + e.getMessage());
        }

        printBogies("Current Passenger Bogies in Train Consist", app.getBogies());

        System.out.println("Verifying object integrity for valid bogies...");
        for (PassengerBogie bogie : app.getBogies()) {
            System.out.printf("Bogies %s contains type '%s' with capacity %d\n",
                    bogie.getId(), bogie.getType(), bogie.getCapacity());
        }

        System.out.println("\nProgram continues safely after invalid input handling...");
    }
}
