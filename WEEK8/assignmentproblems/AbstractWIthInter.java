package WEEK8.assignmentproblems;
// Abstract class Vehicle
abstract class Vehicle {
    // Abstract method
    abstract void start();

    // Concrete method
    void stop() {
        System.out.println("Vehicle stopped safely.");
    }
}

// Interface Fuel
interface Fuel {
    void refuel();
}

// Class Car extending Vehicle and implementing Fuel
class Car extends Vehicle implements Fuel {
    private String model;

    Car(String model) {
        this.model = model;
    }

    // Implementing abstract method from Vehicle
    void start() {
        System.out.println(model + " engine started.");
    }

    // Implementing method from Fuel interface
    public void refuel() {
        System.out.println(model + " is being refueled with petrol.");
    }
}

// Main class to test all methods
public class AbstractWIthInter {
    public static void main(String[] args) {
        Car car = new Car("Toyota Corolla");
        car.start();     // from abstract class
        car.refuel();    // from interface
        car.stop();      // concrete method from abstract class
    }
}

