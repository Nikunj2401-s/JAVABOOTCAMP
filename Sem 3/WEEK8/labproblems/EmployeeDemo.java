package WEEK8.labproblems;

// Abstract class Employee
abstract class Employee {
    protected String name;
    protected double salary;

    // Constructor
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Abstract method
    abstract double calculateBonus();
}

// Interface Payable
interface Payable {
    void generatePaySlip();
}

// Manager class extending Employee and implementing Payable
class Manager extends Employee implements Payable {
    private String department;

    // Constructor
    Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    // Implementing abstract method from Employee
    double calculateBonus() {
        double bonus = salary * 0.20; // 20% bonus
        System.out.println("Bonus for " + name + ": " + bonus);
        return bonus;
    }

    // Implementing method from Payable interface
    public void generatePaySlip() {
        System.out.println("\n--- Pay Slip ---");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: " + salary);
        System.out.println("Total (with Bonus): " + (salary + calculateBonus()));
        System.out.println("----------------");
    }
}

// Main class to demonstrate abstraction and interface
public class EmployeeDemo {
    public static void main(String[] args) {
        Manager mgr = new Manager("Alice", 80000, "Finance");
        mgr.generatePaySlip();
    }
}
