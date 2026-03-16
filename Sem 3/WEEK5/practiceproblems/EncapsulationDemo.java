package WEEK5.practiceproblems;

class Student {
    // 🔒 Private fields
    private int id;
    private String name;

    // ✅ Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // ✅ Getter for id
    public int getId() {
        return id;
    }

    // ✅ Setter for id (with validation)
    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Invalid ID. Must be > 0.");
        }
    }

    // ✅ Getter for name
    public String getName() {
        return name;
    }

    // ✅ Setter for name (with validation)
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name.");
        }
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        // Creating object
        Student s1 = new Student(101, "Alice");

        // Accessing data using getters
        System.out.println("ID: " + s1.getId());
        System.out.println("Name: " + s1.getName());

        // Modifying data using setters
        s1.setId(202);
        s1.setName("Bob");

        System.out.println("Updated ID: " + s1.getId());
        System.out.println("Updated Name: " + s1.getName());

        // Trying invalid values
        s1.setId(-5);
        s1.setName("");
    }
}

