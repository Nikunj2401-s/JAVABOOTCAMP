package WEEK6.labproblems;

class Bird {
    void fly() {
        System.out.println("Bird is flying...");
    }
}

class Penguin extends Bird {
    @Override
    void fly() {
        System.out.println("Penguin cannot fly, but swims!");
    }
}

class Eagle extends Bird {
    @Override
    void fly() {
        System.out.println("Eagle soars high in the sky!");
    }
}

public class FlyingBird {
    public static void main(String[] args) {
        Bird[] birds = { new Bird(), new Penguin(), new Eagle() };

        for (Bird b : birds) {
            b.fly();   // Polymorphism in action
        }
    }
}

