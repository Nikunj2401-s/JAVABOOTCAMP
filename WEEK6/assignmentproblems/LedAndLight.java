package WEEK6.assignmentproblems;

class Light {
    String type;

    Light() {
        this("Generic Light"); // calls another constructor
        System.out.println("Light Default Constructor");
    }

    Light(String type) {
        this.type = type;
        System.out.println("Light Parameterized Constructor: " + type);
    }
}

class LED extends Light {
    int watt;

    LED() {
        this("LED Light", 10); // calls another constructor in same class
        System.out.println("LED Default Constructor");
    }

    LED(String type, int watt) {
        super(type); // calls parent constructor
        this.watt = watt;
        System.out.println("LED Parameterized Constructor: Watt = " + watt);
    }
}

public class LedAndLight {
    public static void main(String[] args) {
        System.out.println("-- Creating LED object with default constructor --");
        LED led1 = new LED();

        System.out.println("\n-- Creating LED object with parameterized constructor --");
        LED led2 = new LED("Bright LED", 20);
    }
}
