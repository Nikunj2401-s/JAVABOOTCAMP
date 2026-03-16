package WEEK6.assignmentproblems;

abstract class Food {
    // Template Method
    final void prepare() {
        wash();
        cook();
        serve();
        System.out.println("-----");
    }

    abstract void wash();
    abstract void cook();
    abstract void serve();
}

class Pizza extends Food {
    void wash() { System.out.println("Washing vegetables for Pizza"); }
    void cook() { System.out.println("Baking Pizza in oven"); }
    void serve() { System.out.println("Serving Pizza with cheese"); }
}

class Soup extends Food {
    void wash() { System.out.println("Washing ingredients for Soup"); }
    void cook() { System.out.println("Boiling Soup ingredients"); }
    void serve() { System.out.println("Serving hot Soup in bowl"); }
}

public class FoodPrep {
    public static void main(String[] args) {
        Food f1 = new Pizza();
        Food f2 = new Soup();

        f1.prepare();
        f2.prepare();
    }
}

