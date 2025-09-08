package javabootcamp.WEEK3.practiceproblems;

// Base class
class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayDetails() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Derived class (reuses Person's code)
class Student extends Person {
    private String studentId;

    public Student(String name, int age, String studentId) {
        super(name, age); // Reuse constructor from Person
        this.studentId = studentId;
    }

    public void displayDetails() {
        super.displayDetails(); // Reuse Person's method
        System.out.println("Student ID: " + studentId);
    }
}

// Another derived class
class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Subject: " + subject);
    }
}

public class OOPDemo {
    public static void main(String[] args) {
        Student s = new Student("Alice", 20, "S12345");
        Teacher t = new Teacher("Mr. Smith", 40, "Mathematics");

        System.out.println("--- Student Details ---");
        s.displayDetails();

        System.out.println("\n--- Teacher Details ---");
        t.displayDetails();
    }
}

