package WEEK7.practiceproblems;

// Parent class
class Person {
    void displayRole() {
        System.out.println("I am a person in the university.");
    }
}

// Child class 1
class Student extends Person {
    void displayRole() {
        System.out.println("I am a student. I study in the university.");
    }
    void study() {
        System.out.println("Student is studying.");
    }
}

// Child class 2
class Professor extends Person {
    void displayRole() {
        System.out.println("I am a professor. I teach in the university.");
    }
    void teach() {
        System.out.println("Professor is teaching.");
    }
}

public class UniversitySystem {
    public static void main(String[] args) {
        // Upcasting: Student object → Person reference
        Person p1 = new Student();
        p1.displayRole();   // Calls Student’s overridden method
        // p1.study();  ❌ Not allowed (Person reference can't see Student-specific method)

        // Upcasting: Professor object → Person reference
        Person p2 = new Professor();
        p2.displayRole();   // Calls Professor’s overridden method
        // p2.teach();  ❌ Not allowed (Person reference can't see Professor-specific method)

        // Direct access with child reference
        Student s = new Student();
        s.study();  // Allowed
    }
}

