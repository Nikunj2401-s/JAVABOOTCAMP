package javabootcamp.WEEK3.Labproblems;

class Vehicle {
    String model;            // Instance member (unique to each object)
    int rentPerDay;          // Instance member
    boolean isRented;        // Instance member

    static int totalVehicles = 0;   // Static member (shared by all)
    static int rentedVehicles = 0;  // Static member

    Vehicle(String model, int rentPerDay) {
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isRented = false;
        totalVehicles++; // Count every new vehicle
    }

    void rentVehicle() {
        if (!isRented) {
            isRented = true;
            rentedVehicles++;
            System.out.println(model + " has been rented.");
        } else {
            System.out.println(model + " is already rented.");
        }
    }

    void returnVehicle() {
        if (isRented) {
            isRented = false;
            rentedVehicles--;
            System.out.println(model + " has been returned.");
        } else {
            System.out.println(model + " was not rented.");
        }
    }

    void displayInfo() {
        System.out.println("Model: " + model + " | Rent/Day: " + rentPerDay + " | Rented: " + isRented);
    }

    static void displayStats() {
        System.out.println("Total Vehicles: " + totalVehicles + " | Currently Rented: " + rentedVehicles);
    }
}

public class VehicleRentalDemo {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle("Car - Honda City", 2000);
        Vehicle v2 = new Vehicle("Bike - Royal Enfield", 800);

        v1.displayInfo();
        v2.displayInfo();

        System.out.println("\n--- Renting Vehicles ---");
        v1.rentVehicle();
        v2.rentVehicle();

        Vehicle.displayStats();  // Accessing static method

        System.out.println("\n--- Returning Vehicle ---");
        v1.returnVehicle();

        Vehicle.displayStats();
    }
}
