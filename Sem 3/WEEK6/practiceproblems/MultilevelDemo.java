package WEEK6.practiceproblems;

// 🛠 Practice Problem 2: Multilevel Inheritance Chain

class LivingBeing {
    LivingBeing() {
        System.out.println("LivingBeing constructor called");
    }
    void breathe() {
        System.out.println("Breathing...");
    }
}

class Animal extends LivingBeing {
    Animal() {
        super(); // calls LivingBeing constructor
        System.out.println("Animal constructor called");
    }
    void eat() {
        System.out.println("Animal is eating...");
    }
}

class Mammal extends Animal {
    Mammal() {
        super(); // calls Animal constructor
        System.out.println("Mammal constructor called");
    }
    void walk() {
        System.out.println("Mammal is walking...");
    }
}

class Human extends Mammal {
    Human() {
        super(); // calls Mammal constructor
        System.out.println("Human constructor called");
    }
    void speak() {
        System.out.println("Human is speaking...");
    }
}

public class MultilevelDemo {
    public static void main(String[] args) {
        Human h = new Human();  // triggers full constructor chain
        System.out.println("------ Actions ------");
        h.breathe(); // from LivingBeing
        h.eat();     // from Animal
        h.walk();    // from Mammal
        h.speak();   // from Human
    }
}

