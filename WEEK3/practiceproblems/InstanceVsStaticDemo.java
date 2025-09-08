package javabootcamp.WEEK3.practiceproblems;

class Student {
    String name;               // instance member
    static int studentCount=0; // static member

    public Student(String name) {
        this.name = name;
        studentCount++;
    }

    public void displayInfo() {
        System.out.println("Student Name: " + name);
    }

    public static void displayTotalStudents() {
        System.out.println("Total Students: " + studentCount);
    }
}

public class InstanceVsStaticDemo {
    public static void main(String[] args) {
        Student s1 = new Student("Alice");
        Student s2 = new Student("Bob");

        s1.displayInfo(); 
        s2.displayInfo();

        Student.displayTotalStudents();
    }
}
