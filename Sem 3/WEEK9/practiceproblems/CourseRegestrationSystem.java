package WEEK9.practiceproblems;

// CourseRegistrationSystem.java

class Course {
    String courseId;
    String courseName;

    public Course(String id, String name) {
        this.courseId = id;
        this.courseName = name;
    }

    @Override
    public String toString() {
        return "Course [ID: " + courseId + ", Name: " + courseName + "]";
    }
}

// Student class implements Cloneable
class Student implements Cloneable {
    int rollNo;
    String name;
    Course course;  // Reference type → key for shallow vs deep copy

    public Student(int rollNo, String name, Course course) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
    }

    // Default Shallow Copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow copy
    }

    // Deep Copy version
    protected Student deepClone() throws CloneNotSupportedException {
        Student cloned = (Student) super.clone();
        cloned.course = new Course(this.course.courseId, this.course.courseName);
        return cloned;
    }

    @Override
    public String toString() {
        return "Student [RollNo: " + rollNo + ", Name: " + name + ", " + course + "]";
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("=== Course Registration System ===");

        Course course1 = new Course("CS101", "Data Structures");
        Student s1 = new Student(1, "Alice", course1);

        // Shallow copy
        Student shallowCopy = (Student) s1.clone();

        // Deep copy
        Student deepCopy = s1.deepClone();

        System.out.println("\nOriginal:      " + s1);
        System.out.println("Shallow Copy:  " + shallowCopy);
        System.out.println("Deep Copy:     " + deepCopy);

        System.out.println("\n--- Changing Course Name in Original ---");
        s1.course.courseName = "Advanced Data Structures";

        System.out.println("\nAfter modification:");
        System.out.println("Original:      " + s1);
        System.out.println("Shallow Copy:  " + shallowCopy);
        System.out.println("Deep Copy:     " + deepCopy);
    }
}
