package WEEK9.labproblems;

import java.util.*;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student s = (Student) obj;
        return id == s.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

public class Relationship {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();

        Student s1 = new Student(101, "Alice");
        Student s2 = new Student(102, "Bob");
        Student s3 = new Student(101, "Alice"); // duplicate id

        set.add(s1);
        set.add(s2);
        set.add(s3); // duplicate, won't be added

        System.out.println("HashSet Elements:");
        for (Student s : set)
            System.out.println(s);
    }
}
