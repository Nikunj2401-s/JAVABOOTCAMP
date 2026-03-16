package WEEK8.assignmentproblems;

// Parent interface
interface Animal {
    void eat();
}

// Child interface extending Animal
interface Pet extends Animal {
    void play();
}

// Class Dog implementing Pet interface
class Dog implements Pet {
    // Implementing method from Animal interface
    public void eat() {
        System.out.println("Dog is eating dog food.");
    }

    // Implementing method from Pet interface
    public void play() {
        System.out.println("Dog is playing fetch with its owner.");
    }
}

// Main class to test interface inheritance
public class InterfaceInheritance {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // From Animal interface
        d.play();  // From Pet interface
    }
}
