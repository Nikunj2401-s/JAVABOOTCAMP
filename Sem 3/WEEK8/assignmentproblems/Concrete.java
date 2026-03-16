package WEEK8.assignmentproblems;

// Abstract class Shape
abstract class Shape {
    // Abstract methods
    abstract double area();
    abstract double perimeter();

    // Concrete method
    void displayInfo() {
        System.out.println("This is a geometric shape.");
    }
}

// Subclass Circle
class Circle extends Shape {
    private double radius;

    // Constructor
    Circle(double radius) {
        this.radius = radius;
    }

    // Implementing abstract methods
    double area() {
        return Math.PI * radius * radius;
    }

    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// Subclass Rectangle
class Rectangle extends Shape {
    private double length;
    private double width;

    // Constructor
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // Implementing abstract methods
    double area() {
        return length * width;
    }

    double perimeter() {
        return 2 * (length + width);
    }
}

// Main class to test implementation
public class Concrete {
    public static void main(String[] args) {
        // Create Circle object
        Circle c = new Circle(5);
        c.displayInfo();
        System.out.println("Circle Area: " + c.area());
        System.out.println("Circle Perimeter: " + c.perimeter());

        System.out.println();

        // Create Rectangle object
        Rectangle r = new Rectangle(4, 6);
        r.displayInfo();
        System.out.println("Rectangle Area: " + r.area());
        System.out.println("Rectangle Perimeter: " + r.perimeter());
    }
}

