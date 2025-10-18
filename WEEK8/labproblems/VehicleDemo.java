package WEEK8.labproblems;

// Abstract class Vehicle
abstract class Vehicle {
    protected int speed;
    protected String fuelType;

    // Constructor
    Vehicle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    // Abstract method
    abstract void startEngine();
}

// Interface Maintainable
interface Maintainable {
    void serviceInfo();
}

// Car class extending Vehicle and implementing Maintainable
class Car extends Vehicle implements Maintainable {
    private String model;

    // Constructor
    Car(int speed, String fuelType, String model) {
        super(speed, fuelType);
        this.model = model;
    }

    // Implementing abstract method from Vehicle
    void startEngine() {
        System.out.println("Starting " + model + " engine. Fuel Type: " + fuelType);
        System.out.println("Car is running at speed: " + speed + " km/h");
    }

    // Implementing method from Maintainable interface
    public void serviceInfo() {
        System.out.println("Service Info: Regular servicing every 6 months or 10,000 km.");
    }
}

// Main class to demonstrate abstraction and interface
public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car(120, "Petrol", "Honda City");
        car.startEngine();
        car.serviceInfo();
    }
}
