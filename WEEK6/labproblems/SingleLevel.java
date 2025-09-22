package WEEK6.labproblems;

class Fruit {
    protected String color;
    protected String taste;

    Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }
}

class Apple extends Fruit {
    private String variety;

    Apple(String color, String taste, String variety) {
        super(color, taste);  // Call parent constructor
        this.variety = variety;
    }

    void display() {
        System.out.println("Apple Variety: " + variety);
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }
}

public class SingleLevel {
    public static void main(String[] args) {
        Apple a = new Apple("Red", "Sweet", "Fuji");
        a.display();
    }
}
