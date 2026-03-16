package WEEK8.labproblems;

// Abstract class Shape
abstract class Shape {
    protected double area;
    protected double perimeter;

    // Abstract methods
    abstract void calculateArea();
    abstract void calculatePerimeter();
}

// Interface Drawable
interface Drawable {
    void draw();
}

// Circle class extending Shape and implementing Drawable
class Circle extends Shape implements Drawable {
    private double radius;

    // Constructor
    Circle(double radius) {
        this.radius = radius;
    }

    // Implementing abstract methods
    void calculateArea() {
        area = Math.PI * radius * radius;
        System.out.println("Area of Circle: " + area);
    }

    void calculatePerimeter() {
        perimeter = 2 * Math.PI * radius;
        System.out.println("Perimeter of Circle: " + perimeter);
    }

    // Implementing method from Drawable interface
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
}

// Main class to demonstrate abstraction and interface
public class ShapeDemo {
    public static void main(String[] args) {
        Circle c = new Circle(5.0);
        c.draw();
        c.calculateArea();
        c.calculatePerimeter();
    }
}

