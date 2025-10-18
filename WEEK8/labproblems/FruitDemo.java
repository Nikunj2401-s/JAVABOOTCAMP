package WEEK8.labproblems;

// Abstract class Fruit
abstract class Fruit {
    protected String color;
    protected String taste;

    // Constructor
    Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    // Abstract method
    abstract void showDetails();
}

// Interface Edible
interface Edible {
    void nutrientsInfo();
}

// Class Apple extends Fruit and implements Edible
class Apple extends Fruit implements Edible {
    private String variety;

    Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    // Implementing abstract method from Fruit
    void showDetails() {
        System.out.println("Fruit: Apple");
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
        System.out.println("Variety: " + variety);
    }

    // Implementing method from Edible interface
    public void nutrientsInfo() {
        System.out.println("Nutrients: Rich in fiber, vitamins, and antioxidants.");
    }
}

// Main class to demonstrate abstraction and interface
public class FruitDemo {
    public static void main(String[] args) {
        Apple apple = new Apple("Red", "Sweet", "Fuji");
        apple.showDetails();
        apple.nutrientsInfo();
    }
}
