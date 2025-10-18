class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee Details → ID: " + id + ", Name: " + name + ", Salary: ₹" + salary;
    }
}

public class Topic1 {
    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Aarav", 55000);
        Employee e2 = new Employee(102, "Diya", 62000);
        Employee e3 = new Employee(103, "Kabir", 70000);

        System.out.println(e1);
        System.out.println("Class Name: " + e1.getClass().getName());

        System.out.println(e2);
        System.out.println("Class Name: " + e2.getClass().getName());

        System.out.println(e3);
        System.out.println("Class Name: " + e3.getClass().getName());
    }
}
