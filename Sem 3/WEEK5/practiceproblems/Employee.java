package WEEK5.practiceproblems;

import java.io.Serializable;

// ✅ JavaBean-compliant class
public class Employee implements Serializable {
    private int id;
    private String name;
    private double salary;
    private boolean active;

    // 1️⃣ No-arg constructor (required for JavaBean)
    public Employee() {
    }

    // 2️⃣ Parameterized constructor (not required, but useful)
    public Employee(int id, String name, double salary, boolean active) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.active = active;
    }

    // 3️⃣ Getters and Setters (JavaBean naming convention)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // For boolean → getter uses "is"
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}

// ✅ Testing the JavaBean
class TestEmployee {
    public static void main(String[] args) {
        // Using no-arg constructor + setters
        Employee e1 = new Employee();
        e1.setId(101);
        e1.setName("Alice");
        e1.setSalary(50000.0);
        e1.setActive(true);

        System.out.println("Employee 1: " + e1.getId() + ", " + e1.getName() +
                ", " + e1.getSalary() + ", active=" + e1.isActive());

        // Using parameterized constructor
        Employee e2 = new Employee(102, "Bob", 60000.0, false);
        System.out.println("Employee 2: " + e2.getId() + ", " + e2.getName() +
                ", " + e2.getSalary() + ", active=" + e2.isActive());
    }
}

