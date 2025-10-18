

// EmployeeAuthenticationSystem.java

class Employee {
    private int id;
    private String name;
    private String email;
    private String password;

    // Constructor
    public Employee(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Overriding equals() to compare Employee objects based on email and password
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference
        if (obj == null || getClass() != obj.getClass()) return false; // Type check

        Employee emp = (Employee) obj;
        return email.equals(emp.email) && password.equals(emp.password);
    }

    // Overriding hashCode() so that equal objects have the same hash
    @Override
    public int hashCode() {
        return email.hashCode() + password.hashCode();
    }

    @Override
    public String toString() {
        return "Employee [ID: " + id + ", Name: " + name + ", Email: " + email + "]";
    }
}

public class EmployeeAuthenticationSystem {
    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Alice", "alice@company.com", "pass123");
        Employee e2 = new Employee(102, "Bob", "bob@company.com", "qwerty");
        Employee e3 = new Employee(103, "Alice Duplicate", "alice@company.com", "pass123");

        System.out.println("e1: " + e1);
        System.out.println("e2: " + e2);
        System.out.println("e3: " + e3);
        System.out.println("------------------------------");

        // Reference comparison
        System.out.println("e1 == e3 → " + (e1 == e3));

        // Logical equality check
        System.out.println("e1.equals(e3) → " + e1.equals(e3));

        // Different credentials
        System.out.println("e1.equals(e2) → " + e1.equals(e2));

        // Hash codes
        System.out.println("e1.hashCode(): " + e1.hashCode());
        System.out.println("e3.hashCode(): " + e3.hashCode());
        System.out.println("e2.hashCode(): " + e2.hashCode());
    }
}
