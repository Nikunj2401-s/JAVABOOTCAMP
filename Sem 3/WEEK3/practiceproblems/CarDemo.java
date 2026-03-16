package javabootcamp.WEEK3.practiceproblems;

// Class = Blueprint for Car
class Car {
    private String brand;
    private String color;
    private int speed;

    // Constructor
    public Car(String brand, String color) {
        this.brand = brand;
        this.color = color;
        this.speed = 0; // default speed
    }

    // Method to accelerate
    public void accelerate(int increase) {
        speed += increase;
        System.out.println(brand + " accelerated to " + speed + " km/h");
    }

    // Method to apply brakes
    public void brake(int decrease) {
        if (speed - decrease < 0) {
            speed = 0;
        } else {
            speed -= decrease;
        }
        System.out.println(brand + " slowed down to " + speed + " km/h");
    }

    // Display details of the car
    public void displayInfo() {
        System.out.println("Car Brand: " + brand);
        System.out.println("Color    : " + color);
        System.out.println("Speed    : " + speed + " km/h");
        System.out.println("-------------------------");
    }
}

// Main class
public class CarDemo {
    public static void main(String[] args) {
        // Creating objects (real cars from the blueprint)
        Car car1 = new Car("Toyota", "Red");
        Car car2 = new Car("BMW", "Black");

        // Using methods on objects
        car1.displayInfo();
        car1.accelerate(50);
        car1.brake(20);

        car2.displayInfo();
        car2.accelerate(80);
        car2.brake(50);
    }
}
