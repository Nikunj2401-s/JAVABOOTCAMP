package WEEK8.practiceproblems;
// Abstract class
abstract class Vehicle {
    // Abstract method
    abstract void start();
}

// Subclass Car extending Vehicle
class Car extends Vehicle {
    void start() {
        System.out.println("Car starts with a key ignition.");
    }
}

// Subclass Bike extending Vehicle
class Bike extends Vehicle {
    void start() {
        System.out.println("Bike starts with a kick or self-start button.");
    }
}

// Main class to demonstrate abstraction
public class VehicleAb {
    public static void main(String[] args) {
        // Using Vehicle reference to call subclass methods
        Vehicle v1 = new Car();
        Vehicle v2 = new Bike();

        v1.start();
        v2.start();
    }
}
