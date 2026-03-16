import java.util.*;

class Student {
    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student s = (Student) obj;
        return rollNo == s.rollNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }

    @Override
    public String toString() {
        return "Student{RollNo=" + rollNo + ", Name='" + name + "'}";
    }
}

public class Topic3 {
    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>();

        Student s1 = new Student(101, "Aarav");
        Student s2 = new Student(102, "Meera");
        Student s3 = new Student(101, "Aarav"); // duplicate rollNo

        students.add(s1);
        students.add(s2);
        students.add(s3); // won't be added (duplicate)

        System.out.println("Students in HashSet:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
