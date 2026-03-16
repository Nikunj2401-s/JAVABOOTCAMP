package WEEK6.practiceproblems;

// 🛠 Practice Problem 1: Single Inheritance with extends and super

class Person {
    String name;
    int age;

    // Parent constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person constructor called");
    }

    void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Student inherits from Person
class Student extends Person {
    String course;

    // Child constructor uses super() to call parent constructor
    Student(String name, int age, String course) {
        super(name, age);  // calls Person constructor
        this.course = course;
        System.out.println("Student constructor called");
    }

    void displayStudent() {
        super.displayInfo(); // calling parent method
        System.out.println("Course: " + course);
    }
}

public class StudentDemo {
    public static void main(String[] args) {
        // Creating a Student object
        Student s = new Student("Alice", 20, "Computer Science");
        s.displayStudent();
    }
}

